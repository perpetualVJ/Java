
/**
 * Write a description of interface IMarkovModel here.
 * 
 * @author Vishal Jotwani 
 * @version 9/8/2020
 */

public interface IMarkovModel {
    public void setTraining(String text);
    
    public void setRandom(int seed);
    
    public String getRandomText(int numChars);

}
