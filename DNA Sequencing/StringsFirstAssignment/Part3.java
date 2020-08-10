public class Part3{
    boolean twoOccurences(String a, String b)
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int count = 0;
        int index = 0;
        while (b.indexOf(a, index) != -1)
        {
            count += 1;
            index = b.indexOf(a, index);
        }

        if (count >= 2)
        {
            return true;
        }
        else{
            return false;
        }
    }

    void testTwoOccurences()
    {
        String a = "", b = "";

        a = "a";  
        b = "A story by Abby Long";
        System.out.println(a + b + twoOccurences(a, b));

        a = "a";
        b = "banana";
        System.out.println(a + b + twoOccurences(a, b));

        a = "a";
        b = "vishal";
        System.out.println(a + b + twoOccurences(a, b));
    }

    String lastPart(String a, String b)
    {
        int index = b.indexOf(a);
        if(index == -1)
        {
            return b;
        }

        return b.substring(index + a.length());
    }

    void testLastPart()
    {
        String a = "an";
        String b = "banana";
        System.out.println(a + b + lastPart(a, b));
    }

    public static void main(String[] args) {
        
        Part3 p = new Part3();
        p.testTwoOccurences();
        p.testLastPart();
    }
}