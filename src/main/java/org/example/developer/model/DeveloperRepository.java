package org.example.developer.model;

import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class DeveloperRepository {
    List<Developer> developers = new ArrayList<>();

    public void save(Developer developer) throws DeveloperNotFoundException {
        if(developers.contains(developer)){
            throw new DeveloperNotFoundException("El desarrollador ya existe");
        }
        developers.add(developer);
    }

    public int findIndexById(String id){
        for (int i=0; i<developers.size(); i++){
            if(developers.get(i).getId().equals(id)){
                return i;
            }
        }
        return -1;
    }

    public void remove(String id){
        int index = findIndexById(id);
        developers.remove(index);
    }
    public void remove(Developer developer){
        developers.remove(developer);
    }

    public void update(String id, Developer updateDeveloper){
        int index = findIndexById(id);
        developers.set(index, updateDeveloper);
    }

    public List<Developer> findAll() throws DeveloperException {
        if(developers.isEmpty()){
            throw new DeveloperException("La lista esta vacía");
        }
        return developers;
    }
}
