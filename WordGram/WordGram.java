
public class WordGram {
    private String[] myWords;
    private int myHash;

    public WordGram(String[] source, int start, int size) {
        myWords = new String[size];
        System.arraycopy(source, start, myWords, 0, size);
    }

    public String wordAt(int index) {
        if (index < 0 || index >= myWords.length) {
            throw new IndexOutOfBoundsException("bad index in wordAt "+index);
        }
        return myWords[index];
    }

    public int length(){
        return myWords.length;
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myWords.length; i++)
        {
            sb.append(myWords[i]);
            sb.append(" ");
        }

        String ret = sb.toString();
        return ret.trim();
    }

    public boolean equals(Object o) {
        WordGram other = (WordGram) o;
        if (this.length() != other.length())
        return false;

        for (int i = 0; i < myWords.length; i++)
        {
            if(!myWords[i].equals(other.wordAt(i)))
            return false;
        }
        return true;

    }

    public WordGram shiftAdd(String word) {	
        String[] o = myWords;
        
        // shift all words one towards 0 and add word at the end. 
        // you lose the first word
        for (int i = 0; i < o.length - 1; i++)
        {
            o[i] = o[i+1];
        }

        o[o.length - 1] = word;

        WordGram out = new WordGram(o, 0, myWords.length);
        return out;
    }

}