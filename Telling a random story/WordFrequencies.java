import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies{

    private ArrayList <String> myWords;
    private ArrayList <Integer> myFreqs;

    //WordFrequencies Constructor
    public WordFrequencies(){
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    void findUnique(){
        myFreqs.clear();
        myWords.clear();

        FileResource fr = new FileResource();

        for (String s : fr.words()){
            s = s.toLowerCase();
            if (!myWords.contains(s)){
                myWords.add(s);
                myFreqs.add(1);
            }
            else{
                int idx = myWords.indexOf(s);
                int value = myFreqs.get(idx);
                myFreqs.set(idx, value + 1);
            }
        }
    }

    int findIndexOfMax(){
        int max = 0;

        for (int i = 0; i < myFreqs.size(); i++)
        {
            if (myFreqs.get(i) > myFreqs.get(max))
            {
                max = i;
                //System.out.println(max);
            }
                
        }
        //System.out.println(myWords.size());
        //System.out.println(myFreqs.size());
        return max;
    }
    void tester(){
        findUnique();

        System.out.println("Word\t Frequency\t");
        for (int i = 0; i < myWords.size(); i++)
        {
            System.out.println(myWords.get(i) +"\t " + myFreqs.get(i));
        }

        System.out.println("Maximal occuring word " + myWords.get(findIndexOfMax()) + " Frequency " +myFreqs.get(findIndexOfMax()));
        System.out.println("No. of unique words " + myWords.size());
    }

    public static void main(String[] args) {
        WordFrequencies w = new WordFrequencies();
        w.tester();
    }
}