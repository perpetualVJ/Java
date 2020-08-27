
/**
 * Write a description of class FirstRatings here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

public class FirstRatings{
    
    public ArrayList<Movie> loadMovies(String fileName)
    {
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();

        ArrayList<Movie> movies = new ArrayList<Movie>();
        for (CSVRecord record : parser)
        {
            Movie m = new Movie(record.get("id"), record.get("title"), record.get("year"), record.get("genre"),
                        record.get("director"), record.get("country"), record.get("poster"), Integer.parseInt(record.get("minutes")));

            movies.add(m);

        }

        return movies;
    }

    void testLoadMovies()
    {
        System.out.println("Testing loadMovies()");
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");

        System.out.println("No. of movies in the database " + movies.size());
        
        /*
        for (Movie m : movies)
        {
            System.out.println(m);
        }
        */

        // Code to determine no. of movies containing Comedy as Genre
        // Code to determine no. of movies having length greater than 150

        int gcount = 0;
        int mcount = 0;
        for (Movie m : movies)
        {
            if (m.getGenres().contains("Comedy"))
            gcount++;

            if (m.getMinutes() > 150)
            mcount++;
        }

        System.out.println("Movies with Comedy genre " + gcount);
        System.out.println("Movies with length greater than 150 minutes " + mcount);

        // Code to determine no. of directors with maximum movies and max no. of movies any director made.

        HashMap <String, Integer> dcount = new HashMap <String, Integer>();

        for (Movie m : movies)
        {
            String[] s = m.getDirector().split(",");

            for (int i = 0; i < s.length; i++)
            {
                if (!dcount.containsKey(s[i]))
                {
                    dcount.put(s[i], 1);
                }
                else{
                    int key = dcount.get(s[i]);
                    dcount.remove(s[i]);
                    dcount.put(s[i], key + 1);
                }
            }
        }

        int max = 0;
        for (int i : dcount.values())
        {
            if (i > max)
                max = i;
        }

        System.out.println("Maximum number of movies any movie directed " + max);

        System.out.println("Directors: ");
        for (String i : dcount.keySet())
        {
            if (dcount.get(i) == max)
            {
                System.out.println(i);
            }
        }

    }

    public ArrayList<Rater> loadRaters(String fileName)
    {
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();

        ArrayList<Rater> rater = new ArrayList<Rater>();
        
        int i = 1;
        Rater r = null;
        String s = "";
        for (CSVRecord record : parser)
        {
            if (i == 1)
            {
                r = new Rater(record.get("rater_id"));
                r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                i = 0;
                s = record.get("rater_id");
            }
            else{
                if (!s.equals(record.get("rater_id")))
                {
                    rater.add(r);
                    r = new Rater(record.get("rater_id"));
                    r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                    s = record.get("rater_id");
                }
                else if(s.equals(record.get("rater_id")))
                {
                    r.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                
                }
            }
        }
        rater.add(r);
        return rater;
    }

    public void testLoadRaters(){

        System.out.println("Testing loadRaters()");
        ArrayList<Rater> rater = loadRaters("data/ratings.csv");

        System.out.println("Total number of raters are: " + rater.size());

        /*
        System.out.println("Ratings: ");

        
        for (Rater r : rater)
        {
            System.out.println(r.getID() + " " + r.numRatings());
        }
        */

        // Code to find number of ratings of a particula rater_id

        String rater_id = "193";
        for (Rater r : rater)
        {
            if (r.getID().equals(rater_id)){
                System.out.println("Rater ID " + r.getID() + "  Number of ratings " + r.numRatings());
            }
        }

        // Code to find maximum no. of ratings given by any rater_id

        int max = 0;
        for (Rater r : rater)
        {
            if (r.numRatings() > max){
                max = r.numRatings();
            }
        }

        System.out.println("Maximum no. of ratings given by any rater_id " + max);

        for (Rater r : rater)
        {
            if (r.numRatings() == max){
                System.out.println("Rater ID " + r.getID() + "  Number of ratings " + r.numRatings());
            }
        }

        // Code to find the number of ratings a particular movie has

        String movie_id = "1798709";
        
        int count = 0;
        for (Rater r : rater)
        {
            ArrayList<String> s = r.getItemsRated();
            if (s.contains(movie_id))
            {
                count++;
            }
        }

        System.out.println("No. of ratings a movie " + movie_id + " has is " + count);

        // Code to determine how many different movies have been rated by all these raters

        HashSet<String> movieNo = new HashSet<String>();
        for (Rater r : rater)
        {
            ArrayList<String> s = r.getItemsRated();
            for (String i : s)
            {
                movieNo.add(i);
            }
        }

        System.out.println("Different movies have been rated by all these raters are " + movieNo.size());
    }
    public static void main(String[] args)
    {
        FirstRatings ratings = new FirstRatings();
        ratings.testLoadMovies();
        ratings.testLoadRaters();
    }
}