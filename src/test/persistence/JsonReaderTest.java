package persistence;

import model.Calendar;
import model.CalorieLog;
import model.Date;
import org.junit.jupiter.api.Test;
import ui.GUI;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nonExistentFile.json");
        try {
            Calendar calendar = reader.read();
            fail("IOException expected");
        } catch (IOException ignored) {
        }
    }

    @Test
    void testReaderEmptyCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyCalendar.json");
        try {
            Calendar calendar = reader.read();
            assertEquals(0, calendar.getDays().size());
            checkCalculator(calendar.getCalculator());
        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

    @Test
    void testReaderGeneralCalendar() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralCalendar.json");
        try {
            Calendar calendar = reader.read();
            List<CalorieLog> days = calendar.getDays();
            List<String> foods = new LinkedList<>();
            foods.add("apple");
            foods.add("banana");
            List<Integer> cals = new LinkedList<>();
            cals.add(100);
            cals.add(150);
            Date date = new Date(21, "April", 2023);

            List<String> foods2 = new LinkedList<>();
            foods2.add("steak");
            foods2.add("sandwich");
            List<Integer> cals2 = new LinkedList<>();
            cals2.add(700);
            cals2.add(450);
            Date date2 = new Date(22, "April", 2023);

            assertEquals(2, days.size());
            checkCalorieLog(foods, cals, 150, date, days.get(0));
            checkCalorieLog(foods2, cals2, 151.2, date2, days.get(1));
            checkCalculator(calendar.getCalculator());

        } catch (IOException e) {
            fail("Failed to read from file");
        }
    }

}
