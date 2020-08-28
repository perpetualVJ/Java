
/**
 * Write a description of ThirdRatings here.
 * 
 * @author Vishal Jotwani
 * @version 26/ 08/ 2020
 */

import java.util.*;

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("data/ratings.csv");
    }
    
    public ThirdRatings(String ratingfile)
    {
        FirstRatings first = new FirstRatings();
        myRaters = first.loadRaters(ratingfile);
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

        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for (String m : myMovies)
        {
            double avg = getAverageByID(m, minimalRaters);
            if (avg != 0.0)
            {
                avgRating.add(new Rating(m, avg));
            }
        }
        
        return avgRating;
    }

    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria)
    {
        
        ArrayList<Rating> avgRating = new ArrayList<Rating>();

        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);
        for (String m : myMovies)
        {
            double avg = getAverageByID(m, minimalRaters);
            if (avg != 0.0)
            {
                avgRating.add(new Rating(m, avg));
            }
        }
        
        return avgRating;
        
    }
}
