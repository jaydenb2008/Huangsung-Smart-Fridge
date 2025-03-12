package edu.sdccd.cisc191.template;

import javafx.application.Application;
import javafx.stage.Stage;

public class FridgeManager extends Application {

    public UI ui = new UI(this);

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){

        ui.createStartScreen(primaryStage);
    }
}
