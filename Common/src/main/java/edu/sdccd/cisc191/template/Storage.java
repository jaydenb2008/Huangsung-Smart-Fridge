package edu.sdccd.cisc191.template;


public class Storage {
    private FoodItem[][] fridge;
    private int fridgeSize;
    private int itemCount;

    public Storage(int fridgeSize) {
        this.fridge = new FoodItem[fridgeSize][1];
        this.fridgeSize = fridgeSize;
        this.itemCount = 0;
    }

    public int getItemCount() {
        return itemCount;
    }

    public int getFridgeSize() {
        return fridgeSize;
    }

    public FoodItem getFoodItem(int index) {
        if (index >= 0 && index < itemCount) {
            return fridge[index][0];
        }
        return null;
    }

    public void addFood(FoodItem food) {
        if (itemCount == fridgeSize) {
            resizeArray();
        }

        fridge[itemCount][0] = food;
        itemCount++;
    }

    public void removeFood(String name) {
        boolean found = false;
        for (int i = 0; i < itemCount; i++) {
            //if index is not empty and name matches, remove item
            if (fridge[i][0] != null && fridge[i][0].getName().equals(name)) {
                found = true;
                fridge[i][0] = null;
            }

            //if the item was not the last item in the array, shift all proceeding items to the left to fill in gaps
            if (found && i < itemCount - 1) {
                fridge[i][0] = fridge[i+1][0];
            }

            //ensure that there is one less item in the fridge before updating the counter
            if (found) {
                fridge[itemCount - 1][0] = null;
                itemCount--;
                System.out.println(name + " removed.");
            } else {
                System.out.println("Item not found");
            }
        }
    }


    public void resizeArray() {
        int newSize = fridgeSize + 5;
        FoodItem[][] newFridge = new FoodItem[newSize][1];

        for (int i = 0; i < fridgeSize; i++) {
            newFridge[i][0] = fridge[i][0];
        }

        this.fridge = newFridge;
        this.fridgeSize = newSize;
    }
}
