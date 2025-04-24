package edu.sdccd.cisc191.common;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.util.Date;

/**
 * This class inherits from the FoodItem class and describes the attributes and behavior of a drink object
 * boolean isOpened: true if the user has opened the drink, false if unopened
 * float quantityLeft: inherited from FoodItem but is used to describe the fraction of the drink remaining (i.e. 0.5f of the bottle of lemonade remaining)
 */

@Entity
public class Drink extends FoodItem {
    private boolean isOpened;

    public Drink() {
        super();
    }

    public Drink(String name, String foodType, float quantityLeft, Date expirationDate, boolean isOpened) {
        super(name, foodType, quantityLeft, expirationDate);
        this.isOpened = isOpened;
    }

    public boolean getOpened() {
        return isOpened;
    }

    public void setOpened() {
        this.isOpened = isOpened;
    }

    public boolean isOpened() {
        return isOpened;
    }
}
