package com.gchaldu.project.controller;

import com.gchaldu.project.exceptions.ProjectException;
import com.gchaldu.project.exceptions.ProjectNotFoundException;
import com.gchaldu.project.factory.FactoryProject;
import com.gchaldu.project.model.Project;
import com.gchaldu.project.model.ProjectRepository;

import java.util.List;

public class ProjectController {
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public void addProject(String id, String name, String description) throws ProjectException, ProjectNotFoundException {
        validateProjectData(id,name,description);
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
}
