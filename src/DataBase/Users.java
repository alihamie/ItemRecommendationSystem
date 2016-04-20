package DataBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Hashtable;

/******************************************************************************
 *  Created by Ali Hamie on 3/10/2016.
 *  Compilation:  javac User.java
 *
 *
 *  Implementation of a the User class
 *
 *  This class contains the information of the User
 *  It has their Id, the movies they have rated
 *  and the ratings for each movie
 *
 *
 ******************************************************************************/
public class Users {


    private String id;
    private Hashtable<String,Integer> ratings = new Hashtable<>();
    private Hashtable<String,Movies> ratedMovies = new Hashtable<>();
    private Hashtable<String,Double> recommended_list = new Hashtable<String,Double>();
    public Users(String id,Movies movie,String rating)
    {
        this.id = id;
        this.ratedMovies.put(movie.getId(),movie);
        this.ratings.put(movie.getId(),Integer.parseInt(rating));
    }


    /******************************************************************************
     *
     *  This function is used to add the movie and as well as add i ts corresponding rating
     *  in the ratings hashtable
     *
     ******************************************************************************/
    public void addMovie(Movies movie,String rating)
    {
        ratedMovies.put(movie.getId(),movie);
        ratings.put(movie.getId(),Integer.parseInt(rating));

    }

    /******************************************************************************
     *
     *  Getter function that returns
     *  all the movies the user has rated
     *
     *****************************************************************************/
    public Hashtable<String,Movies> getRatedMovies()
    {
        return this.ratedMovies;
    }

    //this shows us all the movies the user has rated

    /******************************************************************************
     *
     * A function that prints the information of the user,his ID ,the movies he has rated
     * and their rating.
     *
     *****************************************************************************/
    public void printUser()
    {

        System.out.printf("User %s liked and rated %d movies: \n",this.id,this.getRatedMovies().size());


        for(String s: ratings.keySet())
        {
            System.out.printf("%s rated: %s \n",ratedMovies.get(s).getTitle(),ratings.get(s));
        }

    }

    public void addRecommendation(String id, Double v)
    {
        this.recommended_list.put(id,v);

    }

    public void clearRecommendations()
    {
        this.recommended_list.clear();

    }

    public Hashtable<String,Double> getRecommended_list() {

        return this.recommended_list;
    }
    /******************************************************************************
     *
     *  Returns the hastable of the movie id's and their rating
     *  can be used to get the ratings of an individual movie from the following
     *  user
     *
     *****************************************************************************/
    public Hashtable<String,Integer> getRatings()
    {
        return this.ratings;
    }
    public String getId()
    {
        return this.id;
    }


    /******************************************************************************
     *
     *  Checks if the user has rated a specific movie.
     *
     *****************************************************************************/
    public boolean hasMovie(Movies m)
    {
        if( m.getTitle() == null)
            return false;

        for(String s : ratedMovies.keySet()) {
            if (ratedMovies.get(s).getId().equals(m.getId()))
                return true;
        }

        System.out.println("RETURNING FALSE!!!!!!!!!!!");
        return false;

    }

    public double getAverageRating()
    {
        double sum = 0;

        for(String s : ratings.keySet())
        sum+= ratings.get(s);

        return  sum/ratings.size();
    }

    @Override
    public String toString()
    {
        return id;
    }


}
