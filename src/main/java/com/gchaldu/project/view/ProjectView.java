package com.gchaldu.project.view;

import com.gchaldu.project.controller.ProjectController;
import com.gchaldu.project.exceptions.ProjectException;
import com.gchaldu.project.exceptions.ProjectNotFoundException;

import java.util.Scanner;

public class ProjectView {
    private final ProjectController projectController;
    private Scanner scanner = new Scanner(System.in);

    public ProjectView(ProjectController projectController) {
        this.projectController = projectController;
    }

    public void showMenu(){
        String option;
        while (true){
            System.out.println("1. Agregar Proyecto");
            System.out.println("2. Agregar Proyecto");
            System.out.println("3. salir");
            System.out.println("Ingrese una opción");
            option = scanner.nextLine();
            switch (option){
                case "1" -> addProject();
                case "2" -> showProjectView();
                case "3" -> {return;}
            }
        }
    }

    public void addProject() {
        try {
            System.out.println("Ingrese el id del Proyecto");
            String id = scanner.nextLine();

            System.out.println("Ingrese el nombre del Proyecto");
            String name = scanner.nextLine();

            System.out.println("Ingrese la descripción del Proyecto");
            String description = scanner.nextLine();

            projectController.addProject(id,name,description);
        } catch (ProjectException | ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado, contacte con el administrador " + e.getMessage());
        }
    }

    public void showProjectView(){
        try {
            projectController.showProjects();
        } catch (ProjectException e) {
            System.out.println(e.getMessage());
        }
    }
}
