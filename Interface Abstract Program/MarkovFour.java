
/**
 * Write a description of class MarkovFour here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public class MarkovFour extends AbstractMarkovModel{
	
	public MarkovFour() {
		myRandom = new Random();
	}
	
	
	public void setTraining(String s){
		myText = s.trim();
	}
	 
	public String getRandomText(int numChars){
		if (myText == null){
			return "";
		}
		StringBuilder sb = new StringBuilder();
		int index = myRandom.nextInt(myText.length() - 4);
		String key = myText.substring(index, index + 4);

		sb.append(key);
		for (int i = 0; i < numChars - 4; i++)
		{
			ArrayList<String> list = getFollows(key);
			index = myRandom.nextInt(list.size());

			String next = list.get(index);
			key = key.substring(1) + next;
			sb.append(next);
		}
		
		return sb.toString();
    }
    
	public String toString(){
		return "Markov Model of Order Four"; 
	}
}
