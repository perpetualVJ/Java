
/**
 * Write a description of class EfficientMarkovWord here.
 * 
 * @author Vishal Jotwani 
 * @version 9/8/2020
 */

import java.util.*;

public class EfficientMarkovWord implements IMarkovModel {
    private String[] myText;
    private Random myRandom;
    private int myOrder;
    private HashMap <String, ArrayList<String> > map;
    
    public EfficientMarkovWord(int order) {
        map = new HashMap <String, ArrayList<String> >();
        myRandom = new Random();
        myOrder = order;
    }
    
    public void setRandom(int seed) {
        myRandom = new Random(seed);
    }
    
    public void setTraining(String text){
		myText = text.split("\\s+");
	}
	
	public String getRandomText(int numWords){
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length-myOrder);  // random word to start with
        
        WordGram key = new WordGram(myText, index, myOrder);
		sb.append(key.toString());
		sb.append(" ");
		for(int k=0; k < numWords-myOrder; k++){
		    ArrayList<String> follows = getFollows(key);
			//System.out.println(key + follows);
		    if (follows.size() == 0) {
		        break;
		    }
			index = myRandom.nextInt(follows.size());
			String next = follows.get(index);
			sb.append(next);
			sb.append(" ");
			key = key.shiftAdd(next);
		}
		
		return sb.toString().trim();
	}

	private int indexOf(String[] words, WordGram target, int start)
	{
		for (int i = start; i < words.length - target.length() + 1; i++)
		{
            WordGram w = new WordGram(words, i, target.length());
            if (target.equals(w))
            return i;
		}
		return -1;
	}
	
	private ArrayList<String> getFollows(WordGram kGram) {
        if (map.containsKey(kGram.toString())){
            return map.get(kGram.toString());
        }

	    ArrayList<String> follows = new ArrayList<String>();

		int pos = 0;
		while (pos < myText.length)
		{
			int idx = indexOf(myText, kGram, pos);

			//System.out.println(idx);
			if (idx == -1)
			break;

			if (idx >= myText.length - myOrder)
			break;

			follows.add(myText[idx + myOrder]);

			pos = idx + myOrder;
			//System.out.println("pos " + pos);
			//System.out.println("key " + key);

		}
        map.put(kGram.toString(), follows);
	    return follows;
    }

    public void printHashMapInfo(){
        System.out.println("Size of HashMap " + map.size());

        int max = 0;
        for (String s : map.keySet())
        {
            //System.out.println(s + " " + map.get(s));
            if (map.get(s).size() > max)
            {
                max = map.get(s).size();
            }
        }

        System.out.println("Size of largest value in the hashmap" + max);

        System.out.println("Keys having largest value ");

        for (String s : map.keySet())
        {
            if (map.get(s).size() == max)
            {
                System.out.println(s);
				System.out.println("follow " + map.get(s));
            }
        }
    }
}
