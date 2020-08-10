import edu.duke.*;

public class Part1{
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
    }
    public static void main(String[] args)
    {
        Part1 p = new Part1();
        p.testFindStopCodon();
        p.testFindGene();
        p.testGetAllGenes();
    }
}
