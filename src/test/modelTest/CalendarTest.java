package modelTest;

import ui.CalorieLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class CalendarTest {

    Calendar calendar;
    CalorieLog log1;
    CalorieLog log2;
    CalorieLog log3;

    @BeforeEach
    public void setup() {
        calendar = new Calendar();
        log1 = new CalorieLog();
        log2 = new CalorieLog();
        log3 = new CalorieLog();
    }

    @Test
    public void addOneLogToEmptyCalendar() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
    }

    @Test
    public void addSameLogTwice() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        calendar.addEntry(log1);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
    }

    @Test
    public void addTwoDifferentLogs() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        calendar.addEntry(log2);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
        assertTrue(calendar.getDays().contains(log2));
    }

    @Test
    public void removeOnlyLogInCalendar() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));

        calendar.removeEntry(0);
        assertEquals(0, calendar.getDays().size());
        assertFalse(calendar.getDays().contains(log1));
    }

    @Test
    public void removeLogWithNoIndex() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));

        calendar.removeEntry(3);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
    }

    @Test
    public void removeFirstLogInCalendar() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        calendar.addEntry(log2);
        calendar.addEntry(log3);
        assertEquals(3, calendar.getDays().size());

        calendar.removeEntry(0);
        assertEquals(2, calendar.getDays().size());
        assertFalse(calendar.getDays().contains(log1));
        assertTrue(calendar.getDays().contains(log2));
        assertTrue(calendar.getDays().contains(log3));

        assertEquals(log2, calendar.getDays().get(0));
        assertEquals(log3, calendar.getDays().get(1));
    }

    @Test
    public void removeLastLogInCalendar() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        calendar.addEntry(log2);
        calendar.addEntry(log3);
        assertEquals(3, calendar.getDays().size());

        calendar.removeEntry(2);
        assertEquals(2, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
        assertTrue(calendar.getDays().contains(log2));
        assertFalse(calendar.getDays().contains(log3));

        assertEquals(log1, calendar.getDays().get(0));
        assertEquals(log2, calendar.getDays().get(1));
    }

    @Test
    public void removeOneOfSameTwoLogs() {
        assertEquals(0, calendar.getDays().size());
        calendar.addEntry(log1);
        calendar.addEntry(log1);
        assertEquals(2, calendar.getDays().size());

        calendar.removeEntry(0);
        assertEquals(1, calendar.getDays().size());
        assertTrue(calendar.getDays().contains(log1));
        assertEquals(log1, calendar.getDays().get(0));
    }

}
