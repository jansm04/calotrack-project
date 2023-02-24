package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DateTest {

    Date date;
    int day = 1;
    String month = "January";
    int year = 2023;

    @BeforeEach
    public void setup() {
        int day = 1;
        String month = "January";
        int year = 2023;
        date = new Date(day, month, year);
    }

    @Test
    public void constructorTest() {
        assertEquals(1, date.getDay());
        assertEquals("January", date.getMonth());
        assertEquals(2023, date.getYear());
    }

}
