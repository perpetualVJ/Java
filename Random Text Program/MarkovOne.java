
/**
 * Write a description of class MarkovOne here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public class MarkovOne {
    private String myText;
	private Random myRandom;
	
	public MarkovOne() {
		myRandom = new Random();
	}
	
	public void setRandom(int seed){
		myRandom = new Random(seed);
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
    
    ArrayList<String> getFollows(String key)
    {
        ArrayList<String> list = new ArrayList<String>();

        int pos = 0;
		while(pos < myText.length()){
			int idx = myText.indexOf(key, pos);

			if (idx == -1)
			{
				break;
			}

			if (idx  >= myText.length() - key.length() )
			{
				list.add(" ");
				pos = myText.length();
				break;
			}
			list.add(myText.substring(idx + key.length(), idx + key.length() + 1));
			pos = idx + 1;
		}
		
		return list; 
    }

}
