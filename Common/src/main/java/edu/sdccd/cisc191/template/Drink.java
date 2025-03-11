package edu.sdccd.cisc191.template;

public class Drink extends FoodItem {
    private boolean isOpened;

    public Drink(String name, String foodType, double quantityLeft, boolean isOpened) {
        super(name, foodType, quantityLeft);
        this.isOpened = isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
