package edu.sdccd.cisc191.template;

import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

@ExtendWith(ApplicationExtension.class)
public class UITest {

    private UI ui;
    private Storage mockStorage;
    private TableView<FoodItem> table;

    @Start
    public void start(Stage stage) {
        // Create a mock Storage with test data
        mockStorage = new Storage(5);
        mockStorage.addFood(new FoodItem("Apple", "Fruit", 3.0f, new Date()));
        mockStorage.addFood(new Drink("Milk", "Dairy", 1.5f, new Date(), false));

        // Create UI with mock storage
        ui = new UI(new FridgeManager(), mockStorage);
        ui.createMainField(stage);  // Initialize UI with table

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
