import edu.duke.*;

public class CaesarBreaker{
    int[] countLetters(String phrase){
        int[] counts = new int[26];

        String alpha = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < phrase.length(); i++)
        {
            int idx = alpha.indexOf(Character.toLowerCase(phrase.charAt(i)));
            if (idx != -1)
            counts[idx] += 1;
        }

        return counts;
    }

    int maxIndex(int[] counts)
    {
        int idx = 0;
        for (int i = 0; i < counts.length; i++)
        {
            if (counts[i] > counts[idx])
            {
                idx = i;
            }
        }
        return idx;
    }

    String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();

        int[] freq = countLetters(encrypted);
        int maxdex = maxIndex(freq);

        System.out.println(maxdex);
        int dkey = maxdex - 4;
        if (maxdex < 4)
        {
            dkey = 26 - (4 - maxdex);
        }

        return cc.encrypt(encrypted, 26 - dkey);
    }

    void testDecrypt(){
        String s = "Cfopq Idbflk";
        System.out.println("Decrypt " + s + " " + decrypt(s));
    }

    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
    }
}