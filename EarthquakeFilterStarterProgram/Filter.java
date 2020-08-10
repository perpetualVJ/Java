
/**
 * Write a description of interface Filter here.
 * 
 * @author (Vishal Jotwani) 
 * @version (7/8/2020)
 */
public interface Filter
{
    public  boolean satisfies(QuakeEntry qe); 

    public String getName();
}
