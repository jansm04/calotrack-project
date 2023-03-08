package persistence;

import model.Calculator;
import model.CalorieLog;
import model.Date;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {

    protected void checkCalorieLog(List<String> foods, List<Integer> cals, double weight,
                                   Date date, CalorieLog calorieLog) {
        assertEquals(foods, calorieLog.getFoods());
        assertEquals(cals, calorieLog.getCals());
        assertEquals(weight, calorieLog.getWeight());
        checkDate(date, calorieLog.getDate());
    }

    private void checkDate(Date testDate, Date dateFromFile) {
        assertEquals(testDate.getDay(), dateFromFile.getDay());
        assertEquals(testDate.getMonth(), dateFromFile.getMonth());
        assertEquals(testDate.getYear(), dateFromFile.getYear());
    }

    protected void checkCalculator(Calculator calculator) {
        assertEquals("male", calculator.getGender());
        assertEquals(18, calculator.getAge());
        assertEquals(175, calculator.getHeight());
        assertEquals(150, calculator.getWeight());
        assertEquals(20, calculator.getWeightGoal());
        assertEquals(3, calculator.getLevelOfActivity());
        assertEquals("gain", calculator.getObjective());
        assertEquals(12, calculator.getTime());
    }
}
