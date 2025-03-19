package edu.sdccd.cisc191.template;

public class Notifier extends Thread {
    private final String name;
    private final Storage storage;

    public Notifier(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

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
