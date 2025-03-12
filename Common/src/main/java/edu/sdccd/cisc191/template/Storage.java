package edu.sdccd.cisc191.template;

import java.util.ArrayList;

public class Storage {
    private ArrayList<FoodItem> storage = new ArrayList<FoodItem>();

    //TODO add fridge item to the arrayList
    public void addFood(FoodItem foodItem) {
        storage.addFirst(foodItem);
    }

    //TODO remove fridge item from arraylist
    public void removeFood(FoodItem foodItem) {
        storage.remove(foodItem);
    }

    //TODO convert arrayList to a 2D String array
    public void convertArrayListTo2DArray() {

    }
}
