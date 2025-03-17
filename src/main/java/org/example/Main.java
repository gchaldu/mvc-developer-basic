package org.example;

import org.example.developer.controller.DeveloperController;
import org.example.developer.exceptions.DeveloperException;
import org.example.developer.exceptions.DeveloperNotFoundException;
import org.example.developer.model.Developer;
import org.example.developer.model.DeveloperRepository;
import org.example.developer.view.DeveloperView;

import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Developer developer = new Developer("Mauro", "1", 23);
        Developer developer1 = new Developer("Mauro", "1", 23);

        DeveloperRepository repository = new DeveloperRepository();

        /*try {
            repository.save(developer);
            List<Developer> developers = repository.findAll();

            System.out.println("Lista uno");

            developers.forEach(System.out::println);

            System.out.println("Lista dos");

            repository.save(developer1);

            developers.forEach(System.out::println);
        } catch (DeveloperNotFoundException | DeveloperException e) {
            System.out.println(e.getMessage());
        }*/

        DeveloperController controller = new DeveloperController(repository);
        DeveloperView view = new DeveloperView(controller);
        view.addDeveloperView();
        view.findAllView();
        view.addDeveloperView();
        view.findAllView();




    }
}