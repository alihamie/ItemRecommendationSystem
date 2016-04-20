package Screens;
import DataBase.Data;
import DataBase.Movies;
import DataBase.Users;
import Utilities.Algorithms;
import Utilities.Helper;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;


import javax.swing.*;
import java.io.IOException;
import java.util.*;

/**
 * Created by Ali Hamie on 3/10/2016.
 */
public class MainClass extends ApplicationFrame {

    public static final String USER = "1";
    public  static ArrayList<Double> Mae = new ArrayList<>();

    private static Scanner input;

    public MainClass(String applicationTitle , String chartTitle)
    {
        super(applicationTitle);
        JFreeChart lineChart = ChartFactory.createLineChart(
                chartTitle,
                "Items","Prediction",
                createDataset(),
                PlotOrientation.VERTICAL,
                true,true,false);

        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }


    public MainClass(String applicationTitle , String chartTitle,int v )
    {
        super( applicationTitle );
        JFreeChart barChart = ChartFactory.createBarChart(
                chartTitle,
                "Algorithm",
                "Mae",
                createDatasetBar(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel( barChart );
        chartPanel.setPreferredSize(new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
    }
    public static void main(String arg[]){


      //  init_frame();
        //this reads and creates the data set
        Data.createDataSet(input);
        Data.updateRatings();
        //this is used to make the cosine similarity

        Algorithms.GenerateAdjustedItemCosineSimilarity();


        for(String s : Data.users.keySet())
        Algorithms.WeighterSum(s);



       MainClass chart = new MainClass(
                "Actual Rating vs Prediction" ,
                "Adjusted Cosine Similarity");





        chart.pack( );
        RefineryUtilities.centerFrameOnScreen( chart );
        chart.setVisible( true );
        calculateMAE();




        Data.users.get(USER).clearRecommendations();
        Algorithms.GenerateItemCosineSimilarity();
        for(String s : Data.users.keySet())
            Algorithms.WeighterSum(s);



        MainClass chart2 = new MainClass(
                "Actual Rating vs Prediction" ,
                "Cosine Similarity");

        chart2.pack( );
        RefineryUtilities.centerFrameOnScreen( chart2 );
        chart2.setVisible( true );
        calculateMAE();



        Data.users.get(USER).clearRecommendations();
        Algorithms.GenerateItemCorrelationSimilarity();
        for(String s : Data.users.keySet())
            Algorithms.WeighterSum(s);

        MainClass chart3 = new MainClass(
                "Actual Rating vs Prediction" ,
                "Correlation Similarity");

        chart3.pack( );
        RefineryUtilities.centerFrameOnScreen( chart3 );
        chart3.setVisible( true );
        calculateMAE();


        MainClass barchart = new MainClass("MAE of the Algorithms", "Algorithms MAE",3);
                barchart.pack( );
        RefineryUtilities.centerFrameOnScreen( barchart );
        barchart.setVisible( true );
    }





    private DefaultCategoryDataset createDataset( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        ArrayList<String> names = new ArrayList<>();
        String line = "";

        Hashtable<String,Integer> rating = Data.users.get(USER).getRatings();
        Hashtable<String,Double> u =Data.users.get(USER).getRecommended_list();
        for(String i :  rating.keySet() )
        {

            dataset.addValue( rating.get(i), "Rating" , i );
            dataset.addValue( u.get(i), "Prediction" , i );
        }

        return dataset;
    }




    private DefaultCategoryDataset createDatasetBar( ) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        ArrayList<String> names = new ArrayList<>();
        names.add("Adjusted Cosine");
        names.add("Cosine");
        names.add("Correlation");


        for(int i = 0 ; i < Mae.size(); ++i)
        {

            dataset.addValue( Mae.get(i), names.get(i) , names.get(i) );

        }

        return dataset;
    }


    private static  void calculateMAE()
    {

        ArrayList<String> names = new ArrayList<>();
        String line = "";
        double sum = 0;
        double count  =0;
       for(String s : Data.users.keySet())
       {
           Hashtable<String,Integer> rating = Data.users.get(s).getRatings();
           Hashtable<String,Double> u =Data.users.get(s).getRecommended_list();
           for(String v : rating.keySet() )
           {
                ///if(rating.contains(v) && u.containsKey(v)) {
                    sum += Math.abs(rating.get(v) - u.get(v));
                    count++;
               // }
           }

       }

        Mae.add(sum/count);
        System.out.println("MAE = " +sum/count);

    }




}
