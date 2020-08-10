import edu.duke.*;
import java.util.*;

public class Tester{
    public void testGetFollows(){
        String text = "this is a test yes this is a test.";
        MarkovOne o = new MarkovOne();
        o.setTraining(text);
        ArrayList<String> list = o.getFollows("test");

        for (String s : list)
        {
            System.out.println(s);
        }

        System.out.println(list.size());
    }

    public void testGetFollowsWithFile(){
        FileResource fr = new FileResource();
        String text = fr.asString();
        MarkovOne m = new MarkovOne();
        m.setTraining(text);

        ArrayList<String> list = m.getFollows("he");

        /*
        for (String s : list)
        {
            System.out.println(s);
        }
        */
        System.out.println(list.size());
    }
    public static void main(String[] args)
    {
        Tester t = new Tester();
        t.testGetFollowsWithFile();
    }
}