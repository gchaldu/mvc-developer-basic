package com.gchaldu.developer.controller;

import com.gchaldu.developer.exceptions.DeveloperException;
import com.gchaldu.developer.exceptions.DeveloperNotFoundException;
import com.gchaldu.developer.model.Developer;
import com.gchaldu.developer.model.DeveloperRepository;
import com.gchaldu.developer.view.DeveloperView;
import com.gchaldu.project.model.Project;
import com.gchaldu.project.model.ProjectRepository;

import java.util.List;

public class DeveloperController {
    private final DeveloperRepository developerRepository;
    private final ProjectRepository projectRepository;

    public DeveloperController(DeveloperRepository developerRepository,
                               ProjectRepository projectRepository) {
        this.developerRepository = developerRepository;
        this.projectRepository = projectRepository;
    }

    public void addDeveloper(String name, String id, Integer age, String idProject, String nameProject,
                             String descriptionProject) throws DeveloperNotFoundException, DeveloperException {
        validateDeveloperData(name);
        validateProjectData(nameProject);

        Project project = new Project(idProject, nameProject, descriptionProject);
        Developer developer = new Developer(name,id,age,project);

        developerRepository.save(developer);
    }

    private void validateDeveloperData(String name) throws DeveloperException {
        if(name.trim().isEmpty()) {
            throw new DeveloperException("El nombre no puede estar vacío");
        }
    }

    private void validateProjectData(String name) throws DeveloperException {
        if(name.trim().isEmpty()) {
            throw new DeveloperException("El nombre no puede estar vacío");
        }
    }

    public List<Developer> findAll() throws DeveloperException {
        return developerRepository.findAll();
    }
}
