package edu.sdccd.cisc191.template;

/**
 * Defines the structure and behavior of the background thread used to check if items in the fridge are expired
 * String name: name of the thread
 * Storage storage: 2d array that will store all the food items
 */
public class Notifier extends Thread {
    private final String name;
    private final Storage storage;

    public Notifier(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
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

            for (int i = 0; i < storage.getItemCount(); i++) {
                FoodItem item = storage.getFoodItem(i);

                if (item != null && item.isExpired(item.getExpirationDate())) {
                    System.out.printf("%s is expired!%n", item.getName());
                }
            }
        } catch (EmptyFridgeException e) {
            System.err.println(e.getMessage());
        }
    }
}
