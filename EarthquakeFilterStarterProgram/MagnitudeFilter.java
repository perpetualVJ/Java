import edu.duke.*;

public class MagnitudeFilter implements Filter{
    double min;
    double max;

    public MagnitudeFilter(double min, double max)
    {
        this.min = min;
        this.max = max;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        if (qe.getMagnitude() >= min && qe.getMagnitude() <= max)
        {
            return true;
        }
        return false;
    }

    public String getName(){
        return "Magnitude";
    }
}