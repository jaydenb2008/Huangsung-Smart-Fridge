package edu.sdccd.cisc191.client;

import edu.sdccd.cisc191.common.Drink;
import edu.sdccd.cisc191.common.FoodItem;
import edu.sdccd.cisc191.common.FridgeManager;
import edu.sdccd.cisc191.common.Storage;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class UITest extends ApplicationTest {
    private FridgeManager testFM;
    private UI ui;
    private Storage mockStorage;
    private TableView<FoodItem> table;

    @Override
    public void start(Stage stage) {
        this.testFM = new FridgeManager();

        // Create a mock Storage with test data
        mockStorage = new Storage(5);
        mockStorage.addFood(new FoodItem("Apple", "Fruit", 3.0f, new Date()));
        mockStorage.addFood(new Drink("Milk", "Dairy", 1.5f, new Date(), false));

        // Initialize the UI with the mock storage and fridge manager
        ui = new UI();
        ui.storage = mockStorage;
        ui.fm = testFM;

        // Start the UI (this internally manages its scene)
        ui.start(stage);  // This is assumed to create and set the scene within the UI class
        stage.show();

        // Access the table from the UI class
        table = ui.table; // Reference to the table in UI
    }

    @BeforeEach
    void setup() {
        waitForFxEvents(); // Ensure JavaFX events are processed before each test
    }

    @Test
    public void testTableViewDisplaysMockData() {
        // Check that table contains the correct number of items
        assertEquals(2, table.getItems().size(), "Table should contain two items");

        // Validate the first food item
        FoodItem firstItem = table.getItems().get(0);
        assertEquals("Apple", firstItem.getName(), "First item should be Apple");
        assertEquals("Fruit", firstItem.getFoodType(), "Food type should be Fruit");

        // Validate the second food item (Drink)
        FoodItem secondItem = table.getItems().get(1);
        assertEquals("Milk", secondItem.getName(), "Second item should be Milk");
        assertEquals("Dairy", secondItem.getFoodType(), "Food type should be Dairy");
    }
}
