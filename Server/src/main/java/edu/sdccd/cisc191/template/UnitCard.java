package edu.sdccd.cisc191.template;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * Author Nicholas Hilaire
 *
 * "Java: CSV File Easy Read/Write" https://stackoverflow.com/questions/14226830/java-csv-file-easy-read-write
 * "How to Use BoxLayout"  https://docs.oracle.com/javase/tutorial/uiswing/layout/box.html
 */

//TODO move this class to client because it is a UI class
public class UnitCard extends VBox
{
    private final Label unitNameLabel;
    private final Label unitTypeLabel;
    private final Label specializationLabel;
    private final Label statsLabel;
    private final Label abilitiesLabel;

    public UnitCard()
    {
        super(5); // spacing between each specific unit stat
        // Set padding and border styling
        setPadding(new Insets(10));
        setStyle("-fx-border-color: black; -fx-background-color: #f0f0f0;");

        // Initialize labels so it will display on the application.
        unitNameLabel = new Label();
        unitNameLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        unitTypeLabel = new Label();
        specializationLabel = new Label();
        statsLabel = new Label();
        abilitiesLabel = new Label();

        // Add labels to the VBox so it will display on application.
        getChildren().addAll(unitNameLabel, unitTypeLabel, specializationLabel, statsLabel, abilitiesLabel);
    }

        // Generates the Strings to label the data types from Unit class to display on applicaiton.
        public void setUnit(Unit unit)
        {
            unitNameLabel.setText(unit.getUnitName());
            unitTypeLabel.setText("Type: " + unit.getUnitType());
            specializationLabel.setText("Spec: " + unit.getSpecialization());
            statsLabel.setText("Price: " + unit.getPrice() + " | Armor: " + unit.getArmor() +
                    " | Health: " + unit.getHealth());
            abilitiesLabel.setText("Abilities: " + unit.getAbilities());
        }
}
