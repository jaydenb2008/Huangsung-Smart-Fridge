package edu.sdccd.cisc191.template;

import java.util.Date;

/**
 * This class inherits from the FoodItem class and describes the attributes and behavior of a drink object
 * boolean isOpened: true if the user has opened the drink, false if unopened
 * float quantityLeft: inherited from FoodItem but is used to describe the fraction of the drink remaining (i.e. 0.5f of the bottle of lemonade remaining)
 */
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
