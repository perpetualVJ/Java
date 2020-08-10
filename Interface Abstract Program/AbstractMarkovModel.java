
/**
 * Abstract class AbstractMarkovModel - write a description of the class here
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public abstract class AbstractMarkovModel implements IMarkovModel {
    protected String myText;
    protected Random myRandom;
    
    public AbstractMarkovModel() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
		myRandom = new Random(seed);
	}
    
    public void setTraining(String s) {
        myText = s.trim();
    }
 
    abstract public String getRandomText(int numChars);

    protected ArrayList<String> getFollows(String key)
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
