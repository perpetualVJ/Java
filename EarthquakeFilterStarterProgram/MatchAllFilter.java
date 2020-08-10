import edu.duke.*;
import java.util.*;

public class MatchAllFilter implements Filter{
    private ArrayList<Filter> filter;

    public MatchAllFilter(){
        filter = new ArrayList<Filter>();
    }

    public void addFilter(Filter f)
    {
        filter.add(f);
    }

    public boolean satisfies(QuakeEntry qe)
    {
        for (Filter f : filter){
            if (!f.satisfies(qe))
            return false;
        }
        return true;
    }

    public String getName(){
        StringBuilder name = new StringBuilder();
        for (Filter f : filter)
        {
            name.append(f.getName());
            name.append(" ");
        }

        return name.toString();
    }
}