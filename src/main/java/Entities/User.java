package Entities;

import java.util.HashMap;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String jobTitle;
    private String profilePictureURL;
    private HashMap<String, Skill> skills;
    private String bio;

    public User(String username, String firstName, String lastName, String jobTitle, String profilePictureURL, HashMap<String, Skill> skills, String bio) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.jobTitle = jobTitle;
        this.profilePictureURL = profilePictureURL;
        this.skills = skills;
        this.bio = bio;
    }

    public User(String username, HashMap<String, Skill> skills) {
        this.username = username;
        this.skills = new HashMap<String, Skill>(skills);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getProfilePictureURL() {
        return profilePictureURL;
    }

    public void setProfilePictureURL(String profilePictureURL) {
        this.profilePictureURL = profilePictureURL;
    }

    public void setSkills(HashMap<String, Skill> skills) {
        this.skills = skills;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void addSkill(String name, Skill s){
        this.skills.put(name, s);
    }

    public HashMap<String, Skill> getSkills(){
        return this.skills;
    }

    public String getUsername(){
        return this.username;
    }

    public boolean hasRequiredSkills(HashMap<String, Skill> requiredSkills){
        for (String name: requiredSkills.keySet()) {
            if (skills.containsKey(name)) {
                if (skills.get(name).getPoint() < requiredSkills.get(name).getPoint()) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public void delSkill(String name){
        this.skills.remove(name);
    }

    }


