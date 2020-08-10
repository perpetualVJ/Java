public class Part2{
    int howMany(String a, String b)
    {
        a = a.toLowerCase();
        b = b.toLowerCase();
        int count = 0;
        int index = 0;
        int flag = 0;
        while (b.indexOf(a, index) != -1)
        {
            count += 1;
            index = b.indexOf(a, index);
            index += a.length();
        }
        return count;
    }

    void testHowMany()
    {
        String a = "", b = "";

        a = "a";  
        b = "A story by Abby Long";
        System.out.println(a + " " + b +" " + howMany(a, b));

        a = "aa";
        b = "baaanaa";
        System.out.println(a + " " + b +" " + howMany(a, b));
        
        a = "a";
        b = "vishal";
        System.out.println(a + " " + b +" " + howMany(a, b));
    }

    
    public static void main(String[] args) {
        
        Part2 p = new Part2();
        p.testHowMany();
    }
}