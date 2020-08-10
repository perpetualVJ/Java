import edu.duke.*;

public class wordPlay{
    boolean isVowel(char ch)
    {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u')
        {
            return true;
        }
        return false;
    }
    void testIsVowel(){

        System.out.println("Testing isVowel(): ");
        System.out.println("a " + isVowel('a'));
        System.out.println("A " + isVowel('A'));
        System.out.println("f " + isVowel('f'));
    }

    StringBuilder replaceVowels(StringBuilder phrase, char ch)
    {
        for (int i = 0; i < phrase.length(); i++)
        {
            if(isVowel(phrase.charAt(i)))
            {
                phrase.setCharAt(i, ch);
            }
        }
        return phrase;
    }

    void testReplaceVowels(){
        System.out.println("Testing replace Vowels: ");
        
        StringBuilder phrase = new StringBuilder("Oh my gode");
        char ch;

        ch = 'k';
        System.out.println(phrase + " " + ch +" " + replaceVowels(phrase, ch));
    }

    StringBuilder emphasize(StringBuilder phrase, char ch)
    {
        ch = Character.toLowerCase(ch);
        for (int i = 0; i < phrase.length(); i++)
        {
            if(Character.toLowerCase(phrase.charAt(i)) == ch)
            {
                if (i % 2 != 0)
                {
                    phrase.setCharAt(i, '+');
                }
                else if(i % 2 == 0)
                {
                    phrase.setCharAt(i, '*');
                }
            }
        }
        return phrase;
    }

    void testEmphasize(){
        System.out.println("Testing emphasize(): ");
        
        StringBuilder phrase = new StringBuilder("dna ctgaaactga");
        char ch;

        ch = 'a';
        System.out.println(phrase + " " + ch +" " + emphasize(phrase, ch));

        phrase = new StringBuilder("Mary Bella Abracadabra");
        System.out.println(phrase + " " + ch +" " + emphasize(phrase, ch));
        
    }


    public static void main(String[] args) {
        wordPlay w = new wordPlay();
        w.testIsVowel();
        w.testReplaceVowels();
        w.testEmphasize();
    }
}