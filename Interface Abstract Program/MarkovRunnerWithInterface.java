
/**
 * Write a description of class MarkovRunner here.
 * 
 * @author Vishal Jotwani
 * @version 9/8/2020
 */

import edu.duke.*; 

public class MarkovRunnerWithInterface {
    public void runModel(IMarkovModel markov, String text, int size, int seed) {
        markov.setTraining(text);
		markov.setRandom(seed);
        System.out.println("running with " + markov);
        for(int k=0; k < 3; k++){
			String st= markov.getRandomText(size);
			printOut(st);
		}
    }
    
    public void runMarkov() {
        FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		int size = 200;
		int seed = 42;
        MarkovZero mz = new MarkovZero();
        runModel(mz, st, size, seed);
    
        MarkovOne mOne = new MarkovOne();
        runModel(mOne, st, size, seed);
        
        MarkovModel mThree = new MarkovModel(3);
        runModel(mThree, st, size, seed);
        
        MarkovFour mFour = new MarkovFour();
        runModel(mFour, st, size, seed);

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
		EfficientMarkovModel markov = new EfficientMarkovModel(5);
		//String st = "yes-this-is-a-thin-pretty-pink-thistle";

		FileResource fr = new FileResource();
		String st = fr.asString();
		st = st.replace('\n', ' ');
		runModel(markov, st, 50, 531);
		markov.printHashMapInfo();
	}
	public void compareMethods()
	{
		MarkovModel markov = new MarkovModel(2);
		EfficientMarkovModel emarkov = new EfficientMarkovModel(2);

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
		MarkovRunnerWithInterface m = new MarkovRunnerWithInterface();
		m.runMarkov();
		m.testHashMap();
		m.compareMethods();
	}
}
