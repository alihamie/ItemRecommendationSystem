package DataBase;

import Utilities.Vector;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by Allouch on 3/10/2016.
 * This is a movie Class
 * It has all the relevant information of the movies
 * we have the title the movie id and a list of the users
 * who rated the movie
 *
 */
public class Movies {

    private String id;
    private String title;

    private Hashtable<String,Users> likedUsers = new Hashtable<String,Users>();

    Movies(String id,String title)
    {
        this.id = id;
        this.title = title;

    }



    public String getTitle()
    {
        return this.title;

    }


    public String getId()
    {

        return this.id;
    }

    public void printMovie()
    {
       System.out.println(title);

    }

    public void addUser(Users u)
    {

        this.likedUsers.put(u.getId(),u);
    }

    //this is used to get All the users who liked this specific movie
    public Hashtable getLikedUsers()
    {

        return this.likedUsers;
    }

    /**
     * Created by Allouch on 3/10/2016.
     * The following funtion returns a vector of the
     * every rating of the item.
     */
    public Vector getRatingsVector()
    {

        return  new Vector(getLikedUsers(),id);

    }

    /**
     * Created by Allouch on 3/10/2016.
     * The following funtion returns a vector of the
     * ratings of specified users.
     */
    public Vector getRatingsVector(Hashtable<String,Users> users)
    {

        return  new Vector(users,id);

    }

    @Override
    public String toString()
    {
        return this.title;

    }



}
