
/**
 * Write a description of class MarkovWordTwo here.
 * 
 * @author Vishal Jotwani 
 * @version 9/8/2020
 */

import java.util.*;

public class MarkovWordTwo implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    
    public MarkovWordTwo() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-2);  // random word to start with
		String key1 = myText[index];
        String key2 = myText[index + 1];
		sb.append(key1);
		sb.append(" ");
        sb.append(key2);
        sb.append(" ");
		for(int k=0; k < numWords-2; k++){
		    ArrayList<String> follows = getFollows(key1, key2);
			//System.out.println(key + follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
            key1 = key2;
			key2 = next;
		}
		
		return sb.toString().trim();
	}

	private int indexOf(String[] words, String target1, String target2, int start)
	{
		for (int i = start; i < words.length - 1; i++)
		{
			if (words[i].equals(target1) && words[i + 1].equals(target2))
			return i;
		}
		return -1;
	}
	
	public void testIndexOf()
	{
		String text = "this is just a test yes this is a simple test";
		String[] t = text.split("\\s+");

		System.out.println(indexOf(t, "a", "test", 1));
	}
	private ArrayList<String> getFollows(String key1, String key2) {
	    ArrayList<String> follows = new ArrayList<String>();

		int pos = 0;
		while (pos < myText.length)
		{
			int idx = indexOf(myText, key1, key2, pos);

			//System.out.println(idx);
			if (idx == -1)
			break;

			if (idx >= myText.length - 2)
			break;

			follows.add(myText[idx + 2]);

			pos = idx + 2;
			//System.out.println("pos " + pos);
			//System.out.println("key " + key);

		}
	    return follows;
    }

	public static void main(String[] args)
	{
		MarkovWordTwo m = new MarkovWordTwo();
		m.testIndexOf();
	}
}
