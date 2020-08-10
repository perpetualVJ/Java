import edu.duke.*;

public class DistanceFilter implements Filter{
    double distance;
    Location city;

    public DistanceFilter(double distance, Location city)
    {
        this.distance =distance;
        this.city = city;
    }

    public boolean satisfies(QuakeEntry qe)
    {
        if (qe.getLocation().distanceTo(city) < distance)
        {
            return true;
        }
        return false;
    }

    public String getName(){
        return "Distance";
    }
}