package edu.sdccd.cisc191.common;


public class FridgeManager {

    private final Storage storage;

    public FridgeManager() {
        this.storage = new Storage(10);
    }
    public Storage getStorage() {
        return storage;
    }
}
