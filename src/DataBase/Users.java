package DataBase;

import java.util.ArrayList;
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
    private ArrayList<Movies> ratedMovies = new ArrayList<>();


    public Users(String id,Movies movie,String rating)
    {
        this.id = id;
        this.ratedMovies.add(movie);
        this.ratings.put(movie.getId(),Integer.parseInt(rating));
    }


    /******************************************************************************
     *
     *  This function is used to add the movie and as well as add its corresponding rating
     *  in the ratings hashtable
     *
     ******************************************************************************/
    public void addMovie(Movies movie,String rating)
    {
        ratedMovies.add(movie);
        ratings.put(movie.getId(),Integer.parseInt(rating));

    }

    /******************************************************************************
     *
     *  Getter function that returns
     *  all the movies the user has rated
     *
     *****************************************************************************/
    public ArrayList<Movies> getRatedMovies()
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

        System.out.printf("User %s liked and rated the following: \n",this.id);

        for(int i = 0; i <ratedMovies.size(); ++i)
        {
            System.out.printf("%s rated: %s \n",ratedMovies.get(i).getTitle(),ratings.get(i));

        }

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

        for(Movies n : ratedMovies) {
            if (n.getId().equals(m.getId()))
                return true;
        }

        System.out.println("RETURNING FALSE!!!!!!!!!!!");
        return false;

    }

    @Override
    public String toString()
    {
        return id;
    }
}
