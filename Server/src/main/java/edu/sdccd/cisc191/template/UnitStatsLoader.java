package edu.sdccd.cisc191.template;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Author Nicholas Hilaire
 *
 * References: "Get Substring from String in Java" by Tom Hombergs  (https://www.baeldung.com/java-substring),
 * Java read from URL stream working selectively (https://stackoverflow.com/questions/54433100/java-read-from-url-stream-working-selectively)
 * Read a File from Resources Directory (https://howtodoinjava.com/java/io/read-file-from-resources-folder)
 *
 */


//Class used to access the CSV file and create units with the data from the file.
public class UnitStatsLoader
{
    public static List<Unit> loadUnits()
    {
        List<Unit> units = new ArrayList<>();

        // Updated: use the resources folder so that the project works across computers instead of hardcoding the file path
        URL resource = UnitStatsLoader.class.getResource("/Stats.csv");

        File file = null;
        if (resource != null) {
            file = new File(resource.getPath());
        }
        // Try to locate the file in the given path or in src/main/resources/
        if (!file.exists())
        {
            resource = UnitStatsLoader.class.getResource("/Stats.csv");
            file = new File(resource.getPath());

        // If file is not found will ensure the correct path is used.
        }
        if (!file.exists())
        {
            System.err.println("File not found: " + resource.getPath());
            return units;
        }

        /* Allows the code to gather data in a buffer and then read text in from a character input stream that takes in the file object that is declared
         in the previous code listed above.
        */
        try (BufferedReader br = new BufferedReader(new FileReader(file)))
        {
            String line;
            // Read and discard the header row
            if ((line = br.readLine()) == null)
            {
                return units;
            }

            // while loop reads the file one line at a time until no more lines occur.
            while ((line = br.readLine()) != null)
            {
                // Split the CSV line using a regex that ignores commas within quotes
                String[] stats = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                // for loop to Trim whitespace from each data point from csv file
                for (int i = 0; i < stats.length; i++)
                {
                    stats[i] = stats[i].trim();
                }

                    //Uses the methods from UnitGenerator that creates an Unit object
                    try
                    {
                        Unit unit = UnitGenerator.createUnit(stats);
                        units.add(unit);
                    }

                        catch (NumberFormatException e)
                        {
                            System.err.println("Error parsing stats: " + line);
                            e.printStackTrace();
                        } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
            }
        }

        //Handles any errors if the file is not found
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }

        // Handles any errors and if an error occurs will return an empty string.
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return units;
    }

}