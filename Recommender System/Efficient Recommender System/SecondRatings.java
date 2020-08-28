
/**
 * Write a description of SecondRatings here.
 * 
 * @author Vishal Jotwani
 * @version 26/ 08/ 2020
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("data/ratedmoviesfull.csv", "data/ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingfile)
    {
        FirstRatings first = new FirstRatings();
        myMovies = first.loadMovies(moviefile);
        myRaters = first.loadRaters(ratingfile);
    }

    public int getMovieSize()
    {
        return myMovies.size();
    }

    public int getRaterSize(){
        return myRaters.size();
    }

    public double getAverageByID(String id, int minimalRaters)
    {
        double avg = 0.0;

        double sum = 0.0;
        int count = 0;

        for (Rater r : myRaters)
        {
            if (r.hasRating(id))
            {
                count++;
                sum += r.getRating(id);
            }

            if (count >= minimalRaters){
                avg = sum / count;
            }
        }

        return avg;

    }

    public ArrayList<Rating> getAverageRating(int minimalRaters)
    {
        ArrayList<Rating> avgRating = new ArrayList<Rating>();
        for (Movie m : myMovies)
        {
            double avg = getAverageByID(m.getID(), minimalRaters);
            if (avg != 0.0)
            {
                avgRating.add(new Rating(m.getID(), avg));
            }
        }
        
        return avgRating;
    }

    public String getTitle(String id)
    {
        for (Movie m : myMovies)
        {
            if (m.getID().equals(id))
                return m.getTitle();
        }

        return "ID not found";
    }

    public String getID(String title)
    {
        for (Movie m : myMovies)
        {
            if (m.getTitle().equals(title))
                return m.getID();
        }

        return "NO SUCH TITLE";
    }
}
