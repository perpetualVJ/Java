import java.util.*;

public class LargestQuakes{
    public void findLargestQuakes(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "nov20quakedata.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);

        /* for (QuakeEntry q : list)
        {
            System.out.println(q);
        } */

        System.out.println("read data for "+list.size()); 

        ArrayList<QuakeEntry> largest = getLargest(list, 50);
        for(int k=0; k < largest.size(); k++){
            System.out.println(largest.get(k));
        }
        System.out.println("number found: "+largest.size());       
    }

    private int indexOfLargest(ArrayList<QuakeEntry> QuakeData)
    {
        int max = 0;
        for (int i = 0; i < QuakeData.size(); i++)
        {
            if (QuakeData.get(i).getMagnitude() > QuakeData.get(max).getMagnitude())
            {
                max = i;
            }
        }
        return max;
    }

    public ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> QuakeData, int howMany)
    {
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();
        ArrayList<QuakeEntry> quake = QuakeData;
        for (int i = 0; i < howMany && i < quake.size(); i++){
            QuakeEntry q = null;
            int max = indexOfLargest(quake);
            q = quake.get(max);
            ret.add(q);
            quake.remove(q);
        }

        return ret;
    }

    public static void main(String[] args) {
        LargestQuakes l = new LargestQuakes();
        l.findLargestQuakes();
    }
}