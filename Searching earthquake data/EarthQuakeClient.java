import java.util.*;
import edu.duke.*;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData,
    double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry q : quakeData){
            if(q.getMagnitude() > magMin){
                answer.add(q);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData,
    double distMax,
    Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        
        for (QuakeEntry q : quakeData){
            if (q.getLocation().distanceTo(from) < distMax)
            {
                answer.add(q);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth,
    double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry q : quakeData)
        {
            if (q.getDepth() > minDepth && q.getDepth() < maxDepth)
            {
                answer.add(q);
            }
        }

        return answer;
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where,
    String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        for(QuakeEntry q : quakeData)
        {
            String title = q.getInfo();

            if (where.equals("start"))
            {
                if (title.startsWith(phrase))
                answer.add(q);
            }
            else if (where.equals("end"))
            {
                if (title.endsWith(phrase))
                answer.add(q);
            }
            else{
                if (title.contains(phrase))
                answer.add(q);
            }

        }

        return answer;
    }
    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> filterMag = filterByMagnitude(list, 5.0);

        for (QuakeEntry q : filterMag)
        {
            System.out.println(q);
        }

        System.out.println("Found " + filterMag.size() + " earthquakes that match that criteria");
    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        String source = "nov20quakedatasmall.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> dist = filterByDistanceFrom(list, 1000000.0, city);

        for (QuakeEntry q : dist){
            System.out.println(q.getInfo());
        }
        System.out.println("Found " + dist.size() + " earthquakes that match that criteria");
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> depth = filterByDepth(list, -4000.0, -2000);

        for (QuakeEntry q : depth){
            System.out.println(q);
        }
        System.out.println("Found " + depth.size() + " earthquakes that match that criteria");
    }

    public void quakesByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        String source = "nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> phrase = filterByPhrase(list, "any", "Can");

        for (QuakeEntry q : phrase){
            System.out.println(q);
        }
        System.out.println("Found " + phrase.size() + " earthquakes that match that criteria");
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }
    
    public static void main(String[] args) {
        EarthQuakeClient e = new EarthQuakeClient();
        //e.quakesOfDepth();
        e.quakesByPhrase();
    }
}
