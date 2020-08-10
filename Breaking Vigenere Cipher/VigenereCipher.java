import edu.duke.*;
import java.util.*;

public class VigenereCipher {
    CaesarCipher[] ciphers;
    
    public VigenereCipher(){

    }
    public VigenereCipher(int[] key) {
        ciphers = new CaesarCipher[key.length];
        for (int i = 0; i < key.length; i++) {
            ciphers[i] = new CaesarCipher(key[i]);
        }
    }
    
    public String encrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.encryptLetter(c));
            i++;
        } 
        return answer.toString();
    }
    
    public String decrypt(String input) {
        StringBuilder answer = new StringBuilder();
        int i = 0;
        for (char c : input.toCharArray()) {
            int cipherIndex = i % ciphers.length;
            CaesarCipher thisCipher = ciphers[cipherIndex];
            answer.append(thisCipher.decryptLetter(c));
            i++;
        }
        return answer.toString();
    }
    
    public String toString() {
        return Arrays.toString(ciphers);
    }

    String sliceString(String message, int whichSlice, int totalSlice)
    {
        StringBuilder s = new StringBuilder();
        for (int i = whichSlice; i < message.length(); i = i + totalSlice)
        {
            s.append(message.charAt(i));
        }

        return s.toString();
    }

    int[] tryKeyLength(String encrypted, int kLength, char mostCommon)
    {
        CaesarCracker c = new CaesarCracker(mostCommon);
        int[] key = new int[kLength];

        for (int i = 0; i < kLength; i++)
        {
            String e = sliceString(encrypted, i, kLength);
            key[i] = c.getKey(e);
        }
        return key;
    }

    HashSet<String> readDictionary(FileResource fr){
        HashSet<String> h = new HashSet<String>();

        for (String s : fr.lines()){
            s = s.toLowerCase();
            h.add(s);
        }

        return h;
    }

    int countWords(String message, HashSet<String> h)
    {
        int count = 0;
        String[] s = message.split("\\W");
        for (String i : s)
        {
            if (h.contains(i))
            {
                count++;
            }
        }
        return count;
    }

    String breakForLanguge(String encrypted, HashSet<String> dict)
    {
        int[] key;
        int [] makey = new int[1];
        int max = 0;
        String result = "";
        char mostCommon = mostCommonCharIn(dict);
        for (int i = 1; i < 100; i++)
        {
            key =  tryKeyLength(encrypted, i, mostCommon);
            VigenereCipher v = new VigenereCipher(key);
            String m = v.decrypt(encrypted);
            int count = countWords(m, dict);
            if (count > max)
            {
                makey = key;
                max = count;
                result = m;
            }
        }
        System.out.println("Key is ");
        for(int i : makey)
        {
            System.out.print(i + "\t");
        }
        System.out.println();
        return result;
    }

    char mostCommonCharIn(HashSet<String> dict)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int count[] = new int[26];

        for (String s : dict)
        {
            for (int i = 0; i < s.length(); i++){
                int idx = alphabet.indexOf(s.charAt(i));
                if(idx != -1)
                count[idx] += 1;
            }
        }

        int max = 0;
        for (int i = 0; i < 26; i++)
        {
            if (count[i] > count[max]){
                max = i;
            }
        }

        return alphabet.charAt(max);
    }

    void breakForAllLanguges(String encrypted, HashMap<String, HashSet<String> > languages){
        for (String s : languages.keySet()){
            System.out.println(s);
            System.out.println(breakForLanguge(encrypted, languages.get(s)));
        }
    }
    void breakVigenere(){
        FileResource fr = new FileResource();
        String message = fr.asString();
        System.out.println("Encrypt " + encrypt(message));

        HashMap<String, HashSet<String> > map = new HashMap<String, HashSet<String> >();
        System.out.println("Reading Dictionaries: ");

        String[] lang = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};

        for (int i = 0; i < 8; i++)
        {

            System.out.println("Read " + lang[i]);
             fr = new FileResource();
            HashSet<String> dict = readDictionary(fr);
            map.put(lang[i], dict);
        }
        System.out.println("Reading completed");
        breakForAllLanguges(message, map);

        System.out.println(sliceString("abcdefghijklm", 0, 3));
    }
    
    public static void main(String[] args) {
        int[] a = new int[1];
        a[0] = 1;
        VigenereCipher v = new VigenereCipher(a);
        v.breakVigenere();
    }
}
