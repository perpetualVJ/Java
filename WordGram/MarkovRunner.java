
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Vishal Jotwani 
 * @version 9/8/2020
 */

import edu.duke.*;

public class MarkovRunner {
    public void runModel(IMarkovModel markov, String text, int size){ 
        markov.setTraining(text); 
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runModel(IMarkovModel markov, String text, int size, int seed){ 
        markov.setTraining(text); 
        markov.setRandom(seed);
        System.out.println("running with " + markov); 
        for(int k=0; k < 3; k++){ 
            String st = markov.getRandomText(size); 
            printOut(st); 
        } 
    } 

    public void runMarkov() { 
        FileResource fr = new FileResource(); 
        String st = fr.asString(); 
        st = st.replace('\n', ' '); 
        MarkovWord markovWord = new MarkovWord(3); 
        runModel(markovWord, st, 50, 643); 
    } 

    private void printOut(String s){
        String[] words = s.split("\\s+");
        int psize = 0;
        System.out.println("----------------------------------");
        for(int k=0; k < words.length; k++){
            System.out.print(words[k]+ " ");
            psize += words[k].length() + 1;
            if (psize > 60) {
                System.out.println(); 
                psize = 0;
            } 
        } 
        System.out.println("\n----------------------------------");
    } 

    public void testHashMap(){
		EfficientMarkovWord markov = new EfficientMarkovWord(2);
		String text = "this is a test yes this is really a test yes a test this is wow";
		runModel(markov, text, 50, 42);
		markov.printHashMapInfo();
	}
	public void compareMethods()
	{
		MarkovWord markov = new MarkovWord(2);
		EfficientMarkovWord emarkov = new EfficientMarkovWord(2);

        
		FileResource fr = new FileResource();
		String text = fr.asString();

		double b1 = System.nanoTime();
		runModel(markov, text, 1000, 42);
        double e1 = System.nanoTime();

        System.out.println((e1 - b1) / 1e9);

		double b2 = System.nanoTime();
		runModel(emarkov, text, 1000, 42);
        double e2 = System.nanoTime();

        System.out.println((e2 - b2) / 1e9);

	}
    public static void main(String[] args)
    {
        MarkovRunner m = new MarkovRunner();
        m.testHashMap();
        //m.compareMethods();
    }
}
