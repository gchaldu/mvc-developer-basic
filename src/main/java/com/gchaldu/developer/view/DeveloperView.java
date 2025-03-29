package com.gchaldu.developer.view;

import com.gchaldu.developer.controller.DeveloperController;
import com.gchaldu.developer.exceptions.DeveloperException;
import com.gchaldu.developer.exceptions.DeveloperNotFoundException;
import com.gchaldu.developer.model.Developer;
import com.gchaldu.project.controller.ProjectController;
import com.gchaldu.project.model.Project;
import com.gchaldu.project.view.ProjectView;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DeveloperView {
    private final DeveloperController developerController;
    private final ProjectController projectController;
    private final ProjectView projectView;
    private final Scanner scanner;

    public DeveloperView(DeveloperController developerController, ProjectController projectController,
                         ProjectView projectView) {
        this.developerController = developerController;
        this.projectController = projectController;
        this.projectView = projectView;
        this.scanner = new Scanner(System.in);
    }

    public void showMenu() {
        while (true) {
            System.out.println("\nSeleccione una opción:");
            System.out.println("\uu0031\uFE0F\u20E3 Agregar Desarrollador");
            System.out.println("\uu0032\uFE0F\u20E3 Mostrar Desarrolladores");
            System.out.println("\uu0033\uFE0F\u20E3 Salir");
            System.out.print("Opción: ");

            int option = readValidInteger();
            switch (option) {
                case 1 -> addDeveloperView();
                case 2 -> findAllView();
                case 3 -> {
                    return;
                }
                default -> System.out.println("Opción inválida. Intente nuevamente.");
            }
        }
    }

    private void addDeveloperView() {
        try {
            //Datos del Developer
            String name = readNonEmptyString("👨‍💻 Ingrese el nombre del desarrollador:");
            String id = readNonEmptyString("🆔 Ingrese el ID del desarrollador:");
            int age = readValidInteger("🎂 Ingrese la edad del desarrollador (>=18):", 18);

            Project project = projectView.addOrFindProject();

            developerController.addDeveloper(name, id, age, project.getId(), project.getName(), project.getDescription());
            System.out.println("✅ Desarrollador agregado con éxito.");
        } catch (DeveloperNotFoundException | DeveloperException e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("⚠️ Error inesperado: " + e.getMessage());
        }
    }

    private void findAllView() {
        try {
            List<Developer> developers = developerController.findAll();
            if (developers.isEmpty()) {
                System.out.println("No hay desarrolladores registrados.");
            } else {
                developers.forEach(developer -> {
                    System.out.println("\n🔹 Desarrollador:");
                    System.out.println("\uD83D\uDC68\u200D\uD83D\uDCBB : " + developer.getName());
                    System.out.println("🆔: " + developer.getId());
                    System.out.println("🎂: " + developer.getAge());
                    System.out.println("📂 Proyecto: " + developer.getProject().getName());
                    System.out.println("📝 Descripción del proyecto: " + developer.getProject().getDescription());
                    System.out.println("_________________________");
                });
            }
        } catch (DeveloperException e) {
            System.out.println("⚠️ Error: " + e.getMessage());
        }
    }

    // Método para leer cadenas no vacías
    private String readNonEmptyString(String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("⚠️ El valor no puede estar vacío.");
            }
        } while (input.isEmpty());
        return input;
    }

    // Método para leer enteros válidos con un mínimo opcional
    private int readValidInteger(String message, int min) {
        int value;
        do {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                value = Integer.parseInt(input);
                if (value < min) {
                    System.out.println("⚠️ Debe ser al menos " + min + ".");
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Ingrese un número válido.");
            }
        } while (true);
    }

    // Método sobrecargado para leer enteros sin restricciones
    private int readValidInteger() {
        return readValidInteger("\uD83D\uDD22 Ingrese un número:", Integer.MIN_VALUE);
    }
}

