package Screens;

import DataBase.Data;
import DataBase.Movies;
import DataBase.Users;
import Utilities.Algorithms;
import Utilities.Helper;


import javax.swing.*;
import java.util.*;

/**
 * Created by Ali Hamie on 3/10/2016.
 */
public class MainClass {



    private static Scanner input;

    public static void main(String arg[]){


        init_frame();
        //this reads and creates the data set
        Data.createDataSet(input);
        Data.updateRatings();
        //this is used to make the cosine similarity
       //Algorithms.GenerateItemCosineSimilarity();
        Hashtable<String,Users> first =  Data.movies.get("4").getLikedUsers();
        Hashtable<String,Users> second =  Data.movies.get("3").getLikedUsers();


       Hashtable<String,Users> union = Helper.getCommonUsers(first,second);



       // System.out.println(Data.itemSimilairty[2][1]);
        System.out.println(Algorithms.cosineSimilarity(Data.movies.get("3").getRatingsVector(union).getArray(), Data.movies.get("4").getRatingsVector(union).getArray()));
        System.out.println(Algorithms.correlationSimilarity(Data.movies.get("3").getRatingsVector(union).getArray(), Data.movies.get("4").getRatingsVector(union).getArray()));
        System.out.println(Correlation(Data.movies.get("3").getRatingsVector(union).getArray(), Data.movies.get("4").getRatingsVector(union).getArray()));


        System.out.println(Algorithms.adjustedCosineSimilarity(union,Data.movies.get("3").getRatingsVector(union).getArray(), Data.movies.get("4").getRatingsVector(union).getArray()));



    }


    public static void init_frame()
    {
        JFrame frame = new JFrame("Guess Game");
        ///GuessPanel panel = new GuessPanel();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,300);
      //  frame.add(panel);
        frame.setVisible( true );
    }


    /*****
    Algorithm obtained online to check if our correlation algorithm matches it.
    ****/


    public static double Correlation(double[] xs, double[] ys) {
        //TODO: check here that arrays are not null, of the same length etc

        double sx = 0.0;
        double sy = 0.0;
        double sxx = 0.0;
        double syy = 0.0;
        double sxy = 0.0;

        int n = xs.length;

        for(int i = 0; i < n; ++i) {
            double x = xs[i];
            double y = ys[i];

            sx += x;
            sy += y;
            sxx += x * x;
            syy += y * y;
            sxy += x * y;
        }

        // covariation
        double cov = sxy / n - sx * sy / n / n;
        // standard error of x
        double sigmax = Math.sqrt(sxx / n -  sx * sx / n / n);
        // standard error of y
        double sigmay = Math.sqrt(syy / n -  sy * sy / n / n);

        // correlation is just a normalized covariation
        return cov / sigmax / sigmay;
    }

}
