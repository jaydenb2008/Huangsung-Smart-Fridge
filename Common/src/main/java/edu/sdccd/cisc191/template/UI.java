package edu.sdccd.cisc191.template;


import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.util.converter.FloatStringConverter;

import java.text.SimpleDateFormat;
import java.util.Date;


public class UI {


    FridgeManager fm;
    Stage window;
    private Storage storage;
    private TableView<FoodItem> table;

    public UI(FridgeManager fm, Storage storage) {
        this.fm = fm;
        this.storage = storage;
    }


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
        nameColumn.setOnEditCommit(event -> event.getRowValue().setName(event.getNewValue()));

        // Food Type Column
        TableColumn<FoodItem, String> foodTypeColumn = new TableColumn<>("Food Type");
        foodTypeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFoodType()));
        foodTypeColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        foodTypeColumn.setOnEditCommit(event -> event.getRowValue().setFoodType(event.getNewValue()));

        // Quantity Left Column
        TableColumn<FoodItem, Float> quantityLeftColumn = new TableColumn<>("Quantity Left");
        quantityLeftColumn.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getQuantityLeft()).asObject());
        quantityLeftColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        quantityLeftColumn.setOnEditCommit(event -> event.getRowValue().setQuantityLeft(event.getNewValue()));

        // Expiration Date Column
        TableColumn<FoodItem, String> expirationDateColumn = new TableColumn<>("Expiration Date");
        expirationDateColumn.setCellValueFactory(cellData -> new SimpleStringProperty(new SimpleDateFormat("MM-dd-yyyy").format(cellData.getValue().getExpirationDate())));
        expirationDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        expirationDateColumn.setOnEditCommit(event -> {
            addFoodItem();
        });

        // Adding columns to the table
        table.getColumns().addAll(nameColumn, foodTypeColumn, quantityLeftColumn, expirationDateColumn);

        // Initial data
        table.getItems().add(new FoodItem("Milk", "Dairy", 2.5f, new Date()));

        // Load stored food items into table
        loadStoredItems();

        Pane root = new Pane();
        root.getChildren().add(table);
        root.setStyle("-fx-background-color: black;");
        table.setLayoutX(20);
        table.setLayoutY(20);
        table.setPrefWidth(650);
        table.setPrefHeight(500);

        // Button to allow adding a new row
        Button addRowButton = new Button("Add New Row");
        addRowButton.setStyle("-fx-font-size: 16px; -fx-background-color: white;");
        addRowButton.setLayoutX(350);
        addRowButton.setLayoutY(475);
        addRowButton.setOnAction(e -> {
            // New blank row to the table
            table.getItems().add(new FoodItem("", "", 0, new Date()));
        });

        root.getChildren().add(addRowButton);

        // Button to allow removing a row
        Button removeRowButton = new Button("Remove Selected Item");
        removeRowButton.setStyle("-fx-font-size: 16px; -fx-background-color: white;");
        removeRowButton.setLayoutX(475); // Same X position as the "Add New Row" button
        removeRowButton.setLayoutY(475); // Position it below the "Add New Row" button (adjust as needed)

        removeRowButton.setOnAction(e -> {
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
        });

// Add the remove row button to the root pane (assuming you already have 'root' pane created)
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
        table.getItems().clear();
        for (int i = 0; i < storage.getItemCount(); i++) {
            table.getItems().add(storage.getFoodItem(i));
        }
    }

    /**
     * Adds a new food item to `Storage` and updates the UI.
     */
    private void addFoodItem() {
        FoodItem newItem = new FoodItem("New Item", "Unknown", 1.0f, new Date());
        storage.addFood(newItem);
        table.getItems().add(newItem);
    }
}
    /**
     * Add images of food items later
    public void createObject(int bgNum, int objx, int objy, int objWidth, int objHeight, String objFileName) {


        // For food items and trash can
        Image objectImage = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(objFileName)));
        ImageView objectImageView = new ImageView(objectImage);
        objectImageView.setFitWidth(objWidth);
        objectImageView.setFitHeight(objHeight);
        objectImageView.setLayoutX(objx);
        objectImageView.setLayoutY(objy);


        bgPane[bgNum].getChildren().add(objectImageView);
    }
    **/

