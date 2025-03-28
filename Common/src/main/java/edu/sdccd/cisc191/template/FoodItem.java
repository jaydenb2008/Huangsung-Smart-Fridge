package edu.sdccd.cisc191.template;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//Change and import java.time.Local date... replace Java.util.Date with that.

/**
 * This class defines the attributes and methods of a food object that will be stored in the Huangsung Smart Fridge
 * String name: the name of the food
 * String foodType: the category the food belongs to (i.e. cheese belongs to dairy)
 * float quantityLeft: the amount remaining of said food (i.e. 4 tomatoes)
 * Date expirationDate: the date the food expires (in PST)
 */

public class FoodItem {
    private final String name;
    private final  String foodType;
    private final float quantityLeft;
    private final Date expirationDate;
//no changes after creation.. once you create the food item, its name type quantity and experation date should not change. using final makes sure these values stay the same.

    public FoodItem(String name, String foodType, float quantityLeft, Date expirationDate) {
        this.name = name;
        this.foodType = foodType;
        this.quantityLeft = quantityLeft;
        this.expirationDate = expirationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public float getQuantityLeft() {
        return quantityLeft;
    }

    public void setQuantityLeft(float quantityLeft) {
        this.quantityLeft = quantityLeft;
    }

    public synchronized Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Converts the user's valid String expiration date input into a Date for constructing the FoodItem object
     * @param userInputDate = the String expiration date the user inputs in the UI
     * @return the user's date converted from a String to a Date
     */
    public static Date convertToDate(String userInputDate) {
        Date date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");

        try {
            date = formatter.parse(userInputDate);
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        return date;
    }

    /**
     * Checks if the food item is expired based on the current date and expirationDate attribute
     * @param expiration the expiration date of the food item
     * @return true if the current date is after the expiration date, false otherwise
     */
    public synchronized boolean isExpired(Date expiration) {
        return new Date().after(expiration);
    }
}
