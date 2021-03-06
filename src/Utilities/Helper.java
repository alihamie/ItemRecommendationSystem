package Utilities;

import DataBase.Users;

import java.util.Hashtable;
import java.util.Set;

/**
 * Created by Ali Hamie on 4/7/2016.
 * this class is an utility class
 * with multiple helper methods
 */
public abstract class Helper {



    public static Hashtable<String ,Users> getCommonUsers
            (Hashtable<String,Users> users,Hashtable<String,Users> users1)
    {
        Hashtable<String ,Users> common_users = new Hashtable<>();

        Set<String> s;

        if (users1.size() > users.size())
            s = users1.keySet();
        else s = users.keySet();

        for(String key : s)
        {
            if(users.containsKey(key) && users1.containsKey(key))
                common_users.put(key,users1.get(key));
        }

        return common_users;
    }


    public static void printUsers(Hashtable<String,Users> users)
    {

        for(String s: users.keySet())
        {
            System.out.print(users.get(s).getId()+" ");

        }

        System.out.println();


    }

}
