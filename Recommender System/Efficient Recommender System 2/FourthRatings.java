/**
 * Write a description of class FourthRatings here.
 * 
 * @author Vishal Jotwani 
 * @version 29/08/2020
 */

import java.util.*;

public class FourthRatings {
    public double getAverageByID(String id, int minimalRaters)
    {
        double avg = 0.0;

        double sum = 0.0;
        int count = 0;

        List <Rater> myRaters = RaterDatabase.getRaters();
        for (Rater r : myRaters)
        {
            if (r.hasRating(id))
            {
                count++;
                sum += r.getRating(id);
            }
        }

        if (count >= minimalRaters){
            avg = sum / count;
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

    private double dotProduct(Rater me, Rater r)
    {
        ArrayList<String> list = me.getItemsRated();

        double sum = 0.0;

        for (String s : list)
        {
            if (r.hasRating(s))
            {
                double a = me.getRating(s) - 5;
                double b = r.getRating(s) - 5;

                sum += a * b;
            }
        }
        return sum;
    }

    public ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> rating = new ArrayList<Rating>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();

        Rater my_rater = RaterDatabase.getRater(id);

        for (Rater r : raters)
        {
            if (!r.getID().equals(id))
            {
                double prod = dotProduct(my_rater, r);
                if (prod > 0)
                {
                    rating.add(new Rating(r.getID(), prod));
                }
            }
        }

        Collections.sort(rating, Collections.reverseOrder());
        return rating;
    }

    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters)
    {
        
        ArrayList<Rating> similarRaters = getSimilarities(id);

        ArrayList<Rating> myRaters = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());

        for (String movie_id : myMovies)
        {
            int i = 0;
            int count = 0;
            double sum = 0;
            double avg = 0.0;
            for (Rating r : similarRaters)
            {
                if ((RaterDatabase.getRater(r.getItem())).hasRating(movie_id))
                {
                    count++;
                    sum += r.getValue() * RaterDatabase.getRater(r.getItem()).getRating(movie_id);
                    //System.out.println("Sum" + sum);
                }

                i++;
                if (i == numSimilarRaters)
                {
                    break;
                }
            }
        
            if (count >= minimalRaters){
                avg = sum / count;
                //System.out.println("avg");
            }

            
            myRaters.add(new Rating(movie_id, avg));
        }

        Collections.sort(myRaters, Collections.reverseOrder());

        return myRaters;
    }

    public ArrayList<Rating> getSimilarRatingsWithFilter(String id, int numSimilarRaters, int minimalRaters,
                                                Filter filterCriteria)
    {
        ArrayList<Rating> similarRaters = getSimilarities(id);
        ArrayList<Rating> myRaters = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(filterCriteria);

        for (String movie_id : myMovies)
        {
            int i = 0;
            int count = 0;
            double sum = 0;
            double avg = 0.0;
            for (Rating r : similarRaters)
            {
                if ((RaterDatabase.getRater(r.getItem())).hasRating(movie_id))
                {
                    count++;
                    sum += r.getValue() * RaterDatabase.getRater(r.getItem()).getRating(movie_id);
                    //System.out.println("Sum" + sum);
                }

                i++;
                if (i == numSimilarRaters)
                {
                    break;
                }
            }
        
            if (count >= minimalRaters){
                avg = sum / count;
                //System.out.println("avg");
            }

            
            myRaters.add(new Rating(movie_id, avg));
        }

        Collections.sort(myRaters, Collections.reverseOrder());

        return myRaters;
    }



}
