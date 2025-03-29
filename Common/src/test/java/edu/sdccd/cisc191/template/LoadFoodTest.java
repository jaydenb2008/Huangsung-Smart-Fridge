package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class LoadFoodTest {

    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage(5); // Initialize Storage object before each test
    }

    @Test
    void testLoadFoodItems() {
        // Assuming the file "test_food.csv" exists with appropriate data
        String filename = "test_save_food.csv";

        // Load food items from the file
        LoadFood.loadFoodItems(filename, storage);

        // Ensure that items were loaded
        assertNotNull(storage);
        assertTrue(storage.getItemCount() > 0, "Storage should contain food items after loading.");

        // Verify the first item loaded
        FoodItem item = storage.getFoodItem(0);
        assertNotNull(item, "Food item should not be null.");
        assertEquals("Apple", item.getName(), "First item's name should be Apple.");
        assertEquals("Fruit", item.getFoodType(), "First item's food type should be Fruit.");
        assertTrue(item.getQuantityLeft() > 0, "First item's quantity should be greater than 0.");
        assertNotNull(item.getExpirationDate(), "First item's expiration date should not be null.");
    }

    @Test
    void testSaveFoodItems() {
        // Add some test data to the storage
        FoodItem apple = new FoodItem("Apple", "Fruit", 10, new Date());
        storage.addFood(apple);

        String filename = "test_save_food.csv";

        // Save food items to the file
        LoadFood.saveFoodItems(filename, storage);

        // Verify the file exists
        File file = new File(filename);
        assertTrue(file.exists(), "The file should exist after saving.");

        // Now, let's load the food items back and verify the contents
        Storage newStorage = new Storage(5);
        LoadFood.loadFoodItems(filename, newStorage);

        // Ensure the loaded storage has the same data
        assertEquals(1, newStorage.getItemCount(), "There should be one food item loaded.");
        FoodItem loadedItem = newStorage.getFoodItem(0);
        assertEquals("Apple", loadedItem.getName(), "Loaded item should be an Apple.");
        assertEquals("Fruit", loadedItem.getFoodType(), "Loaded item should be of type Fruit.");
        assertEquals(10, loadedItem.getQuantityLeft(), "Loaded item quantity should be 10.");
    }
}
