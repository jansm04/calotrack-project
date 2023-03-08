package persistence;

import model.Calculator;
import model.CalorieLog;
import model.Date;
import org.junit.jupiter.api.Test;
import ui.Calendar;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void fileInvalidTest() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0invalidFileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException ignored) {
            //pass
        }
    }

    @Test
    void writeEmptyCalendarTest() {
        try {
            Calendar calendar = new Calendar();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyCalendar.json");
            calendar = reader.read();
            assertEquals(0, calendar.getDays().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void writeGeneralCalendarTest() {
        try {
            Calendar calendar = new Calendar();
            calendar.addEntry(createDay1());
            calendar.setCalc(createCalculator());
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralCalendar.json");
            writer.open();
            writer.write(calendar);
            writer.close();

            JsonReader reader = new JsonReader("./data/testReaderGeneralCalendar.json");
            calendar = reader.read();
            List<CalorieLog> days = calendar.getDays();
            assertEquals(2, days.size());
            List<String> foods = new LinkedList<>();
            foods.add("apple");
            foods.add("banana");
            List<Integer> cals = new LinkedList<>();
            cals.add(100);
            cals.add(150);
            Date date = new Date(21, "April", 2023);

            checkCalorieLog(foods, cals, 150, date, days.get(0));
            checkCalculator(calendar.getCalculator());

        } catch (IOException ignored) {
            fail("Exception should not have been thrown");
        }
    }

    private Calculator createCalculator() {
        Calculator calc = new Calculator();
        calc.setGender("male");
        calc.setAge(18);
        calc.setHeight(175);
        calc.setWeight(150);
        calc.setWeightGoal(20);
        calc.setLevelOfActivity(3);
        calc.setObjective("gain");
        calc.setTime(12);
        return calc;
    }

    private CalorieLog createDay1() {
        CalorieLog day1 = new CalorieLog();
        day1.addFood("apple");
        day1.addNumOfCals(100);
        day1.addFood("pasta");
        day1.addNumOfCals(500);
        day1.setWeight(150);
        day1.setDate(21, "April", 2023);
        return day1;
    }



}
