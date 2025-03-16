package edu.sdccd.cisc191.template;

import java.util.Date;

public class Drink extends FoodItem {
    private boolean isOpened;

    public Drink(String name, String foodType, float quantityLeft, Date expirationDate, boolean isOpened) {
        super(name, foodType, quantityLeft, expirationDate);
        this.isOpened = isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
