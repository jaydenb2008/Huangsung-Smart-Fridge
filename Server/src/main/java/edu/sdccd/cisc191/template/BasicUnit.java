package edu.sdccd.cisc191.template;

public class BasicUnit extends Unit {

    public BasicUnit(String unitName, String unitType, String specialization, int price, int armor,
                     int health, int sightRange, double unseenRange, int speed,
                     int weight, String abilities) {
        super(unitName, unitType, specialization, price, armor, health, sightRange, unseenRange, speed, weight, abilities);
    }

    // You can add additional methods or override existing ones if needed

    @Override
    public String toString() {
        return "BasicUnit{" +
                "unitName='" + getUnitName() + '\'' +
                ", unitType='" + getUnitType() + '\'' +
                ", specialization='" + getSpecialization() + '\'' +
                ", price=" + getPrice() +
                ", armor=" + getArmor() +
                ", health=" + getHealth() +
                ", sightRange=" + getSightRange() +
                ", unseenRange=" + getUnseenRange() +
                ", speed=" + getSpeed() +
                ", weight=" + getWeight() +
                ", abilities='" + getAbilities() + '\'' +
                '}';
    }
}
