package com.gchaldu;

import com.gchaldu.developer.controller.DeveloperController;
import com.gchaldu.developer.exceptions.DeveloperException;
import com.gchaldu.developer.exceptions.DeveloperNotFoundException;
import com.gchaldu.developer.model.DeveloperRepository;
import com.gchaldu.developer.view.DeveloperView;
import com.gchaldu.project.controller.ProjectController;
import com.gchaldu.project.model.Project;
import com.gchaldu.project.model.ProjectRepository;
import com.gchaldu.project.view.ProjectView;

public class Main {
    public static void main(String[] args) {
        DeveloperRepository developerRepository = new DeveloperRepository();
        ProjectRepository projectRepository = new ProjectRepository();
        DeveloperController developerController = new DeveloperController(developerRepository, projectRepository);
        ProjectController projectController = new ProjectController(projectRepository);
        ProjectView projectView = new ProjectView(projectController);
        DeveloperView view = new DeveloperView(developerController, projectController, projectView);

        view.showMenu();

    }
}