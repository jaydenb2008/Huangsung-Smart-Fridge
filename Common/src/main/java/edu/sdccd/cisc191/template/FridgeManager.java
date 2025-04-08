package edu.sdccd.cisc191.template;


import javafx.application.Application;
import javafx.stage.Stage;


public class FridgeManager {

    private final Storage storage;

    public FridgeManager() {
        this.storage = new Storage(10);
    }
    public Storage getStorage() {
        return storage;
    }
}
