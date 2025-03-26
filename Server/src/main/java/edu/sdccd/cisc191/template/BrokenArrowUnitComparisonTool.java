
package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import java.util.List;

/**
 * Author Nicholas Hilaire
 *
 * Java GUI Tutorial - Make a GUI in 13 Minutes #99 "https://www.youtube.com/watch?v=5o3fMLPY7qY
 * "How to Use BoxLayout"  https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 */

//TODO: Move this class to Client because it is a UI class
public class BrokenArrowUnitComparisonTool extends Application
{

    // ComboBoxes for selecting units on the left and right panels
    private ComboBox<Unit> leftComboBox;
    private ComboBox<Unit> rightComboBox;

    // Panels that display unit details
    private VBox leftPanel;
    private VBox rightPanel;

    @Override
    public void start(Stage primaryStage)
    {
        // Load units from CSV (ensure the path is correct)
        // List of units loaded from CSV
        List<Unit> unitList = UnitStatsLoader.loadUnits("C:\\Users\\Nicko\\IdeaProjects\\CISC191-FinalProjectTemplate\\Server\\src\\main\\resources\\Broken Arrow Unit Stats.csv");
        System.out.println("Units loaded: " + unitList.size());

        // Create ComboBoxes and populate them with units
        leftComboBox = new ComboBox<>();
        rightComboBox = new ComboBox<>();
        leftComboBox.getItems().addAll(unitList);
        rightComboBox.getItems().addAll(unitList);

        // Use a StringConverter so that only the unit's name appears in the drop-down
        StringConverter<Unit> converter = new StringConverter<>() {
            @Override
            public String toString(Unit unit)
            {
                return unit == null ? "" : unit.getUnitName();
            }
            @Override
            public Unit fromString(String string)
            {
                return null; // Not needed in this case
            }
        };


        leftComboBox.setConverter(converter);
        rightComboBox.setConverter(converter);

        // Create detail panels for each side (without an image)
        leftPanel = createUnitDetailPanel();
        rightPanel = createUnitDetailPanel();

        // When a selection changes, update the corresponding panel
        leftComboBox.setOnAction(e -> updatePanel(leftPanel, leftComboBox.getValue()));
        rightComboBox.setOnAction(e -> updatePanel(rightPanel, rightComboBox.getValue()));

        // Top controls: display the ComboBoxes in an HBox
        HBox topControls = new HBox(10,
                new Label("Left Unit:"), leftComboBox,
                new Label("Right Unit:"), rightComboBox);
        topControls.setPadding(new Insets(10));

        // Container for the two detail panels side by side
        HBox panelsContainer = new HBox(20, leftPanel, rightPanel);
        panelsContainer.setPadding(new Insets(10));

        // Main UI using a BorderPane
        BorderPane root = new BorderPane();
        root.setTop(topControls);
        root.setCenter(panelsContainer);


        // Sets the UI to display unit as cards shapes in side by side panel.
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Unit Comparison (No Image)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Creates a panel (VBox) containing labels for unit details
    private VBox createUnitDetailPanel()
    {
        VBox panel = new VBox(10);
        panel.setPadding(new Insets(10));
        panel.setStyle("-fx-border-color: gray; -fx-border-width: 2; -fx-background-color: #f9f9f9;");

        // Create labels for each stat
        Label nameLabel = new Label("Name: ");
        Label typeLabel = new Label("Type: ");
        Label specializationLabel = new Label("Specialization: ");
        Label statsLabel = new Label("Stats: ");
        Label abilitiesLabel = new Label("Abilities: ");

        // Use a helper object to store these components for easy updating
        PanelComponents comps = new PanelComponents(nameLabel, typeLabel, specializationLabel, statsLabel, abilitiesLabel);
        panel.setUserData(comps);

        panel.getChildren().addAll(nameLabel, typeLabel, specializationLabel, statsLabel, abilitiesLabel);
        return panel;
    }

    // Updates the provided panel with data from the selected unit
    private void updatePanel(VBox panel, Unit unit)
    {
        if (unit == null) return;
        PanelComponents comps = (PanelComponents) panel.getUserData();
        comps.nameLabel.setText("Name: " + unit.getUnitName());
        comps.typeLabel.setText("Type: " + unit.getUnitType());
        comps.specializationLabel.setText("Specialization: " + unit.getSpecialization());
        comps.statsLabel.setText("Price: " + unit.getPrice() +
                " | Armor: " + unit.getArmor() +
                " | Health: " + unit.getHealth());
        comps.abilitiesLabel.setText("Abilities: " + unit.getAbilities());
    }

    // Helper class to hold UI components in each panel
    private static class PanelComponents
    {
        Label nameLabel;
        Label typeLabel;
        Label specializationLabel;
        Label statsLabel;
        Label abilitiesLabel;

        PanelComponents(Label nameLabel, Label typeLabel, Label specializationLabel, Label statsLabel, Label abilitiesLabel)
        {
            this.nameLabel = nameLabel;
            this.typeLabel = typeLabel;
            this.specializationLabel = specializationLabel;
            this.statsLabel = statsLabel;
            this.abilitiesLabel = abilitiesLabel;
        }
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}