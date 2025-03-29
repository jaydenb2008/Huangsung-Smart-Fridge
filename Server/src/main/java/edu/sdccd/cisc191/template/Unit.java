package edu.sdccd.cisc191.template;

/**
 * Author Nicholas Hilaire
 *
 *
 * References: "Bro code: Java: Inheritance" https://www.youtube.com/watch?v=Zs342ePFvRI
 */

public abstract class Unit
{
    private final String unitName;
    private final String unitType;
    private final String specialization;
    private final int price;
    private final int armor;
    private final int health;
    private final int sightRange;
    private final double unseenRange;
    private final int speed;
    private final int weight;
    private final String abilities;


    //  Constructor to Initialize the private objects in the Unit class.
    public Unit(String unitName, String unitType, String specialization, int price, int armor,
                int health, int sightRange, double unseenRange, int speed,
                int weight, String abilities)

    {
        this.unitName = unitName;
        this.unitType = unitType;
        this.specialization = specialization;
        this.price = price;
        this.armor = armor;
        this.health = health;
        this.sightRange = sightRange;
        this.unseenRange = unseenRange;
        this.speed = speed;
        this.weight = weight;
        this.abilities = abilities;
    }


    //Setter so that the code get the data in the CSV file and translate it to code
        public String getUnitName()
        {
            return unitName;
        }


        public String getUnitType()
        {
            return unitType;
        }


        public String getSpecialization()
        {
            return specialization;
        }


        public int getPrice()
        {
            return price;
        }


        public int getArmor()
        {
            return armor;
        }

        public int getHealth()
        {
            return health;
        }

        public int getSightRange()
        {
            return sightRange;
        }

        public double getUnseenRange()
        {
            return unseenRange;
        }

        public int getSpeed()
        {
            return speed;
        }

        public int getWeight()
        {
            return weight;
        }

        public String getAbilities()
        {
            return abilities;
        }


        // Takes the previous get Methods and translates the data into the specific unit stat.
            @Override
            public String toString()
            {
                return "Unit{" +
                        "unitName='" + unitName + '\'' +
                        ", unitType='" + unitType + '\'' +
                        ", specialization='" + specialization + '\'' +
                        ", price=" + price +
                        ", armor=" + armor +
                        ", health=" + health +
                        ", sightRange=" + sightRange +
                        ", unseenRange=" + unseenRange +
                        ", speed=" + speed +
                        ", weight=" + weight +
                        ", abilities='" + abilities + '\'' +
                        '}';
            }

}

