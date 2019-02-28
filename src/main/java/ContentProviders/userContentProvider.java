package ContentProviders;

import Exceptions.ProjectNotFoundException;
import Entities.*;
import Repositories.*;


import java.util.HashMap;


public class userContentProvider {
    public static HashMap<String, String> getHTMLContentsForUser(String uID){
        User u = UserRepo.getUserById(uID);
        if (u != null) {
            return getUserContentMap(u);
        }
        else{
            return null;
        }
    }

    public static HashMap<String,HashMap<String,String>> getHTMLContentsForAllUsers(String currentuserID){
        HashMap<String, User> allUsers = UserRepo.getUserList();
        HashMap<String,HashMap<String,String>> contentMap = new HashMap<String,HashMap<String,String>>();
        for (HashMap.Entry<String, User> entry : allUsers.entrySet()) {
            String uid = entry.getKey();
            if(uid != currentuserID){

                    contentMap.put(uid, getShortUserContentMap(entry.getValue()));
            }

        }
        return contentMap;
    }

    private static HashMap<String, String> getUserContentMap(User u){
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("id", u.getUsername());
        contentMap.put("first name", u.getFirstName());
        contentMap.put("last name", u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        contentMap.put("bio", u.getBio());
        return contentMap;

    }

    private static HashMap<String, String> getShortUserContentMap(User u){
        HashMap<String, String> contentMap = new HashMap<>();
        contentMap.put("id", u.getUsername());
        contentMap.put("name", u.getFirstName() + " " +  u.getLastName());
        contentMap.put("jobTitle", u.getJobTitle());
        return contentMap;

    }
}
