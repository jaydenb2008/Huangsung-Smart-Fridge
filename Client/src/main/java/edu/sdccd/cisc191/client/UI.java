package edu.sdccd.cisc191.client;

/**
 * This class provides a GUI to interact with the Smart Fridge.
 * The interface features a main screen displaying a table of food items, with options to add, edit, and remove items.
 * The system also supports saving and loading food items from a CSV file.
 *
 * Users can interact with the following components:
 * - A TableView to display food items with editable fields such as name, food type, quantity, and expiration date.
 * - Buttons to add and remove food items, and a checkbox to specify whether an item is a drink.
 */
import edu.sdccd.cisc191.common.*;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class defines the UI of the program.
 * Contains a start screen and a tableview of all the foods and drinks the user inputs
 * Starts the notifier warning the user of expired foods
 * Loads and saves all the foods into csv
 */
public class UI implements NotifierListener {


    FridgeManager fm;
    Stage window;
    private final Storage storage;
    protected TableView<FoodItem> table;
    private static final String CSV_FILE = "food_items.csv";

    public UI(FridgeManager fm, Storage storage) {
        this.fm = fm;
        this.storage = storage;
    }

    /**
     * Render the screen that shows the start screen
     * @param primaryStage the window that will render the start screen
     */
    public void createStartScreen(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Huangsung Smart-Fridge");
        window.setWidth(700);
        window.setHeight(900);


        Pane root = new Pane();
        root.setStyle("-fx-background-color: black;");


        // Floating Text
        Text floatingText = new Text(80, 200, "Lehuang Smart Fridge");
        floatingText.setFont(Font.font("Times New Roman", 50));
        floatingText.setFill(Color.DARKRED);

        //START BUTTON
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 24px; -fx-background-color: white;");
        startButton.setLayoutX((700 - 150) / 2);
        startButton.setLayoutY(400);


        startButton.setOnAction(e -> {
            // Transition to the main "Fridge" screen
            createMainField(window);
        });


        root.getChildren().addAll(floatingText, startButton);


        Scene titleScene = new Scene(root, 900, 700);
        window.setScene(titleScene);
        window.show();
    }

    /**
     * Render the screen that shows all the foodItems
     * @param primaryStage the window that will render the table of food items
     */
    public void createMainField(Stage primaryStage) {
        this.window = primaryStage;
        window.setTitle("Huangsung Smart-Fridge");
        window.setWidth(700);
        window.setHeight(900);

        // TableView for the fridge groceries
        table = new TableView<>();
        table.setEditable(true);


        // Name Column
        TableColumn<FoodItem, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        nameColumn.setOnEditCommit(event -> {
            event.getRowValue().setName(event.getNewValue());
            saveCurrentItems();
        });

        // Food Type Column
        TableColumn<FoodItem, String> foodTypeColumn = new TableColumn<>("Food Type");
        foodTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodType()));
        foodTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        foodTypeColumn.setOnEditCommit(event -> {
            event.getRowValue().setFoodType(event.getNewValue());
            saveCurrentItems();
        });

        // Quantity Left Column
        TableColumn<FoodItem, Float> quantityLeftColumn = new TableColumn<>("Quantity Left");
        quantityLeftColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getQuantityLeft()).asObject());
        quantityLeftColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        quantityLeftColumn.setOnEditCommit(event -> {
            event.getRowValue().setQuantityLeft(event.getNewValue());
            saveCurrentItems();
        });

        // Expiration Date Column
        TableColumn<FoodItem, String> expirationDateColumn = new TableColumn<>("Expiration Date");
        expirationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("MM-dd-yyyy").format(cellData.getValue().getExpirationDate())));
        expirationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        expirationDateColumn.setOnEditCommit(event -> {
            Date newDate = FoodItem.convertToDate(event.getNewValue());
            event.getRowValue().setExpirationDate(newDate);
            saveCurrentItems();
        });


            TableColumn<FoodItem, Boolean> isOpenedColumn = new TableColumn<>("Opened?");
            isOpenedColumn.setCellValueFactory(cellData -> {
                if (cellData.getValue() instanceof Drink) {
                    return ((Drink) cellData.getValue()).isOpenedProperty().asObject();
                }
                return new SimpleBooleanProperty(false).asObject();
            });
            isOpenedColumn.setCellFactory(tc -> new CheckBoxTableCell<>());
            isOpenedColumn.setOnEditCommit(event -> {
                if (event.getRowValue() instanceof Drink) {
                    ((Drink) event.getRowValue()).setOpened(event.getNewValue());
                    saveCurrentItems();
                }
            });

        // Adding columns to the table
        table.getColumns().addAll(nameColumn, foodTypeColumn, quantityLeftColumn, expirationDateColumn, isOpenedColumn);

        // Initial data
        //table.getItems().add(new Drink("Milk", "Dairy", 0.5f, new Date(), false));

        // Load stored food items into table
        loadStoredItems();

        if(storage.getItemCount() > 0) {
            Notifier notifier = new Notifier("UI notifier", storage, this); // 'this' is the UI, which implements NotifierListener
            notifier.start();
        }

        Pane root = new Pane();
        root.getChildren().add(table);
        root.setStyle("-fx-background-color: black;");
        table.setLayoutX(20);
        table.setLayoutY(20);
        table.setPrefWidth(650);
        table.setPrefHeight(500);

        CheckBox drinkCheckBox = new CheckBox("Is it a Drink?");
        drinkCheckBox.setLayoutX(370);
        drinkCheckBox.setLayoutY(420);
        root.getChildren().add(drinkCheckBox);

        // Button to allow adding a new row
        Button addRowButton = new Button("Add New Row");
        addRowButton.setStyle("-fx-font-size: 16px; -fx-background-color: white;");
        addRowButton.setLayoutX(350);
        addRowButton.setLayoutY(475);
        addRowButton.setOnAction(e -> {
            // New blank row to the table
            addFoodItem(drinkCheckBox.isSelected());
            saveCurrentItems();
        });

        root.getChildren().add(addRowButton);

        // Button to allow removing a row
        Button removeRowButton = new Button("Remove Selected Item");
        removeRowButton.setStyle("-fx-font-size: 16px; -fx-background-color: white;");
        removeRowButton.setLayoutX(475); // Same X position as the "Add New Row" button
        removeRowButton.setLayoutY(475); // Position it below the "Add New Row" button (adjust as needed)

        removeRowButton.setOnAction(e -> {
            removeSelectedItem();
            saveCurrentItems();
        });

        root.getChildren().add(removeRowButton);
        // Create and set the Scene
        Scene scene = new Scene(root, 900, 700);
        window.setScene(scene);
        window.show();
    }

    /**
     * Loads stored items from `Storage` into the UI TableView.
     */
    private void loadStoredItems() {
        // Load items into the Storage object directly
        LoadFood.loadFoodItems(CSV_FILE, storage);

        // Assuming storage.getItemCount() returns the number of valid items in the Storage's 2D array
        for (int i = 0; i < storage.getItemCount(); i++) {
            FoodItem item = storage.getFoodItem(i);
            if (item != null) {
                table.getItems().add(item);  // Add non-null items to the table
            }
        }
    }



    private void saveCurrentItems() {
        LoadFood.saveFoodItems(CSV_FILE, storage);
    }

    /**
     * Adds a new food item or drink to `Storage` and updates the UI.
     * @param isDrink if the drink box is checked a drink object will be instantiated
     */
    private void addFoodItem(boolean isDrink) {
        FoodItem newItem;

        if (isDrink) {
            newItem = new Drink("New Drink", "Beverage", 1.0f, new Date(), false);
        } else {
            newItem = new FoodItem("New Item", "Unknown", 1.0f, new Date());
        }

        storage.addFood(newItem);
        table.getItems().add(newItem);
    }

    private void removeSelectedItem() {

        // Get the selected food item in the table
        FoodItem selectedItem = table.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            // Remove from the Storage
            storage.removeFood(selectedItem.getName());

            // Remove the item from the TableView
            table.getItems().remove(selectedItem);

            System.out.println(selectedItem.getName() + " has been removed from the fridge.");
        } else {
            System.out.println("No item selected for removal.");
        }
    }

    @Override
    public void onItemsExpired(String[][] expiredItems) {
        showExpirationAlert(expiredItems);

    }
    public void showExpirationAlert(String[][] expiredItems) {
        StringBuilder message = new StringBuilder("The following items are expired:\n");

        for (String[] itemRow : expiredItems) {
            if (itemRow[0] != null) {
                message.append("- ").append(itemRow[0]).append("\n");
            }
        }

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Expired Items Alert");
        alert.setHeaderText("Some food items have expired!");
        alert.setContentText(message.toString());
        alert.showAndWait();
    }
}

