package com.gchaldu.project.factory;

import com.gchaldu.project.model.Project;

public class FactoryProject {
    public static Project createProject(String id, String name, String description){
        return new Project(id, name, description);
    }
}
