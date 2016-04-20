package Utilities;

import DataBase.Data;
import DataBase.Movies;
import DataBase.Users;

import java.util.ArrayList;
import java.util.Hashtable;

/**
 * Created by Allouch on 4/9/2016.
 */
public class Algorithms {


    public static double cosineSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double normA = 0.0;
        double normB = 0.0;
        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += Math.pow(vectorA[i], 2);
            normB += Math.pow(vectorB[i], 2);
        }
        double result = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));

        if(result < 0|| Double.isNaN(result))
            return 0;
        else return result;
    }


    public static double correlationSimilarity(double[] vectorA, double[] vectorB) {
        double dotProduct = 0.0;
        double avgItem1 = getAverageRating(vectorA);
        double avgItem2 = getAverageRating(vectorB);

        double normA = 0.0;
        double normB = 0.0;


        for (int i = 0; i < vectorA.length; i++) {

            dotProduct += ( (vectorA[i] - avgItem1) * (vectorB[i] - avgItem2 ));
            normA += Math.pow(vectorA[i] - avgItem1,2);
            normB += Math.pow(vectorB[i] - avgItem2,2);

        }
        double result = dotProduct / (Math.sqrt(normA) * Math.sqrt(normB));

        if(result < 0 || Double.isNaN(result))
         return 0;
        else return result;
    }


    public static double adjustedCosineSimilarity(Hashtable<String,Users> users,double vectorA[],double vectorB[])
    {
        double dotProduct = 0.0;

        double normA = 0.0;
        double normB = 0.0;

            int i = 0;
            for (String s : users.keySet()) {

                double avgRating = users.get(s).getAverageRating();
                dotProduct += ((vectorA[i] - avgRating) * (vectorB[i] - avgRating));
                normA += Math.pow(vectorA[i] - avgRating, 2);
                normB += Math.pow(vectorB[i] - avgRating, 2);

                i++;
        }

        double result = (dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));
        if(result < 0|| Double.isNaN(result))
            return 0;
        else return result;


    }


    public static double getAverageRating(double[] ratings)
    {

        double sum = 0;

        for(int i = 0; i < ratings.length; ++i)
        {
            sum+=ratings[i];

        }

        return  sum/ratings.length;
    }


    public static void WeighterSum(String userID)
    {
        Users u = Data.users.get(userID);
        Hashtable<String,Integer> ratedItems = u.getRatings();
        double userSum = 0;
        double itemSum = 1;
        double result = 0;

        for(String i:Data.movies.keySet())
        {

            for(String j : ratedItems.keySet())
            {
                double similar = Data.itemSimilairty[Integer.parseInt(j)][Integer.parseInt(Data.movies.get(i).getId())];
               // double similar = Data.itemSimilairty[Integer.parseInt(Data.movies.get(i).getId())][Integer.parseInt(j)];

                userSum += similar * ratedItems.get(j);
                itemSum+= Math.abs( similar ) ;
            }
           // if(itemSum !=0)
           result = userSum/itemSum;
           // else result = 0;


          // System.out.println(Data.movies.get(i).getTitle()+result);
            userSum = 0;
            itemSum = 1;


            Data.users.get(userID).addRecommendation(Data.movies.get(i).getId(),result);
        }

       // System.out.println(Data.movies.get(recommendedId).getTitle());


    }



    public static void GenerateItemCosineSimilarity()
    {
        double res = 0;
        for(String i : Data.movies.keySet()) {

            for (String j : Data.movies.keySet()) {

                Hashtable<String,Users> itemi =  Data.movies.get(i).getLikedUsers();
                Hashtable<String,Users> itemj =  Data.movies.get(j).getLikedUsers();

                Hashtable<String,Users> union = Helper.getCommonUsers(itemi,itemj);
                double vectori [] = Data.movies.get(i).getRatingsVector(union).getArray();
                double vectorj [] = Data.movies.get(j).getRatingsVector(union).getArray();

                if(!union.isEmpty())
                res = cosineSimilarity(vectori,vectorj);
                else res = 0;

                Data.itemSimilairty[Integer.parseInt(i)][Integer.parseInt(j)] =  res;
                Data.itemSimilairty[Integer.parseInt(j)][Integer.parseInt(i)] =  res;
            }

        }

    }


    public static void GenerateItemCorrelationSimilarity()
    {
        double res = 0;
        for(String i : Data.movies.keySet()) {

            for (String j : Data.movies.keySet()) {

                Hashtable<String,Users> itemi =  Data.movies.get(i).getLikedUsers();
                Hashtable<String,Users> itemj =  Data.movies.get(j).getLikedUsers();

                Hashtable<String,Users> union = Helper.getCommonUsers(itemi,itemj);
                double vectori [] = Data.movies.get(i).getRatingsVector(union).getArray();
                double vectorj [] = Data.movies.get(j).getRatingsVector(union).getArray();

                if(!union.isEmpty())
                    res = correlationSimilarity(vectori,vectorj);
                else res = 0;

                Data.itemSimilairty[Integer.parseInt(i)][Integer.parseInt(j)] =  res;
                Data.itemSimilairty[Integer.parseInt(j)][Integer.parseInt(i)] =  res;
            }

        }

    }

    public static void GenerateAdjustedItemCosineSimilarity()
    {
        double res = 0;
        for(String i : Data.movies.keySet()) {

            for (String j : Data.movies.keySet()) {

                Hashtable<String,Users> itemi =  Data.movies.get(i).getLikedUsers();
                Hashtable<String,Users> itemj =  Data.movies.get(j).getLikedUsers();

                Hashtable<String,Users> union = Helper.getCommonUsers(itemi,itemj);
                double vectori [] = Data.movies.get(i).getRatingsVector(union).getArray();
                double vectorj [] = Data.movies.get(j).getRatingsVector(union).getArray();

                if(!union.isEmpty())
                    res = adjustedCosineSimilarity(union,vectori,vectorj);
                else res = 0;

                Data.itemSimilairty[Integer.parseInt(i)][Integer.parseInt(j)] =  res;
                Data.itemSimilairty[Integer.parseInt(j)][Integer.parseInt(i)] =  res;

            }

        }

    }

}
