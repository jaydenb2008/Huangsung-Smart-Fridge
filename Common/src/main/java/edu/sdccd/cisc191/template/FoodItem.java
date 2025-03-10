package edu.sdccd.cisc191.template;

import java.util.Date;

public class FoodItem {
    private String name;
    private String foodType;
    private double quantity;
    private Date expirationDate;

    public FoodItem(String name, String foodType, double quantity, Date expirationDate) {
        this.name = name;
        this.foodType = foodType;
        this.quantity = quantity;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        return new Date().after(expirationDate);
    }
}
