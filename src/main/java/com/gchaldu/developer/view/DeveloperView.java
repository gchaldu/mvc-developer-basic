package com.gchaldu.developer.view;

import com.gchaldu.developer.controller.DeveloperController;
import com.gchaldu.developer.exceptions.DeveloperException;
import com.gchaldu.developer.exceptions.DeveloperNotFoundException;
import com.gchaldu.developer.model.Developer;

import java.util.List;
import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController controller;
    private final Scanner scanner;

    public DeveloperView(DeveloperController controller) {
        this.controller = controller;
        scanner = new Scanner(System.in);
    }

    public void showMenu() {
        int option;
        while (true){
            System.out.println("Ingrese un opción");
            System.out.println();
            System.out.println("1. Agregar Desarrollador");
            System.out.println("2. Mostrar Desarrollador");
            System.out.println("3. Salir");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 1 -> addDeveloperView();
                case 2 -> findAllView();
                case 3 -> {
                    return;
                }
            }
        }
    }

    private void addDeveloperView(){
        try {
            String name;
            do{
                System.out.println("Ingrese el nombre del desarrollador");
                name = scanner.nextLine();
                if(name.isEmpty()){
                    System.out.println("El nombre no puede estar vacío");
                }
            }while (name.isEmpty());

            String id;
            do{
                System.out.println("Ingrese el ID del desarrollador");
                id = scanner.nextLine();
                if(id.isEmpty()){
                    System.out.println("El ID no puede estar vacío");
                }
            }while (id.isEmpty());

            Integer age=null;
            do {
                System.out.println("Ingrese la edad del desarrollador");
                String inputAge = scanner.nextLine(); // Leer la entrada como String

                if (inputAge.isEmpty()) {
                    System.out.println("La edad no puede estar vacía. Por favor, ingrese un valor.");
                    continue; // Volver al inicio del bucle
                }

                try {
                    age = Integer.parseInt(inputAge); // Convertir la entrada a un número
                    if (age < 18) {
                        System.out.println("La edad tiene que ser mayor o igual a 18");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Por favor, ingrese un número válido para la edad.");
                    age = null; // Reiniciar la edad para repetir el bucle
                }
            } while (age == null || age < 18);

            controller.addDeveloper(name,id,age);
        } catch (DeveloperNotFoundException | DeveloperException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
        }
    }

    public void findAllView(){

        try {
            controller.findAll().stream().forEach( developer -> {
                System.out.println("Detalle del Desarrollador");
                System.out.println("Nombre: " + developer.getName());
                System.out.println("ID: " + developer.getId());
                System.out.println("Edad: " + developer.getAge());
                System.out.println("_________________________");
            });
        } catch (DeveloperException e) {
            System.out.println("Error: " + e.getMessage());
        }


    }
}
