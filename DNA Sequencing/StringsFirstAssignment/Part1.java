public class Part1{

    String findSimpleGene(String dna){
        
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
        }
        return result;
        
    }

    void testSimpleGene()
    {
        System.out.println("Testing Simple Gene ");
        String dna = "";

        dna = "ATGATG";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna));

        dna = "ATGACTGACTGACTGTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna));

        dna = "ATGATGTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna));

        dna = "ATGATGCTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna));

        dna = "ATGATGTAAATGTAA";
        System.out.println("DNA is " + dna + " gene is " + findSimpleGene(dna));
    }

    public static void main(String[] args)
    {
        Part1 p = new Part1();
        p.testSimpleGene();
    }
}