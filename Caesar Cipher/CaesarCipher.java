import edu.duke.*;

public class CaesarCipher{
    String encrypt(String input, int key){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);

        String ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ushifted = ualphabet.substring(key) + ualphabet.substring(0, key);
        int flag = 0;

        char ch ;
        StringBuilder in = new StringBuilder(input);
        for (int i = 0; i < in.length(); i++)
        {
            ch = in.charAt(i);
            if (Character.isLowerCase(ch)){
                int idx = alphabet.indexOf(ch);
                if (idx != -1)
                {
                    in.setCharAt(i, shiftedAlphabet.charAt(idx));
                }
            }
            else{
                int idx = ualphabet.indexOf(ch);
                if (idx != -1)
                {
                    in.setCharAt(i, ushifted.charAt(idx));
                }
            }
            
        }
        String inp = new String(in);
        return inp;
    }

    void testEncrypt(){
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("Testing testEncrypt(): ");
        System.out.println(input + "23" + encrypt(input, 15));
    }

    String encryptTwoKeys(String input, int key1, int key2)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String shiftedAlphabetOdd = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabetEven = alphabet.substring(key2) + alphabet.substring(0, key2);

        String ualphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String ushiftedOdd = ualphabet.substring(key1) + ualphabet.substring(0, key1);
        String ushiftedEven = ualphabet.substring(key2) + ualphabet.substring(0, key2);
        int flag = 0;

        char ch ;
        StringBuilder in = new StringBuilder(input);
        for (int i = 0; i < in.length(); i++)
        {
            
           if (i % 2 == 0)
           {
            ch = in.charAt(i);
            if (Character.isLowerCase(ch)){
                int idx = alphabet.indexOf(ch);
                if (idx != -1)
                {
                    in.setCharAt(i, shiftedAlphabetOdd.charAt(idx));
                }
            }
            else{
                int idx = ualphabet.indexOf(ch);
                if (idx != -1)
                {
                    in.setCharAt(i, ushiftedOdd.charAt(idx));
                }
            }
           }
            else{
                ch = in.charAt(i);
                if (Character.isLowerCase(ch)){
                    int idx = alphabet.indexOf(ch);
                    if (idx != -1)
                    {
                        in.setCharAt(i, shiftedAlphabetEven.charAt(idx));
                    }
                }
                else{
                    int idx = ualphabet.indexOf(ch);
                    if (idx != -1)
                    {
                        in.setCharAt(i, ushiftedEven.charAt(idx));
                    }
                }
            }
        }
        String inp = new String(in);
        return inp;
    }

    void testEncryptTwoKeys()
    {
        String input = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
        System.out.println("Testing testEncryptTwoKeys(): ");
        System.out.println(input  + " " + encryptTwoKeys(input, 21, 8));
    }
    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        c.testEncrypt();
        c.testEncryptTwoKeys();
    }
}