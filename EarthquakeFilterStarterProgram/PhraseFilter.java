import edu.duke.*;

public class PhraseFilter implements Filter{
    String where;
    String phrase;

    public PhraseFilter(String where, String phrase)
    {
        this.where = where;
        this.phrase = phrase;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        String title = qe.getInfo();

        if (where.equals("start"))
        {
            if (title.startsWith(phrase))
            return true;
        }
        else if (where.equals("end"))
        {
            if (title.endsWith(phrase))
            return true;
        }
        else{
            if (title.contains(phrase))
            return true;
        }
        return false;
    }

    public String getName(){
        return "Phrase";
    }
}