import edu.duke.*;
import java.util.HashMap;

public class CodonCount{

    private HashMap<String, Integer> dnaCount;
    public CodonCount(){
        dnaCount = new HashMap<String, Integer>();
    }

    void buildCodonMap(int start, String dna)
    {
        dnaCount.clear();
        dna = dna.toUpperCase();

        for (int i = start; i < dna.length() - 3; i = i + 3)
        {
            String sub = dna.substring(i, i + 3);
            if(dnaCount.containsKey(sub))
            {
                int value = dnaCount.get(sub);
                dnaCount.remove(sub);
                dnaCount.put(sub, value + 1);
            }
            else
            {
                dnaCount.put(sub, 1);
            }
        }
    }

    String getMostCommonCodon(){
        int max = -1;
        String common = "";

        for (String s : dnaCount.keySet())
        {
            if (max == -1)
            {
                max = dnaCount.get(s);
                common = s;
            }
            else{
                if (max < dnaCount.get(s))
                {
                    max = dnaCount.get(s);
                    common = s;
                }
            }
        }
        return common;
    }

    void printCodonCounts(int start, int end){
        System.out.println("Codon With Counts Between " + start + " " + end);
        for (String s : dnaCount.keySet()){
            if (dnaCount.get(s) >= start && dnaCount.get(s) <= end){
                System.out.println(s + " " + dnaCount.get(s));
            }
        }
    }

    void tester(){
        FileResource fr = new FileResource();
        String s = fr.asString();

        for (int i = 0; i < 3; i++){
            System.out.println("Reading map starting with " + i);
            buildCodonMap(i, s);
            System.out.println("No. of unique Codons " + dnaCount.size());
            System.out.println("Most Common Codon " + getMostCommonCodon() + "Count " + dnaCount.get(getMostCommonCodon()));
            printCodonCounts(1, 7);
        }
    }
    
    public static void main(String[] args) {
        CodonCount c = new CodonCount();
        c.tester();
    }
}