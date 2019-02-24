package Repositories;
import Project.*;
import Skill.*;
import Exceptions.*;

import java.util.HashMap;
import java.util.*;

public class ProjectRepo {
    private static ProjectRepo ourInstance = new ProjectRepo();
    private static HashMap<String, Project> projectList = new HashMap<String, Project>();

    public static ProjectRepo getInstance() {
        return ourInstance;
    }

    private ProjectRepo() {
    }

    public static void addProject(String id, String title, String description, String imageURL, int budget, long deadline, HashMap<String, Skill> skills) {
        Project newProject = new Project(id, title, description, imageURL, budget, deadline, skills);
        projectList.put(title, newProject);
    }

    public static Project findItemInProjectList(String title) {
        return projectList.get(title);
    }

    public static Project getProjectById(String id) throws ProjectNotFoundException {
        Iterator it = projectList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            Project p = (Project) pair.getValue();
            if(p.getId().equals(id)){
                return p;
            }
            //it.remove(); // avoids a ConcurrentModificationException
        }
        throw new ProjectNotFoundException("Project Not Found");
    }
    public static HashMap<String,Project> getAllProjects(){
        return new HashMap<>(projectList);
    }


}
