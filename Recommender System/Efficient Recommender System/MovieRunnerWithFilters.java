
/**
 * Write a description of class MovieRunnerWithFilters here.
 * 
 * @author Vishal Jotwani 
 * @version 27/08/2020
 */


import java.util.*;

public class MovieRunnerWithFilters{
    public void printAverageRatings(){

        System.out.println("Average rating of all movies");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = third.getAverageRating(minimalRaters);

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
        }

    }

    public void printAverageRatingsByYear(){

        System.out.println("Average rating of all movies filtered by year");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, new YearAfterFilter(2000));

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
        }
    }

    public void printAverageRatingsByGenre(){

        System.out.println("Average rating of all movies filtered by Genre");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, new GenreFilter("Crime"));

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printAverageRatingsByMinutes(){

        System.out.println("Average rating of all movies filtered by Minutes");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, new MinutesFilter(110, 170));

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getMinutes(r.getItem()) + " minutes");
        }
    }

    public void printAverageRatingsByDirectors(){

        System.out.println("Average rating of all movies filtered by Directors");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze"));

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getDirector(r.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre()
    {
        System.out.println("Average rating of all movies filtered by Year After and Genre");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1980));
        allFilter.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, allFilter);

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getYear(r.getItem()) + " " +  MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printAverageRatingsByDirectorsAndMinutes()
    {
        System.out.println("Average rating of all movies filtered by Directors and Minutes");
        ThirdRatings third = new ThirdRatings("data/ratings_short.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");

        System.out.println("No. of raters in file " + third.getRaterSize());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new MinutesFilter(30, 170));
        allFilter.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));

        ArrayList<Rating> rating = third.getAverageRatingsByFilter(minimalRaters, allFilter);

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
    
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getDirector(r.getItem()) + " " +  MovieDatabase.getMinutes(r.getItem()));
        }
    }
    public static void main(String[] args)
    {
        MovieRunnerWithFilters avg = new MovieRunnerWithFilters();
        avg.printAverageRatings();
        avg.printAverageRatingsByYear();
        avg.printAverageRatingsByGenre();
        avg.printAverageRatingsByMinutes();
        avg.printAverageRatingsByDirectors();
        avg.printAverageRatingsByYearAfterAndGenre();
        avg.printAverageRatingsByDirectorsAndMinutes();
    }

}