package edu.sdccd.cisc191.template;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodItem {
    private String name;
    private String foodType;
    private float quantityLeft;
    private Date expirationDate;


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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

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

    public boolean isExpired(Date expiration) {
        return new Date().after(expiration);
    }
}
