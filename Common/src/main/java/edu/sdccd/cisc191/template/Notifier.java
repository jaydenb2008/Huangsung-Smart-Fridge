package edu.sdccd.cisc191.template;

import java.util.ArrayList;
import java.util.List;

/**
 * Defines the structure and behavior of the background thread used to check if items in the fridge are expired
 * String name: name of the thread
 * Storage storage: 2d array that will store all the food items
 */
public class Notifier extends Thread {
    private final String name;
    private final Storage storage;
    private String[][] expiredItems; // Store expired item names
    private UI ui; // Reference to UI for notifications



    public Notifier(String name, Storage storage, UI ui) {
        this.name = name;
        this.storage = storage;
        this.ui = ui;
        this.expiredItems = new String[storage.getItemCount()][2]; // Store expired item details. Each row holds name and expiration date
    }

    /**
     * when the thread starts, it will iterate through the storage and check if any of the items are expired using the .isExpired() method
     * if there are no items in the fridge, EmptyFridgeException will be called
     */
    @Override
    public void run() {

        System.out.println(name + " is running in Thread: " + Thread.currentThread().getName());

        try {
            //if fridge is empty, then throw EmptyFridgeException
            if (storage.getItemCount() == 0) {
                throw new EmptyFridgeException("There are no items in the fridge to check!");
            }

            int expiredCount = 0;
            for (int i = 0; i < storage.getItemCount(); i++) {
                FoodItem item = storage.getFoodItem(i);

                if (item != null && item.isExpired(item.getExpirationDate())) {
                    System.out.printf("%s is expired!%n", item.getName());
                    // Store expired item details
                    expiredItems[expiredCount][0] = item.getName();
                    expiredItems[expiredCount][1] = item.getExpirationDate().toString();
                    expiredCount++;
                }
            }

            if (expiredCount > 0) {
                ui.showExpirationAlert(expiredItems); // Notify the UI
            }

        } catch (EmptyFridgeException e) {
            System.err.println(e.getMessage());
        }
    }
}
