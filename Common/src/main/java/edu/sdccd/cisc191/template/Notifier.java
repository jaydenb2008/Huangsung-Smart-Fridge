package edu.sdccd.cisc191.template;

public class Notifier implements Runnable {
    private String name;
    private Storage storage;

    public Notifier(String name, Storage storage) {
        this.name = name;
        this.storage = storage;
    }

    public void run() {

        System.out.println(name + " is running in Thread: " + Thread.currentThread().getName());
        //TODO create try-catch and exception for if there are no items in the fridge

        for (int i = 0; i < storage.getItemCount(); i++) {
            FoodItem item = storage.getFoodItem(i);

            if (item.isExpired(item.getExpirationDate())) {
                System.out.printf("%s is expired!%n", item.getName());
            }

            System.out.println(name + " ended.");
        }
    }
}
