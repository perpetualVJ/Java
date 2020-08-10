import edu.duke.*;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.File;

public class WordsInFiles{
    
    private HashMap <String, ArrayList<String> > wordCount;
    private ArrayList<String> words;

    public WordsInFiles(){
        wordCount = new HashMap<String, ArrayList<String> >();
        words = new ArrayList<String>();
    }

    private void addWordsFromFile(File f)
    {
        FileResource fr = new FileResource(f);
        ArrayList<String> a = new ArrayList<String>();
        for (String s: fr.words())
        {
            if(!words.contains(s)) 
            {
                words.add(s);
            }  

            if (wordCount.containsKey(f.getName()))
            {
                //System.out.println(f.getName());
                a = wordCount.get(f.getName());
                a.add(s);
                wordCount.remove(wordCount.get(f.getName()));
                wordCount.put(f.getName(), a);
            }
            else
            {
                ArrayList <String> b = new ArrayList<String>();
                b.add(s);
                wordCount.put(f.getName(), b);
            }
        }
    }

    void buildWordFileMap(){
        DirectoryResource dr = new DirectoryResource();
        wordCount.clear();

        for (File f: dr.selectedFiles()){
            addWordsFromFile(f);
        }
    }

    int maxFiles(){
        int max = 0;
        int count = 0;
        for (String s : words){
            count = 0;
            for (ArrayList<String> a : wordCount.values()){
                if (a.contains(s))
                count++;
            }
            if (count > max)
            max = count;
        }
        return max;
    }

    ArrayList<String> wordsInNumFiles(int number)
    {
        ArrayList<String> a = new ArrayList<String>();
        int count = 0;
        for (String s : words){
            count = 0;
            for (ArrayList<String> b : wordCount.values()){
                if (b.contains(s))
                count++;
            }
            if (count == number)
            a.add(s);
        }
        return a;
    }

    void printFiles(String word)
    {
        ArrayList<String> a = new ArrayList<String>();
        for (String s : wordCount.keySet()){
            a = wordCount.get(s);
            if (a.contains(word))
            {
                System.out.println(s);
            }
        }
    }

    void tester(){
        buildWordFileMap();
        System.out.println("Maximum no. of files any word is in " + maxFiles());

        printFiles("laid");
        System.out.println(" ");
        printFiles("tree");
        //for (String s : a){
        //    System.out.println(s);
        //    printFiles(s);
        //}

        /*System.out.println("Content of HashMap");
        for (String s : wordCount.keySet()){
            ArrayList<String> list = wordCount.get(s);
            System.out.println(s);
            for (String i : list){
                System.out.println(i);
            }
        }*/
    }

    public static void main(String[] args){
        WordsInFiles w = new WordsInFiles();
        w.tester();
    }
}