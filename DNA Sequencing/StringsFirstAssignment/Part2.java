public class Part2{

    String findSimpleGene(String dna, String startCodon, String stopCodon){
        
        int flag = 0;
        if (dna.toLowerCase() == dna)
        {
            flag = 1;
            dna = dna.toUpperCase();
        }
        String result = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1)
        {
            return "";
        }
        int endIndex = dna.indexOf("TAA", startIndex + 3);
        if (endIndex == -1)
        {
            return "";
        }
        
        if ((endIndex - startIndex) % 3 == 0)
        {
            result = dna.substring(startIndex, endIndex + 3);
            if (flag == 1)
            {
                result = result.toLowerCase();
            }
        }
        return result;
    }

    void testSimpleGene()
    {
        String dna = "";

        dna = "ATGATG";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna, "ATG", "TAA"));

        dna = "atgatgatgtaataa";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna, "ATG", "TAA"));

        dna = "ATGATGTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna, "ATG", "TAA"));

        dna = "ATGATGCTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna, "ATG", "TAA"));

        dna = "ATGATGTAAATGTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna, "ATG", "TAA"));
    }

    public static void main(String[] args)
    {
        Part2 p = new Part2();
        p.testSimpleGene();
    }
}