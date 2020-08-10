
/**
 * Write a description of class MarkovModel here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public class MarkovModel extends AbstractMarkovModel{
    private int N;
	
	public MarkovModel(int n) {
		myRandom = new Random();
        N = n;
	}
	
	public void setTraining(String s){
		myText = s.trim();
	}
	 
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - N);
		String key = myText.substring(index, index + N);

		sb.append(key);
		for (int i = 0; i < numChars - N; i++)
		{
			ArrayList<String> list = getFollows(key);
			if (list.isEmpty())
            break;
			index = myRandom.nextInt(list.size());

			String next = list.get(index);
			key = key.substring(1) + next;
			sb.append(next);
		}
		
		return sb.toString();
    }
    
    
	public String toString(){
		return "Markov Model of Order " + N; 
	}
}
