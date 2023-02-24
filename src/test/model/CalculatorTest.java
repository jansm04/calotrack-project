package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorTest {

    Calculator calc;

    @BeforeEach
    public void setup() {
        calc = new Calculator();
        calc.setGender("male");
        calc.setAge(18);
        calc.setHeightFeet(5);
        calc.setHeightInches(10);
        calc.setWeight(150);
        calc.setWeightGoal(20);
        calc.setLevelOfActivity(1);
        calc.setObjective("maintain");
        calc.setTime(12);
    }


    @Test
    public void bmrCalculatorTest() {

        // check that bmr is greater than total for those who want to lose weight
        calc.setObjective("lose");
        assertTrue(calc.bmrCalculator() > calc.totalDailyCaloricRequirement());

        // check that bmr is less than total for those who want to gain weight
        calc.setObjective("gain");
        assertTrue(calc.bmrCalculator() < calc.totalDailyCaloricRequirement());

        // check that bmr is the same as total for those who want to maintain weight
        calc.setObjective("maintain");
        assertEquals(calc.bmrCalculator(), calc.totalDailyCaloricRequirement());

        // check that male bmr is greater than female bmr
        calc.setGender("male");
        int bmrMale = calc.bmrCalculator();
        calc.setGender("female");
        int bmrFemale = calc.bmrCalculator();
        assertTrue(bmrMale > bmrFemale);
    }

    @Test
    public void activityLevelTest() {
        calc.setLevelOfActivity(1);
        int total1 = calc.totalDailyCaloricRequirement();
        calc.setLevelOfActivity(2);
        int total2 = calc.totalDailyCaloricRequirement();
        calc.setLevelOfActivity(3);
        int total3 = calc.totalDailyCaloricRequirement();
        calc.setLevelOfActivity(4);
        int total4 = calc.totalDailyCaloricRequirement();
        calc.setLevelOfActivity(5);
        int total5 = calc.totalDailyCaloricRequirement();

        // check that each activity level has a lower total than the level higher than it
        assertTrue(total1 < total2);
        assertTrue(total2 < total3);
        assertTrue(total3 < total4);
        assertTrue(total4 < total5);

        // check that the totals increase by 250 per activity level
        assertEquals(1706, total1);
        assertEquals(1956, total2);
        assertEquals(2206, total3);
        assertEquals(2456, total4);
        assertEquals(2706, total5);
    }

    @Test
    public void getObjectiveTest() {
        calc.setObjective("maintain");
        assertEquals("maintain", calc.getObjective());
        calc.setObjective("gain");
        assertEquals("gain", calc.getObjective());
        calc.setObjective("lose");
        assertEquals("lose", calc.getObjective());
    }
}
