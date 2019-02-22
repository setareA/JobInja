import Commands.*;
import Repositories.UserRepo;
import Skill.*;
import User.*;
import com.google.gson.JsonElement;
import javafx.util.Pair;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean isFinished = false;

    public static void main(String[] args) throws Exception {
        addProjects();
        addSkills();
        addUser();

        ReflectionServer server = new ReflectionServer();
        server.startServer();

        while (!isFinished) {
            Pair<String, String> commandParts = getCommandParts();
            String commandName = commandParts.getKey();
            String commandData = commandParts.getValue();
            Command cmd;

            switch (commandName) {
                case "register":
                    cmd = MyJsonParser.parseUserInfo(commandData);
                    break;
                case "bid":
                    cmd =  MyJsonParser.parseBidInfo(commandData);
                    break;
                case "auction":
                    cmd = MyJsonParser.parseAuctionInfo(commandData);
                    isFinished = true;
                    break;
                default :
                    cmd = null;
                    break;
            }
            if(cmd != null)
                cmd.execute();
        }
    }

    private static Pair<String, String> getCommandParts() {
        String command = scanner.nextLine();
        int spaceIndex = command.indexOf(" ");
        return new Pair<> (command.substring(0, spaceIndex), command.substring(spaceIndex));
    }

    private static void addProjects(){
        HttpConnection connection = new HttpConnection();
        try {
            ArrayList<JsonElement> projectlist =  connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/project"));
            ArrayList<Command> command_list = MyJsonParser.parseProjectList(projectlist);
            for (Command command : command_list) {
                command.execute();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void addSkills(){
        HttpConnection connection = new HttpConnection();
        try {
            ArrayList<JsonElement> skillList =  connection.httpGet(new URL("http://142.93.134.194:8000/joboonja/skill"));
            ArrayList<Command> command_list = MyJsonParser.parseSkillList(skillList);
            for (Command command : command_list) {
                command.execute();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addUser(){
        String bio = "khodabiamorz mikhast kheili kara bokone vali pool nadasht";
        Skill s = new  Skill("HTML", 5);
        Skill s1 = new Skill("Javascript", 4);
        Skill s2 = new Skill("C++", 2);
        Skill s3 = new Skill("Java", 3);
        HashMap<String, Skill> map = new HashMap<String, Skill>();
        map.put("HTML", s);
        map.put("Javascript", s1);
        map.put("C++", s2);
        map.put("Java", s3);
        User u = new User("1", "Ali", "Sharifzadeh","Web Programmer", " ",map, bio);
        UserRepo.addUser(u);
    }
}

