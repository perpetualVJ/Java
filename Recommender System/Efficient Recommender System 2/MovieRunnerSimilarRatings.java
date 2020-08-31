/**
 * Write a description of class MovieRunnerSimilarRatings here.
 * 
 * @author Vishal Jotwani 
 * @version 29/08/2020
 */

 import java.util.*;

 public class MovieRunnerSimilarRatings{

    public void printAverageRatings(){

        System.out.println("Average rating of all movies");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        ArrayList<Rating> rating = Fourth.getAverageRating(minimalRaters);

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
        }

    }

    public void printAverageRatingsByYearAfterAndGenre()
    {
        System.out.println("Average rating of all movies filtered by Year After and Genre");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 1;

        AllFilters allFilter = new AllFilters();
        allFilter.addFilter(new YearAfterFilter(1980));
        allFilter.addFilter(new GenreFilter("Romance"));

        ArrayList<Rating> rating = Fourth.getAverageRatingsByFilter(minimalRaters, allFilter);

        Collections.sort(rating);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        for (Rating r : rating)
        {
            System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
            System.out.println("\t " + MovieDatabase.getYear(r.getItem()) + " " +  MovieDatabase.getGenres(r.getItem()));
        }
    }

    public void printSimilarRatings(){
        System.out.println("Average rating of all movies");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 5;

        ArrayList<Rating> rating = Fourth.getSimilarRatings("65", 20, minimalRaters);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        int flag = 0;
        for (Rating r : rating)
        {
            if (flag == 0)
            {
                System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
                flag = 1;
            }
        }

    }

    public void printSimilarRatingsByDirector(){
        System.out.println("Average rating of all movies filtered by director");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 3;
        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";
        ArrayList<Rating> rating = Fourth.getSimilarRatingsWithFilter("1034", 10, minimalRaters, new DirectorsFilter(directors));

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        int flag = 0;
        for (Rating r : rating)
        {
            if (flag == 0)
            {
                System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
                flag = 1;
            }
        }
    }

    public void printSimilarRatingsByGenreAndMinutes(){
        System.out.println("Average rating of all movies filtered by Genre And minutes");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 5;
        String directors = "Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone";

        AllFilters filter = new AllFilters();
        filter.addFilter(new GenreFilter("Adventure"));
        filter.addFilter(new MinutesFilter(100, 200));
        ArrayList<Rating> rating = Fourth.getSimilarRatingsWithFilter("65", 10, minimalRaters, filter);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        int flag = 0;
        for (Rating r : rating)
        {
            if (flag == 0)
            {
                System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
                flag = 1;
            }
        }
    }

    

    public void printSimilarRatingsByYearAfterAndMinutes(){
        System.out.println("Average rating of all movies filtered by Year After And minutes");
        FourthRatings Fourth = new FourthRatings();

        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        System.out.println("No. of raters in file " + RaterDatabase.size());
        System.out.println("No. of movies in database " + MovieDatabase.size());

        int minimalRaters = 5;

        AllFilters filter = new AllFilters();
        filter.addFilter(new YearAfterFilter(2000));
        filter.addFilter(new MinutesFilter(80, 100));
        ArrayList<Rating> rating = Fourth.getSimilarRatingsWithFilter("65", 10, minimalRaters, filter);

        System.out.println("No. of movies rated with minimal raters " + minimalRaters + " " + rating.size());
        int flag = 0;
        for (Rating r : rating)
        {
            if (flag == 0)
            {
                System.out.println(r.getValue() + " " +  MovieDatabase.getTitle(r.getItem()));
                flag = 1;
            }
        }
    }
    public static void main(String[] args) {
        MovieRunnerSimilarRatings m = new MovieRunnerSimilarRatings();
        m.printAverageRatings();
        m.printAverageRatingsByYearAfterAndGenre();
        m.printSimilarRatings();
        m.printSimilarRatingsByDirector();
        m.printSimilarRatingsByGenreAndMinutes();
        m.printSimilarRatingsByYearAfterAndMinutes();
    }
 }