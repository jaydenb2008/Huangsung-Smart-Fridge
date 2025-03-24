package edu.sdccd.cisc191.template;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoadFood {

    /**
     * Loads the food items from a CSV file into the 2D array in Storage.
     *
     * @param filename The name of the CSV file.
     * @param storage  The Storage object where the food items will be loaded.
     */
    public static void loadFoodItems(String filename, Storage storage) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            // Skip the header line
            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 4) {
                    String name = data[0];
                    String foodType = data[1];
                    float quantityLeft = Float.parseFloat(data[2]);
                    Date expirationDate = dateFormat.parse(data[3]);

                    // Create a FoodItem or Drink object based on certain conditions
                    FoodItem item = new FoodItem(name, foodType, quantityLeft, expirationDate);
                    // If you want to handle drinks differently, check here
                    if (foodType.equalsIgnoreCase("Beverage")) {
                        item = new Drink(name, foodType, quantityLeft, expirationDate, false);
                    }

                    // Add item to the 2D array in Storage
                    storage.addFood(item);
                }
            }
        } catch (IOException | java.text.ParseException e) {
            System.err.println("Error while loading food items from CSV: " + e.getMessage());
        }
    }


    /**
     * Saves the food items from the Storage's 2D array into a CSV file.
     *
     * @param filename The name of the CSV file.
     * @param storage  The Storage object containing the 2D array of FoodItems.
     */
    public static void saveFoodItems(String filename, Storage storage) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            // Write the CSV header
            writer.write("Name,Food Type,Quantity Left,Expiration Date\n");

            // Format for expiration date
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            // Iterate through the 2D array of food items in Storage
            for (int i = 0; i < storage.getItemCount(); i++) {
                FoodItem item = storage.getFoodItem(i);

                if (item != null) {
                    // Write each food item as a CSV line
                    writer.write(String.format("%s,%s,%f,%s\n",
                            item.getName(),
                            item.getFoodType(),
                            item.getQuantityLeft(),
                            dateFormat.format(item.getExpirationDate())));
                }
            }
        } catch (IOException e) {
            System.err.println("Error while saving food items to CSV: " + e.getMessage());
        }
    }
}
