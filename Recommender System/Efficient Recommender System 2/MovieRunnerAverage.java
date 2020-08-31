
/**
 * Write a description of class MovieRunnerAverage here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


import java.util.*;

public class MovieRunnerAverage{
    public void printAverageRatings(){
        SecondRatings second = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");

        System.out.println("No. of movies in file " + second.getMovieSize());
        System.out.println("No. of raters in file " + second.getRaterSize());

        int minimalRaters = 3;

        ArrayList<Rating> rating = second.getAverageRating(minimalRaters);

        Collections.sort(rating);

        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  second.getTitle(r.getItem()));
        }

    }

    public void getAverageRatingOneMovie()
    {
        SecondRatings second = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");

        String movie = "The Godfather";
        String id = second.getID(movie);
        int minimalRaters = 3;
        System.out.println("Average rating of " + movie + " " + second.getAverageByID(id, minimalRaters));
    }

    public static void main(String[] args)
    {
        MovieRunnerAverage avg = new MovieRunnerAverage();
        avg.printAverageRatings();
        avg.getAverageRatingOneMovie();
    }

}