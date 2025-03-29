package com.gchaldu.project.view;

import com.gchaldu.project.controller.ProjectController;
import com.gchaldu.project.exceptions.ProjectException;
import com.gchaldu.project.exceptions.ProjectNotFoundException;
import com.gchaldu.project.model.Project;

import java.util.Optional;
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
            System.out.println("\u0031\uFE0F\u20E3 Agregar Proyecto");
            System.out.println("\u0032\uFE0F\u20E3 Agregar Proyecto");
            System.out.println("\u0033\uFE0F\u20E3 salir");
            System.out.println("\u1F522 Ingrese una opción");
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
            System.out.println("\uD83C\uDD94 Ingrese el id del Proyecto");
            String id = scanner.nextLine();

            System.out.println("\uD83D\uDCC2 Ingrese el nombre del Proyecto");
            String name = scanner.nextLine();

            System.out.println("\uD83D\uDCDD Ingrese la descripción del Proyecto");
            String description = scanner.nextLine();

            projectController.addProject(id,name,description);
        } catch (ProjectException | ProjectNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado, contacte con el administrador " + e.getMessage());
        }
    }

    private void addProjectView() {
        try {
            String idProject = readNonEmptyString("\uD83C\uDD94 Ingrese el ID del Proyecto:");
            String nameProject = readNonEmptyString("\uD83D\uDCC2 Ingrese el nombre del Proyecto:");
            String descriptionProject = readNonEmptyString("\uD83D\uDCDD Ingrese la descripción del Proyecto:");

            projectController.addProject(idProject, nameProject, descriptionProject);
            System.out.println("✅ Proyecto agregado con éxito.");
        } catch (ProjectException | ProjectNotFoundException e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        } catch ( Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    private String readNonEmptyString(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("⚠️ El valor no puede estar vacío.");
            }
        } while (input.isEmpty());
        return input;
    }

    public Project addOrFindProject() throws ProjectException, ProjectNotFoundException {
        String idProject = readNonEmptyString("\uD83C\uDD94 Ingrese el ID del Proyecto:");
        Optional<Project> project = projectController.findById(idProject);

        if (project.isPresent()) {
            System.out.println("✅ Proyecto encontrado y agregado con éxito.");
            return project.get();
        } else {
            System.out.println("⚠️ Proyecto no encontrado. Ingrese los datos para crearlo.");
            String nameProject = readNonEmptyString("\uD83D\uDCC2 Ingrese el nombre del Proyecto:");
            String descriptionProject = readNonEmptyString("\uD83D\uDCDDIngrese la descripción del Proyecto:");
            projectController.addProject(idProject, nameProject, descriptionProject);
            return new Project(idProject, nameProject, descriptionProject);
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
