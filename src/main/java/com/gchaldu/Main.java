package com.gchaldu;

import com.gchaldu.developer.controller.DeveloperController;
import com.gchaldu.developer.exceptions.DeveloperException;
import com.gchaldu.developer.exceptions.DeveloperNotFoundException;
import com.gchaldu.developer.model.DeveloperRepository;
import com.gchaldu.developer.view.DeveloperView;

public class Main {
    public static void main(String[] args) {
        DeveloperRepository repository = new DeveloperRepository();
        DeveloperController controller = new DeveloperController(repository);
        DeveloperView view = new DeveloperView(controller);

        view.showMenu();



    }
}