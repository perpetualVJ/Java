
/**
 * Write a description of class MinMaxFilter here.
 * 
 * @author (Vishal Jotwani) 
 * @version (7/8/2020)
 */
public class MinMagFilter implements Filter
{
    private double magMin; 
    
    public MinMagFilter(double min) { 
        magMin = min;
    } 

    public boolean satisfies(QuakeEntry qe) { 
        return qe.getMagnitude() >= magMin; 
    } 

    public String getName(){
        return "MinMag";
    }
}
