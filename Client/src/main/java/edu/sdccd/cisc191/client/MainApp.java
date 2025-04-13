package edu.sdccd.cisc191.client;

import edu.sdccd.cisc191.common.FridgeManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        FridgeManager fridgeManager = new FridgeManager();
        UI ui = new UI(fridgeManager, fridgeManager.getStorage());
        ui.createStartScreen(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}