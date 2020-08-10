
/**
 * Write a description of class MarkovOne here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public class MarkovOne extends AbstractMarkovModel{

	public MarkovOne() {
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
		int index = myRandom.nextInt(myText.length() - 1);
		String key = myText.substring(index, index + 1);

		sb.append(key);
		for (int i = 0; i < numChars - 1; i++)
		{
			ArrayList<String> list = getFollows(key);
			index = myRandom.nextInt(list.size());

			String next = list.get(index);
			key = next;
			sb.append(next);
		}
		
		return sb.toString();
    }
    
	public String toString(){
		return "Markov Model of Order One"; 
	}
}
