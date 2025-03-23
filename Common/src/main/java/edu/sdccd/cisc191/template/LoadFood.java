package edu.sdccd.cisc191.template;

/**
 * This class handles loading, saving, and updating food items from a CSV file.
 */
import java.io.*;
import java.util.*;
import java.text.*;

/**
 * Class responsible for handling CSV file operations for FoodItem objects.
 */
public class LoadFood {
    /**
     * The default file path for the CSV file.
     */
    private static final String FILE_PATH = "food_items.csv";

    /**
     * Loads food items from the specified CSV file.
     *
     * @param filePath The path to the CSV file.
     * @return A list of FoodItem objects loaded from the file.
     */

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    /**
     * Date format used for parsing and formatting dates in the "MM-dd-yyyy" format.
     * Ensures consistency when reading from and writing to the CSV file.
     */

    public static List<FoodItem> loadFoodItems(String filePath) {
        List<FoodItem> items = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0].trim();
                String foodType = values[1].trim();
                float quantityLeft = Float.parseFloat(values[2].trim());
                Date expirationDate = dateFormat.parse(values[3].trim());


                items.add(new FoodItem(name, foodType, quantityLeft, expirationDate));
            }
        } catch (IOException | NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        return items;
    }

    /**
     * Saves a list of food items to the specified CSV file.
     *
     * @param filePath The path to the CSV file.
     * @param items The list of FoodItem objects to be saved.
     */
    public static void saveFoodItems(String filePath, List<FoodItem> items) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write("Name,FoodType,QuantityLeft,ExpirationDate");
            bw.newLine();
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

            for (FoodItem item : items) {
                bw.write(item.getName() + "," + item.getFoodType() + "," + item.getQuantityLeft() + "," + dateFormat.format(item.getExpirationDate()));
                bw.newLine();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing food item in the CSV file or adds it if not found.
     *
     * @param filePath The path to the CSV file.
     * @param updatedItem The FoodItem object with updated information.
     */
    public static void updateFoodItem(String filePath, FoodItem updatedItem) {
        List<FoodItem> items = loadFoodItems(filePath);
        boolean found = false;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equalsIgnoreCase(updatedItem.getName())) {
                items.set(i, updatedItem);
                found = true;
                break;
            }
        }

        if (!found) {
            items.add(updatedItem);
        }

        saveFoodItems(filePath, items);
    }
}
