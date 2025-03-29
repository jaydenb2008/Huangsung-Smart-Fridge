package edu.sdccd.cisc191.template;


/**
 * Author Nicholas Hilaire
 *
 *
 * References: "How to write a unit Test" https://stackoverflow.com/questions/8751553/how-to-write-a-unit-test
 * "Writing Tests with JUNIT 5 "https://blog.jetbrains.com/idea/2020/09/writing-tests-with-junit-5/"
 */

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ServerTest
{

    // Test if specific unit type is inherenting from the Unit superClass
    @Test
    public void testInheritance()
    {
        Unit tank = new Tank("Test Tank", "Test Tank", "Tank", 100, 100, 50, 200, 300, 18, 20, "TestAbilities");
        Unit fighter = new Fighter("Test Fighter", "Fighter", "Air", 200, 30, 150, 400, 1.75, 300, 4000, "Test Abilities", 120);
        Unit infantry = new InfantryUnit("Test Infantry", "Infantry", "Ground", 50, 10, 100, 500, 1.0, 15, 1000, "Test Abilities", 30);

        assertEquals(true, tank instanceof Unit, "Tanks inherents from Unit Class");
        assertEquals(true, fighter instanceof Unit, "Fighter inherents from Unit Class");
        assertEquals(true, infantry instanceof Unit, "Infantry inherents from Unit Class");

    }

    // Test if the file is being read from the path and the contents of the file have data.
    @Test
    public void TestIOStream()
    {
        List<Unit> units = UnitStatsLoader.loadUnits();
        assertFalse(units.isEmpty());
    }

    // Test if units are being created in the UnitGenerator class
    @Test
    public void TestUnitList()
        {
            List<Unit> units = new ArrayList<>();
            assertNotNull(units, "The units list should be created and not null");
            assertTrue(units.isEmpty(), "The units list should be empty upon initialization");
            Unit testUnit = new Tank("Test Unit", "Infantry", "Test Specialization", 100, 10, 100, 500, 1.5, 20, 2000, "Test Abilities");

        }

}
