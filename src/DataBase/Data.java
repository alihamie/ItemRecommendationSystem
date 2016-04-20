package DataBase;

import javax.jws.soap.SOAPBinding;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/**
 * Created by Ali Hamie on 3/10/2016.
 * This is a singlelton class where all the data is stored
 * This class contains every information we need in our program
 *
 * @movies is a hashtable that has all the movies and can get the movie
 * by using the movie ID.
 *
 * @users is another hashtable that stores the users and can be accesed by
 * using the user ID.
 *
 * @instance is the object instance of the class used to access the information.
 */
public class Data {


    public static HashMap<String,Movies> movies = new HashMap<String,Movies>();
   // public  static  Movies[] movies = new Movies[1689];
    public  static HashMap<String,Users> users = new HashMap<String,Users>();


    public static double[][] itemSimilairty;
    public static ArrayList<ArrayList<Double>> itemSimilairty2 = new ArrayList< ArrayList<Double> > ();
    public static Data instance = new Data();

    private Data()
    {

    }

    /******************************************************************************
     *
     * This function works as an init funtion it reads all the data from the two files
     * and stores them in our data structures.
     * it opens the u.data file that has ratings and user data and the
     * u.item file that has the movies and their attributes
     *
     ******************************************************************************/
    public static void createDataSet(Scanner input)  {


        ///gets the movie data
        try {
            input = new Scanner(new BufferedReader(new FileReader("u.item") ));
        }catch (FileNotFoundException e)
        {
            System.out.printf("File not Found\n");

        }


        while (input.hasNextLine())
        {

            String []attributes = input.nextLine().split("\\|");
            addMovieItem(attributes[0],attributes[1]);

        }
        input.close();

        //gets the user data
        try {
            input = new Scanner(new BufferedReader(new FileReader("u.data") ));
        }catch (FileNotFoundException e)
        {
            System.out.printf("File not Found\n");

        }

        while (input.hasNext())
        {
            String [] attributes = input.nextLine().split("\\s+");

            //adds a user
            addUser(attributes[0],
                    instance.movies.get(attributes[1])
                    ,attributes[2]);


        }

        input.close();
        //dynamically initialize the size of item similairty matrix
        itemSimilairty  = new double[movies.size()+1][movies.size()+1];

    }



    /******************************************************************************
     *
     * adds a new Item to our item hashmap
     *
     ******************************************************************************/
    private static void addMovieItem(String id,String title)
    {

        instance.movies.put(id, new Movies(id,title));
    }


    /******************************************************************************
     *
     * adds a new user to our user hashmap,however it also checks if the user has been
     * added then it adds the rating and the movie.
     * and then it updates the movie hashmap by adding the user that has rated it.
     *
     ******************************************************************************/
    private static void addUser(String id,Movies movie,String rating)
    {

        if(instance.users.get(id)  == null)
        {
            instance.users.put(id,new Users(id,movie,rating) );
            instance.movies.get(movie.getId()).addUser(new Users(id,movie,rating));
        }
        else {
            instance.users.get(id).addMovie(movie, rating);
            instance.movies.get(movie.getId()).addUser(instance.users.get(id) );
        }


    }

    /******************************************************************************
     *
     * After all the users have been read and their ratings for every item has been
     * read we need to update it in the data structures we have so  thay they reflect
     * the current database.
     *
     ******************************************************************************/
   public static void updateRatings()
    {


        for(String i : Data.movies.keySet())
        {
            Hashtable<String,Users> us =  Data.movies.get(i).getLikedUsers();
            for(String j : us.keySet())
            {

                Data.movies.get(i).addUser( Data.users.get(us.get(j).getId()));
            }

        }

    }

}
