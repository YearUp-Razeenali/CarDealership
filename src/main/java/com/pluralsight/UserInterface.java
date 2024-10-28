package com.pluralsight;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
        // Initialize the dealership
        init();
    }

    // Private method to initialize the dealership
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    // Display the menu and process user input
    public void display() {
        Scanner scanner = new Scanner(System.in);
        int command;

        do {
            System.out.println("Welcome to " + dealership.getName());
            System.out.println("1. View all vehicles");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            command = scanner.nextInt();

            switch (command) {
                case 1:
                    processAllVehiclesRequest();
                    break;
                case 2:
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        } while (command != 2);

        scanner.close();
    }

    // Method to display all vehicles
    private void displayVehicles(List<Vehicle> vehicles) {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles available.");
            return;
        }
        for (Vehicle vehicle : vehicles) {
            System.out.println("VIN: " + vehicle.getVin() + ", Year: " + vehicle.getYear() +
                    ", Make: " + vehicle.getMake() + ", Model: " + vehicle.getModel() +
                    ", Type: " + vehicle.getVehicleType() + ", Color: " + vehicle.getColor() +
                    ", Mileage: " + vehicle.getOdometer() + ", Price: $" + vehicle.getPrice());
        }
    }

    // Process request to display all vehicles
    public void processAllVehiclesRequest() {
        List<Vehicle> vehicles = dealership.getAllVehicles();
        displayVehicles(vehicles);
    }

}
