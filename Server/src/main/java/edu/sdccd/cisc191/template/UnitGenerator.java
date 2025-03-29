package edu.sdccd.cisc191.template;


/**
 * Author Nicholas Hilaire
 *
 * References: "Get Substring from String in Java" by Tom Hombergs  (https://www.baeldung.com/java-substring),
 * Java read from URL stream working selectively (https://stackoverflow.com/questions/54433100/java-read-from-url-stream-working-selectively)
 * Read a File from Resources Directory (https://howtodoinjava.com/java/io/read-file-from-resources-folder)
 * "Java: CSV File Easy Read/Write" https://stackoverflow.com/questions/14226830/java-csv-file-easy-read-write
 * "How to correctly use parseInt in Java" https://stackoverflow.com/questions/77137174/how-to-correctly-use-parseint-in-java
 */

public class UnitGenerator {

    public static Unit createUnit(String[] stats) throws Exception {
        // Loops through all the lines in the CSV file and removes extra lines from strings.
        for (int i = 0; i < stats.length; i++)
        {
            stats[i] = stats[i].replaceAll("\"", "").trim();
        }

        // Gets the units information from the CSV file scanning each data inputs legenth and assigning it to a specific stat
        String unitType = stats.length > 0 ? stats[0] : "";  //Displays the Unit's type
        String unitName = stats.length > 1 ? stats[1] : "";  //Displays the Unit's Name
        String specialization = stats.length > 2 ? stats[2] : "";  //Displays the Unit's Specilaization
        int price = parseIntSafe(stats, 3, 0);  //Displays the Unit's Price
        int armor = parseIntSafe(stats, 4, 0);  // Displays the Units Armor Level
        int health = parseIntSafe(stats, 5, 0);  //Displays the Units Health
        int sightRange = parseIntSafe(stats, 6, 0);  //Displays the Unit's sight range
        double unseenRange = parseDoubleSafe(stats, 7, 0.0); //Displays the unseen Range
        int speed = parseIntSafe(stats, 8, 0);  //Displays the unit's speed
        int weight = parseIntSafe(stats, 9, 0);  //Displays its weights
        String abilities = stats.length > 10 ? stats[10] : ""; //Lists its abilities
        int extra = parseIntSafe(stats, 11, 0); //List the Extra stats that are associated  with its unit type

            // Based on the unit type (category), create the appropriate unit object.
            if (unitType.equalsIgnoreCase("Tank"))
            {
                return new Tank(unitName, unitType, specialization, price, armor, health, sightRange, unseenRange, speed, weight, abilities);
            }

            else if (unitType.equalsIgnoreCase("Fighter"))
            {
                return new Fighter(unitName, unitType, specialization, price, armor, health, sightRange, unseenRange, speed, weight, abilities, extra);
            }

            else if (unitType.equalsIgnoreCase("Infantry"))
            {
                return new InfantryUnit(unitName, unitType, specialization, price, armor, health, sightRange, unseenRange, speed, weight, abilities, extra);
            }

            else
            {
                return new BasicUnit(unitName, unitType, specialization, price, armor, health, sightRange, unseenRange, speed, weight, abilities);
            }
    }

            // Helper method to space an integer safely
            private static int parseIntSafe(String[] stats, int index, int defaultValue)
            {
                if (stats.length > index && !stats[index].isEmpty()) {
                    try {
                        return Integer.parseInt(stats[index]);
                    } catch (NumberFormatException e) {
                        System.err.println("Failed to parse integer at index " + index + ": " + stats[index]);
                    }
                }
                return defaultValue;
            }

            // Helper method to space a double safely
            private static double parseDoubleSafe(String[] stats, int index, double defaultValue) {
                if (stats.length > index && !stats[index].isEmpty()) {
                    try {
                        return Double.parseDouble(stats[index]);
                    } catch (NumberFormatException e) {
                        System.err.println("Failed to parse double at index " + index + ": " + stats[index]);
                    }
                }
                return defaultValue;
            }
}