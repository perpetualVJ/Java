import edu.duke.*;

public class Part2{
    int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
        int index = startIndex;
    
        if (stopIndex == -1)
        {
            return -1;
        }

        while(true)
        {

            stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            if (stopIndex == -1)
            break;
            
            if ((stopIndex - index) % 3 == 0)
            {
                break;
            }
            startIndex = stopIndex;
        }

        return stopIndex;
    }

    void testFindStopCodon(){
        String dna, stopCodon;
        int startIndex;

        dna = "ATGATGTAATAAATG";
        startIndex = 3;
        stopCodon = "TAA";

        System.out.println(dna + startIndex + stopCodon + " " + findStopCodon(dna, startIndex, stopCodon));

        dna = "TGATGATGAATG";
        startIndex = 9;
        stopCodon = "TGA";

        System.out.println(dna + startIndex + stopCodon + " " + findStopCodon(dna, startIndex, stopCodon));
    }

    String findGene(String dna, int where)
    {
        String result = "";

        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1)
        {
            return "";
        }

        int s1 = findStopCodon(dna, startIndex, "TAA");
        int s2 = findStopCodon(dna, startIndex, "TGA");
        int s3 = findStopCodon(dna, startIndex, "TAG");

        if (s1 == -1 && s2 == -1 && s3 == -1)
        {
            return "";
        }

        if (s1 <= s2 && s1 <= s3)
        {
            if (s1 != -1)
            {
                result = dna.substring(startIndex, s1 + 3);
            }
            else
            {
                if (s2 < s3)
                {
                    if (s2 == -1)
                    result = dna.substring(startIndex, s3 + 3);
                    else
                    result = dna.substring(startIndex, s2 + 3);
                }
                else{
                    if (s3 == -1)
                    result = dna.substring(startIndex, s2 + 3);
                    else
                    result = dna.substring(startIndex, s3 + 3);
                }
            }
        }
        else  if (s1 >= s2 && s2 <= s3)
        {
                if (s2 != -1)
                {
                    result = dna.substring(startIndex, s2 + 3);
                }
                else
                {
                    if (s1 < s3)
                    {
                        if (s1 == -1)
                        result = dna.substring(startIndex, s3 + 3);
                        else
                        result = dna.substring(startIndex, s1 + 3);
                    }
                    else{
                        if (s3 == -1)
                        result = dna.substring(startIndex, s1 + 3);
                        else
                        result = dna.substring(startIndex, s3 + 3);
                    }
                }
        }
        else
        {
            if (s3 != -1)
            {
                result = dna.substring(startIndex, s3 + 3);
            }
            else
            {
                if (s1 < s2)
                {
                    if (s1 == -1)
                    result = dna.substring(startIndex, s2 + 3);
                    else
                    result = dna.substring(startIndex, s1 + 3);
                }
                else{
                    if (s2 == -1)
                    result = dna.substring(startIndex, s1 + 3);
                    else
                    result = dna.substring(startIndex, s2 + 3);
                }
            }

            
        }
        return result;
    }
    void testFindGene()
    {
        String dna = "";
        int where = 0;
        dna = "AAAAAAAAAA";
        System.out.println(dna + " " + findGene(dna, where));

        dna = "ATGATGTAATAGTGA";
        System.out.println(dna + " " + findGene(dna, where));

        dna = "ATGATGTGA";
        System.out.println(dna + " " + findGene(dna, where));

        dna = "TAAATGATGTAA";
        System.out.println(dna + " " + findGene(dna, where));

        dna = "TGATGATGAATG";
        System.out.println(dna + " " + findGene(dna, where));
    }

    StorageResource getAllGenes(String dna){
        int startIndex = 0;
        String gene;
        StorageResource storage = new StorageResource();
        while(true)
        {
            gene = findGene(dna, startIndex);
            if(gene.equals(""))
                break;
            
            storage.add(gene);
            startIndex = dna.indexOf(gene, startIndex) + gene.length();
        }

        return storage;
    }

    void testGetAllGenes(){
        String dna = "ATGATGATGTAATAATGATGA";
        StorageResource s = getAllGenes(dna);
        System.out.println(s.size());

        FileResource fr = new FileResource();

        dna = fr.asString();
        
        System.out.println("CTG Count " + countCTG(dna));
        s = getAllGenes(dna);
        System.out.println(s.size());
        processGenes(s);
    }

    float cgratio(String dna){
        int countc = 0 , countg = 0;
        dna = dna.toUpperCase();
        for (int i = 0; i < dna.length(); i++)
        {
            if(dna.charAt(i) == 'C')
            countc += 1;
            if(dna.charAt(i)== 'G')
            countg += 1;
        }
        float cg = (float) (countc + countg) / dna.length();
        return cg;
    }

    int countCTG(String dna)
    {
        int count = 0;
        dna = dna.toUpperCase();
        for (int i = 0; i < dna.length() - 3; i++)
        {
            if (dna.charAt(i) == 'C' && dna.charAt(i+1) == 'T' && dna.charAt(i+2) == 'G')
            count++;
        }

        return count;
    }

    void processGenes(StorageResource sr){
        
        int count = 0;
        System.out.println("Strings having characters greater than 9");
        for(String s: sr.data()){
            if (s.length() > 60)
            {
                count += 1;
                System.out.println(s);
            }
        }

        System.out.println("No. of strings having chars greater than 60 " + count);

        count = 0;
        System.out.println("Strings having cg ratio greater than 0.35");
        for(String s: sr.data()){
            if (cgratio(s) > 0.35)
            {
                count += 1;
                System.out.println(s);
            }
        }

        System.out.println("No. of strings having cg ratio greater than 0.35 " + count);

        
        System.out.println("Strings having largest length");
        int max = -1;
        for(String s: sr.data()){
            if (s.length() > max)
            {
                max = s.length();
            }
        }

        System.out.println("Length of largest string is " + max);
    }
    public static void main(String[] args)
    {
        Part2 p = new Part2();
        p.testFindStopCodon();
        p.testFindGene();
        p.testGetAllGenes();

    }

}
