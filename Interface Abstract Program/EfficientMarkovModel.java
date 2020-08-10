
/**
 * Write a description of class EfficientMarkovModel here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import java.util.*;

public class EfficientMarkovModel extends AbstractMarkovModel{
    private int N;
    private HashMap <String, ArrayList<String> > map;
	
	public EfficientMarkovModel(int n) {
		myRandom = new Random();
        map = new HashMap <String, ArrayList<String> >();
        N = n;
	}
	
	public void setTraining(String s){
		myText = s.trim();
		buildMap();
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
    
	private void buildMap(){
		
		for (int i = 0; i < myText.length() - N; i++)
		{
			String s = myText.substring(i, i + N);
			if (map.containsKey(s))
			{
				ArrayList<String> follows = map.get(s);
				map.remove(s);
				follows.add(myText.substring(i+N, i+ N + 1));
				map.put(s, follows);
			}
			else
			{
				ArrayList<String> follows = new ArrayList<String>();
				follows.add(myText.substring(i + N, i + N + 1));
				map.put(s, follows);
			}
		}

		String s = myText.substring(myText.length() - N, myText.length());
		if (!map.containsKey(s))
		{
			ArrayList<String> follows = new ArrayList<String>();
			map.put(s, follows);
		}
	}
    @Override    
	public String toString(){
		return "Markov Model of Order " + N; 
	}

    @Override
    protected ArrayList<String> getFollows(String key)
    {
        ArrayList<String> list = map.get(key);
		return list; 
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
            }
        }

    }
}
