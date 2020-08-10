import edu.duke.*;

public class DepthFilter implements Filter{
    double min;
    double max;

    public DepthFilter(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        if (qe.getDepth() >= min && qe.getDepth() <= max)
        {
            return true;
        }
        return false;
    }

    public String getName(){
        return "Depth";
    }
}