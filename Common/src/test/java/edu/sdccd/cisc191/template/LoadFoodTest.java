package edu.sdccd.cisc191.template;

import org.junit.jupiter.api.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

        import static org.junit.jupiter.api.Assertions.*;

public class LoadFoodTest {

    private static final String TEST_CSV_FILE = "test_food_items.csv";
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

    @BeforeEach
    void setUp() throws IOException {
        // Create a test CSV file with sample data
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_CSV_FILE))) {
            writer.write("Name,FoodType,QuantityLeft,ExpirationDate");
            writer.newLine();
            writer.write("Milk,Dairy,2.5,12-31-2025");
            writer.newLine();
            writer.write("Apple,Fruit,5.0,06-15-2025");
            writer.newLine();
        }
    }

    @Test
    void testLoadFoodItems() {
        List<FoodItem> items = LoadFood.loadFoodItems(TEST_CSV_FILE);

        assertEquals(2, items.size(), "Should load two food items");
        assertEquals("Milk", items.get(0).getName());
        assertEquals("Dairy", items.get(0).getFoodType());
        assertEquals(2.5f, items.get(0).getQuantityLeft());
        assertEquals("12-31-2025", dateFormat.format(items.get(0).getExpirationDate()));

        assertEquals("Apple", items.get(1).getName());
        assertEquals("Fruit", items.get(1).getFoodType());
        assertEquals(5.0f, items.get(1).getQuantityLeft());
        assertEquals("06-15-2025", dateFormat.format(items.get(1).getExpirationDate()));
    }

    @Test
    void testSaveFoodItems() throws Exception {
        List<FoodItem> newItems = Arrays.asList(
                new FoodItem("Bread", "Grain", 1.0f, dateFormat.parse("08-01-2025")),
                new FoodItem("Cheese", "Dairy", 0.5f, dateFormat.parse("09-10-2025"))
        );

        LoadFood.saveFoodItems(TEST_CSV_FILE, newItems);

        List<FoodItem> loadedItems = LoadFood.loadFoodItems(TEST_CSV_FILE);
        assertEquals(2, loadedItems.size());
        assertEquals("Bread", loadedItems.get(0).getName());
        assertEquals("Cheese", loadedItems.get(1).getName());
    }

    @Test
    void testUpdateFoodItem() throws Exception {
        FoodItem updatedMilk = new FoodItem("Milk", "Dairy", 3.0f, dateFormat.parse("01-01-2026"));
        LoadFood.updateFoodItem(TEST_CSV_FILE, updatedMilk);

        List<FoodItem> items = LoadFood.loadFoodItems(TEST_CSV_FILE);
        assertEquals(2, items.size());
        assertEquals(3.0f, items.get(0).getQuantityLeft());
        assertEquals("01-01-2026", dateFormat.format(items.get(0).getExpirationDate()));
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_CSV_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}
