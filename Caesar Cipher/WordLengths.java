import edu.duke.*;

public class WordLengths{
    void countWordLengths(FileResource resource, int[] counts){
        int start = 0;
        int last = 0;
        int size;
        for(String s : resource.words()){
            start = 0;
            last = s.length() - 1;
        
            if(s.length() == 1)
            {
                if(Character.isLetter(s.charAt(0)))
                counts[1]++;
            }
            else if (s.length() > 1){
                while(!Character.isLetter(s.charAt(start)))
            {
                start++;
            }
            while(!Character.isLetter(s.charAt(last)))
            {
                last--;
            }

            size = last - start + 1;
            if(size >= counts.length)
            {
                counts[counts.length - 1]++;
            }
            else
            {
                counts[size]++;
            }
            }
            
        }

        for (int i = 0; i < counts.length; i++)
        {
            System.out.println("counts[" + i + "]\t" + counts[i]);
        }
    }

    int indexMax(int[] values){
        int midx = 0;
        for (int i = 0; i < values.length; i++)
        {
            if (values[i] > values[midx])
            {
                midx = i;
            }
        }
        return midx;
    }
    void testCountWordLengths(){
        FileResource f = new FileResource();
        int[] counts = new int[31];
        countWordLengths(f, counts);

        System.out.println("Maximum element Index in file " + indexMax(counts));
    } 

    public static void main(String[] args) {
        WordLengths w = new WordLengths();
        w.testCountWordLengths();
    }
}