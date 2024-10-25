package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DealershipFileManager {

    private static final String FILE_PATH = "inventory.csv";

    public Dealership getDealership() {
        Dealership dealership = null;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            // Read the first line for dealership info (name, address, phone)
            String dealershipInfo = br.readLine();
            if (dealershipInfo != null) {
                String[] dealershipData = dealershipInfo.split("\\|");
                String name = dealershipData[0];
                String address = dealershipData[1];
                String phone = dealershipData[2];

                // Create a new Dealership object
                dealership = new Dealership(name, address, phone);
            }

            // Read the remaining lines for vehicle info
            ArrayList<Vehicle> vehicles = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] vehicleData = line.split("\\|");
                int vin = Integer.parseInt(vehicleData[0]);
                int year = Integer.parseInt(vehicleData[1]);
                String make = vehicleData[2];
                String model = vehicleData[3];
                String vehicleType = vehicleData[4];
                String color = vehicleData[5];
                int mileage = Integer.parseInt(vehicleData[6]);
                double price = Double.parseDouble(vehicleData[7]);

                // Create a Vehicle object and add it to the list
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, mileage, price);
                vehicles.add(vehicle);
            }

            // Set the inventory in the dealership
            if (dealership != null) {
                dealership.setInventory(vehicles);
            }

        } catch (IOException e) {
            System.out.println("Error reading the inventory file: " + e.getMessage());
        }

        return dealership;
    }

    //empty in phase 2
    public void saveDealership(Dealership dealership) {}
}
