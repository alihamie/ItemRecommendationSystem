package Screens;

import DataBase.Data;
import DataBase.Movies;
import DataBase.Users;


import java.util.*;

/**
 * Created by Ali Hamie on 3/10/2016.
 */
public class MainClass {



    private static Scanner input;

    public static void main(String arg[]){



        //this reads and creates the data set
        Data.createDataSet(input);



        System.out.println( Data.movies.get("1").getRatingsVector().toString());
        System.out.println( Data.movies.get("1").getLikedUsers().size());




    }




}
