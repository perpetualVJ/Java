import edu.duke.*;
import java.util.ArrayList;

public class CharactersInPlay{
    private ArrayList <String> myChar;
    private ArrayList <Integer> myFreqs;

    public CharactersInPlay(){
        myChar = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    void update(String person){
        if (myChar.contains(person)){
            int idx = myChar.indexOf(person);
            int value = myFreqs.get(idx);
            myFreqs.set(idx, value + 1);
        }
        else{
            myChar.add(person);
            myFreqs.add(1);
        }
    }

    void findAllCharacters(){
        myChar.clear();
        myFreqs.clear();

        FileResource fr = new FileResource();

        String sub;

        for (String s : fr.lines()){
            int idx = s.indexOf('.');

            if (idx != -1)
            {
                sub = s.substring(0, idx);
                update(sub);
            }
        }
    }

    void tester(){
        findAllCharacters();

        System.out.println("Character\t Frequency\t");
        for (int i = 0; i < myChar.size(); i++)
        {
            if (myFreqs.get(i) > 1)
            System.out.println(myChar.get(i) +"\t " + myFreqs.get(i));
        }

        characterWithNumParts(10, 15);

    }

    void characterWithNumParts(int num1, int num2){
        System.out.println("Character\t Parts\t");
        for (int i = 0; i < myChar.size(); i++)
        {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2)
            System.out.println(myChar.get(i) +"\t " + myFreqs.get(i));
        }
    }
    public static void main(String[] args) {
        CharactersInPlay c = new CharactersInPlay();
        c.tester();
    }
}