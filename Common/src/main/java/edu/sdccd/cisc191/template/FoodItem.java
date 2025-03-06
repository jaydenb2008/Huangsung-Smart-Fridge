package edu.sdccd.cisc191.template;

import java.util.Date;

public abstract class FoodItem implements Perishable {
    private String name;
    private int quantity;
    private Date expirationDate;
    private String storageType;

    public FoodItem(String name, int quantity, Date expirationDate, String storageType) {
        this.name = name;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.storageType = storageType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    @Override
    public boolean isExpired() {
        return new Date().after(expirationDate);
    }

    @Override
    public int daysUntilExpiration() {
        long time = expirationDate.getTime();
        return (int) (time / 86400000);
    }
}
