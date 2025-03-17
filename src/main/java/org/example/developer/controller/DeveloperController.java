package org.example.developer.controller;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.model.Developer;
import org.example.developer.model.DeveloperRepository;

import java.util.List;

public class DeveloperController {
    private final DeveloperRepository repository;

    public DeveloperController(DeveloperRepository repository) {
        this.repository = repository;
    }

    public void addDeveloper(String name, String id, Integer age) throws DeveloperNotFoundException, DeveloperException {
        validateDeveloperData(name);
        Developer developer = new Developer(name,id,age);
        repository.save(developer);
    }

    private void validateDeveloperData(String name) throws DeveloperException {
        if(name.trim().isEmpty()) {
            throw new DeveloperException("El nombre no puede estar vacío");
        }
    }

    public List<Developer> findAll() throws DeveloperException {
        return repository.findAll();
    }
}
