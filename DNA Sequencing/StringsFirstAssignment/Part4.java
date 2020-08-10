import edu.duke.*;

public class Part4{
    public static void main(String[] args) {
        URLResource url = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        
        for (String words : url.words()) {
            words = words.toLowerCase();
            if (words.indexOf("youtube.com") != -1)
            {
                int index = words.indexOf("youtube.com");
                int start = words.lastIndexOf("\"", index);
                int stop = words.indexOf("\"", index);

                System.out.println(words.substring(start + 1, stop));
            }
        }
    }
}