package edu.sdccd.cisc191.template;

/**
 * This class provides a GUI to interact with the Smart Fridge.
 * The interface features a main screen displaying a table of food items, with options to add, edit, and remove items.
 * The system also supports saving and loading food items from a CSV file.
 *
 * Users can interact with the following components:
 * - A TableView to display food items with editable fields such as name, food type, quantity, and expiration date.
 * - Buttons to add and remove food items, and a checkbox to specify whether an item is a drink.
 */
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.converter.FloatStringConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

/**
 * This class defines the UI of the program.
 * Contains a start screen and a tableview of all the foods and drinks the user inputs
 * Starts the notifier warning the user of expired foods
 * Loads and saves all the foods into csv
 */
public class UI {


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
        // Set a specified size for the nameColumn
        nameColumn.setPrefWidth(100);
        // Prevent users from moving columns around and resizing them
        nameColumn.setReorderable(false);
        nameColumn.setResizable(false);

        // Food Type Column
        TableColumn<FoodItem, String> foodTypeColumn = new TableColumn<>("Food Type");
        foodTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodType()));
        foodTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        foodTypeColumn.setOnEditCommit(event -> {
            event.getRowValue().setFoodType(event.getNewValue());
            saveCurrentItems();
        });
        foodTypeColumn.setPrefWidth(100);
        foodTypeColumn.setReorderable(false);
        foodTypeColumn.setResizable(false);

        // Quantity Left Column
        TableColumn<FoodItem, Float> quantityLeftColumn = new TableColumn<>("Quantity Left");
        quantityLeftColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getQuantityLeft()).asObject());
        quantityLeftColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        quantityLeftColumn.setOnEditCommit(event -> {
            event.getRowValue().setQuantityLeft(event.getNewValue());
            saveCurrentItems();
        });
        quantityLeftColumn.setPrefWidth(100);
        quantityLeftColumn.setReorderable(false);
        quantityLeftColumn.setResizable(false);


        // Expiration Date Column
        TableColumn<FoodItem, String> expirationDateColumn = new TableColumn<>("Expiration Date");
        expirationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("MM-dd-yyyy").format(cellData.getValue().getExpirationDate())));
        expirationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        expirationDateColumn.setOnEditCommit(event -> {
            Date newDate = FoodItem.convertToDate(event.getNewValue());
            event.getRowValue().setExpirationDate(newDate);
            saveCurrentItems();
        });
        expirationDateColumn.setPrefWidth(100);
        expirationDateColumn.setReorderable(false);
        expirationDateColumn.setResizable(false);



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
        isOpenedColumn.setPrefWidth(100);
        isOpenedColumn.setReorderable(false);
        isOpenedColumn.setResizable(false);


        // Adding columns to the table
        table.getColumns().addAll(nameColumn, foodTypeColumn, quantityLeftColumn, expirationDateColumn, isOpenedColumn);

        // Initial data
        //table.getItems().add(new Drink("Milk", "Dairy", 0.5f, new Date(), false));

        // Load stored food items into table
        loadStoredItems();

        if(storage.getItemCount() > 0) {
            Notifier notifier = new Notifier("UI notifier", storage, this);
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
        // Create a dialog box to specify information to add a new food/drink item to the bridge
        Dialog<FoodItem> dialog = new Dialog<>();
        dialog.setTitle("Add Food/Drink");

        // Set the button types.
        ButtonType okButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(okButtonType, ButtonType.CANCEL);

        // Create a VBox to hold our text fields.
        VBox entry = new VBox(10); // 10 is spacing between nodes

        // Create and configure text fields.
        // TextField to specify the name of the food item
        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        // TextField to specify the food type
        TextField foodTypeField = new TextField();
        foodTypeField.setPromptText("Food Type");
        // TextField to specify the quantity of the food item
        TextField quantityField = new TextField();
        quantityField.setPromptText("Quantity");
        // TextField to specify the expiration date of the food item
        TextField expirationField = new TextField();
        expirationField.setPromptText("Expiration Date (MM/dd/yyyy)");

        // Add text fields to the layout.
        entry.getChildren().addAll(nameField, foodTypeField, quantityField, expirationField);
        dialog.getDialogPane().setContent(entry);

        // Request focus on the name field by default.
        Platform.runLater(nameField::requestFocus);

        // Convert the result to a FoodItem (or Drink) when the OK button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == okButtonType) {
                if (nameField.getText().isEmpty() || foodTypeField.getText().isEmpty() || quantityField.getText().isEmpty() || expirationField.getText().isEmpty()) {
                    // If any of the fields are empty, don't let the field to be saved.
                    System.out.println("Empty, don't continue");
                } else {
                    // Fetch the name and type from the text boxes
                    String name = nameField.getText();
                    String type = foodTypeField.getText();

                    // Parse quantity, defaulting to 1.0 if parsing fails.
                    double quantity = 1.0;
                    try {
                        quantity = Double.parseDouble(quantityField.getText());
                    } catch (NumberFormatException e) {
                        System.out.println("NumberFormatException");
                    }

                    // Parse the expiration date. Adjust the format if needed.
                    Date expiration = new Date();
                    try {
                        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
                        expiration = sdf.parse(expirationField.getText());
                    } catch (ParseException e) {
                        // Optionally notify user or use a default date.
                    }

                    // Create the appropriate object based on isDrink.
                    if (isDrink) {
                        // Assume Drink extends FoodItem and takes a float for quantity.
                        return new Drink(name, type, (float) quantity, expiration, false);
                    } else {
                        return new FoodItem(name, type, (float) quantity, expiration);
                    }
                }
            }
            return null;
        });

        // Grab the resultant from the user's input from the dialog
        Optional<FoodItem> result = dialog.showAndWait();

        // Push the new item into the storage and table
        result.ifPresent(newItem -> {
            storage.addFood(newItem);
            table.getItems().add(newItem);
        });
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
}

