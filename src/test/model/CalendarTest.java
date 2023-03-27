package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class CalendarTest {

    Calendar calendar;
    CalorieLog day1;
    CalorieLog day2;

    @BeforeEach
    public void setup() {
        calendar = new Calendar();
        day1 = new CalorieLog();
        day2 = new CalorieLog();
    }

    @Test
    public void addFirstEntryTest() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(day1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
    }

    @Test
    public void addTwoEntriesTest() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(day1);
        assertEquals(1, calendar.getDays().size());
        calendar.addEntry(day2);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
        assertTrue(calendar.getDays().contains(day2));
    }

    @Test
    public void removeOnlyEntryTest() {
        calendar.addEntry(day1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
        calendar.removeEntry(0);
        assertEquals(0, calendar.getDays().size());
        assertFalse(calendar.getDays().contains(day1));
    }

    @Test
    public void removeSecondLastEntryTest() {
        calendar.addEntry(day1);
        calendar.addEntry(day2);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
        assertTrue(calendar.getDays().contains(day2));
        calendar.removeEntry(1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
        assertFalse(calendar.getDays().contains(day2));
    }

    @Test
    public void removeFirstOfTwoEntriesTest() {
        calendar.addEntry(day1);
        calendar.addEntry(day2);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day1));
        assertTrue(calendar.getDays().contains(day2));
        calendar.removeEntry(0);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(day2));
        assertFalse(calendar.getDays().contains(day1));
    }

}
