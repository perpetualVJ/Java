import edu.duke.*;
import java.util.*;

public class LogAnalyser{
    private ArrayList<LogEntry> webLog;

    public LogAnalyser(){
        webLog = new ArrayList<LogEntry>();
    }

    void readFile(){
        FileResource fr = new FileResource();

        for (String s : fr.lines())
        {
            webLog.add(WebLogParser.parseEntry(s));
        }
    }

    void printAll(){
        for (LogEntry l : webLog){
            System.out.println(l);
        }
    }

    int countUniqueIPs(){
        ArrayList<String> unique = new ArrayList<String>();

        for (LogEntry s: webLog)
        {
            if (!unique.contains(s.getIPAddress())){
                unique.add(s.getIPAddress());
            }
        }
        return unique.size();
    }

    void printAllHigherThanNum(int num){
        System.out.println("Web logs having status code greater than "+ num);
        for (LogEntry l : webLog){
            if (l.getStatusCode() > num){
                System.out.println(l);
            }
        }
    }

    void uniqueIPVisitsOnDay(String date){
        System.out.println("Unique IPs visits on day " + date);
        ArrayList<String> d = new ArrayList<String>();
        for (LogEntry l : webLog)
        {
            String dt = l.getAccessTime().toString();
            if (dt.substring(4, 10).equals(date)){
                if (!d.contains(l.getIPAddress())){
                    d.add(l.getIPAddress());
                    System.out.println(l);
                }
            }
        }
    }

    int countUniqueIPsInRange(int low, int high){
        System.out.println("No. of unique IPs having status code in range " + low + " to " + high);
        int count = 0;

        ArrayList<String> d = new ArrayList<String>();
        for (LogEntry l : webLog)
        {
            int code = l.getStatusCode();
            if (code >= low && code <= high){
                if (!d.contains(l.getIPAddress())){
                    d.add(l.getIPAddress());
                    count++;
                }
            }
        }
        return count;
    }

    HashMap<String, Integer> countVisitsPerIP(){
        HashMap<String, Integer> map = new HashMap<String, Integer>();

        for (LogEntry l : webLog){
            String ip = l.getIPAddress();
            if(map.containsKey(ip))
            {
                int value = map.get(ip);
                map.remove(ip);
                map.put(ip, value + 1);
            }
            else
            {
                map.put(ip, 1);
            }
        }
        return map;
    }

    int mostVisitsByIP(HashMap<String, Integer> map)
    {
        int max = 0; 
        for (Integer i : map.values())
        {
            if (i > max)
            {
                max = i;
            }
        }
        return max;
    }
    ArrayList<String> IPsMostVisits(HashMap<String, Integer> map){
        ArrayList<String> list = new ArrayList<String>();
        int most = mostVisitsByIP(map);

        for(String s : map.keySet()){
            if (map.get(s) == most)
            {
                list.add(s);
            }
        }
        return list;
    }

    HashMap <String, ArrayList<String> > IPsForDays(){
        HashMap <String, ArrayList<String> > map = new HashMap <String, ArrayList<String> >();
        
        for (LogEntry l : webLog)
        {
            String date = l.getAccessTime().toString().substring(4, 10);
            if (!map.containsKey(date))
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(l.getIPAddress());
                map.put(date, list);
            }
            else{
                ArrayList<String> list = map.get(date);
                map.remove(date);
                list.add(l.getIPAddress());
                map.put(date, list);
            }
        }
        return map;
    }

    String dayWithMostIPVisits(HashMap <String, ArrayList<String> > map)
    {
        int max = 0;
        String m = "";

        for (String s : map.keySet())
        {
            int l = map.get(s).size();
            if(l > max)
            {
                max = l;
                m = s;
            }
        }
        return m;
    }

    ArrayList<String> IPsWithMostVisitsOnDay(HashMap <String, ArrayList<String> > map, String day)
    {
        ArrayList<String> list = map.get(day);
        HashMap <String, Integer> v = new HashMap<String, Integer>();

        for (String ip : list){
            if(v.containsKey(ip))
            {
                int value = v.get(ip);
                v.remove(ip);
                v.put(ip, value + 1);
            }
            else
            {
                v.put(ip, 1);
            }
        }
        
        int most = mostVisitsByIP(v);

        ArrayList<String> result = new ArrayList<String>();
        for (String s : v.keySet()){
            if (v.get(s) == most)
            {
                result.add(s);
            }
        }
        return result;
    }
    void tester(){
        readFile();
        printAll();

        System.out.println("Number of unique IPs " + countUniqueIPs());
        printAllHigherThanNum(300);
        uniqueIPVisitsOnDay("Sep 24");
        System.out.println(countUniqueIPsInRange(200, 299));

        HashMap <String, Integer> map = countVisitsPerIP();
        System.out.println("Count of Visits per IP");
        for (String s : map.keySet()){
            System.out.println(s + " " + map.get(s));
        }

        System.out.println("Most visited IP address " + mostVisitsByIP(map));

        ArrayList<String> list = IPsMostVisits(map);
        System.out.println("IPs with most visits ");
        for (String s : list)
        {
            System.out.println(s);
        }

        HashMap<String, ArrayList<String> > iday = IPsForDays();
        System.out.println("IP's for Days");
        for(String s : iday.keySet())
        {
           ArrayList<String> l = iday.get(s);
            System.out.println("Day " + s);
            for (String st : l)
            {
                System.out.print(st + "\t");
            }
           System.out.println();
        }

        System.out.println("Day with most IP Vists " + dayWithMostIPVisits(iday));

        list = IPsWithMostVisitsOnDay(iday, "Sep 29");
        System.out.println("IPs with most visits on day ");

        for (String s : list)
        {
           System.out.print(s + "\t");
        }
    }


    public static void main(String[] args) {
        LogAnalyser l = new LogAnalyser();
        l.tester();
    }
}