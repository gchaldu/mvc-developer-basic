package com.gchaldu.project.controller;

import com.gchaldu.project.exceptions.ProjectException;
import com.gchaldu.project.exceptions.ProjectNotFoundException;
import com.gchaldu.project.factory.FactoryProject;
import com.gchaldu.project.model.Project;
import com.gchaldu.project.model.ProjectRepository;

import java.util.List;
import java.util.Optional;

public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(String id, String name, String description) throws ProjectException, ProjectNotFoundException {
        //validateProjectData(id,name,description);
        validateString(id, "El id no puede ser nulo o estar vacío");
        validateString(name, "El nombre no puede estar vacío");
        validateString(description, "La descripción no puede estar vacía");
        Project project = FactoryProject.createProject(id,name,description);
        projectRepository.save(project);
    }

    private void validateProjectData(String id, String name, String description) throws ProjectException {
        if(id == null || id.isEmpty()){
            throw new ProjectException("El id no puede estar vacío");
        }

        if(description == null || description.isEmpty()){
            throw new ProjectException("La descripción no puede estar vacía");
        }

        if(name == null || name.isEmpty()){
            throw new ProjectException("El nombre no puede estar vacío");
        }
    }

    public void showProjects() throws ProjectException {
        projectRepository.findAll().stream().forEach( project -> {
            System.out.println("id: " + project.getId());
            System.out.println("Nombre: " + project.getName());
            System.out.println("Descripción: " + project.getDescription());
        });
    }

    public ProjectRepository getProjectRepository() {
        return projectRepository;
    }

    public void validateString( String input, String message) throws ProjectException {
        if(input == null || input.isEmpty()){
            throw new ProjectException(message);
        }
    }

    public Optional<Project> findById(String id) throws ProjectException {
        validateString(id, "El id no puede ser nulo o estar vacío");
        return projectRepository.findById(id);
    }
}
