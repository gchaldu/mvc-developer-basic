package com.gchaldu.project.model;

import com.gchaldu.project.exceptions.ProjectException;
import com.gchaldu.project.exceptions.ProjectNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class ProjectRepository {
    private final List<Project> projects;

    public ProjectRepository() {
        projects = new ArrayList<>();
    }

    public void save(Project project) throws ProjectNotFoundException{
        if( project==null || projects.contains(project)){
            throw new ProjectNotFoundException("El proyecto es duplicado");
        }
        projects.add(project);
    }

    public List<Project> findAll() throws ProjectException {
        if (projects.isEmpty()) {
            throw new ProjectException("La lista está vacía");
        }
        return projects;
    }
}
