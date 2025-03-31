package edu.sdccd.cisc191.template;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Describes the attributes, methods, and behavior of a storage class
 * FoodItem[][] fridge = the 2D array that will store all the FoodItem and Drink objects like a fridge
 * int fridgeSize = the amount of food items the fridge can store
 * int itemCount = the number of food items in the fridge (initially set to 0)
 */
public class Storage {
    private FoodItem[][] fridge;
    private int fridgeSize;
    private int itemCount;
    private final Lock storageLock; //Ensures threat-safety with a lock

    public Storage(int fridgeSize) {
        this.fridge = new FoodItem[fridgeSize][1];
        this.fridgeSize = fridgeSize;
        this.itemCount = 0;
        this.storageLock = new ReentrantLock(); //Creates a reentrant lock
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getFridgeSize() {
        return fridgeSize;
    }

    /**
     * A method to obtain an item in the fridge based on its placement (index)
     * @param index the placement of the food item in the 2d array fridge
     * @return the item in the fridge with a placement index
     */
    public FoodItem getFoodItem(int index) {
        storageLock.lock();  // Locks before accessing resources
        try {
            if (index >= 0 && index < itemCount) {
                return fridge[index][0];
            }
            return null;
        } finally {
            storageLock.unlock();  // Releases the lock after using it
        }
    }

    /**
     * checks if there is enough space in the 2d array and then adds the desired food item to the end of the array
     * itemCount increases by 1
     * @param food the food item that will be added
     */
    public synchronized void addFood(FoodItem food) {
        storageLock.lock();  // Lock to protect
        try {
            if (itemCount == fridgeSize) {
                resizeArray();
            }
            fridge[itemCount][0] = food;
            itemCount++;
        } finally {
            storageLock.unlock();  // Releases the lock after modification
        }
    }

    /**
     * removes food item from fridge and shifts all proceeding items left with the last empty slot being null
     * itemCount decrements by 1
     * @param name the name of the food item to be removed
     */
    public synchronized void removeFood(String name) {
        storageLock.lock();  // Lock to prevent modifications
        try {
            boolean found = false;
            for (int i = 0; i < itemCount; i++) {
                if (fridge[i][0] != null && fridge[i][0].getName().equals(name)) {
                    found = true;
                    fridge[i][0] = null;
                }
                if (found && i < itemCount - 1) {
                    fridge[i][0] = fridge[i + 1][0];
                }
            }
            if (found) {
                fridge[itemCount - 1][0] = null;
                itemCount--;
                System.out.println(name + " removed.");
            } else {
                System.out.println("Item not found");
            }
        } finally {
            storageLock.unlock();  // Release lock after processing
        }
    }


    /**
     * If the fridge is full when the user wants to add another food item, this method will be called
     * adds 5 more spaces to the fridge and copies over the items in the old fridge to the new one
     */
    public synchronized void resizeArray() {
        storageLock.lock();  // Lock during resizing
        try {
            int newSize = fridgeSize + 5;
            FoodItem[][] newFridge = new FoodItem[newSize][1];
            System.arraycopy(fridge, 0, newFridge, 0, fridgeSize); // Copy items to new array
            this.fridge = newFridge;
            this.fridgeSize = newSize;
        } finally {
            storageLock.unlock();  // Release the lock
        }
    }
}
