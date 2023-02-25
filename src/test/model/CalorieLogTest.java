package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CalorieLogTest {

    CalorieLog log;

    @BeforeEach
    public void setup() {
        log = new CalorieLog();
    }

    @Test
    public void addOneFoodItemToEmptyList() {
        assertEquals(0, log.getFoods().size());
        log.addFood("Pasta");
        assertEquals(1, log.getFoods().size());
        assertTrue(log.getFoods().contains("Pasta"));
    }

    @Test
    public void addSameFoodItemTwice() {
        log.addFood("Pasta");
        log.addFood("Pasta");
        assertEquals(2, log.getFoods().size());
        assertTrue(log.getFoods().contains("Pasta"));
    }

    @Test
    public void addMultipleDifferentFoodItems() {
        log.addFood("Pasta");
        log.addFood("Chicken");
        assertEquals(2, log.getFoods().size());
        assertTrue(log.getFoods().contains("Pasta"));
        assertTrue(log.getFoods().contains("Chicken"));
    }

    @Test
    public void addOneCalNumToEmptyList() {
        assertEquals(0, log.getCals().size());
        log.addNumOfCals(300);
        assertEquals(1, log.getCals().size());
        assertTrue(log.getCals().contains(300));
    }

    @Test
    public void addSameCalNumTwice() {
        log.addNumOfCals(300);
        log.addNumOfCals(300);
        assertEquals(2, log.getCals().size());
        assertTrue(log.getCals().contains(300));
    }

    @Test
    public void addMultipleDifferentCalNums() {
        log.addNumOfCals(300);
        log.addNumOfCals(400);
        assertEquals(2, log.getCals().size());
        assertTrue(log.getCals().contains(300));
        assertTrue(log.getCals().contains(400));
    }

    @Test
    public void removeItemFromEmptyList() {
        log.removeFoodAndCals(0);
        assertEquals(0, log.getFoods().size());
    }

    @Test
    public void removeOnlyItemInList() {
        log.addFood("Pasta");
        log.addNumOfCals(300);
        log.removeFoodAndCals(0);
        assertEquals(0, log.getFoods().size());
        assertEquals(0, log.getCals().size());
    }

    @Test
    public void removeItemNotInList() {
        log.addFood("Pasta");
        log.addNumOfCals(300);
        log.removeFoodAndCals(3);
        assertEquals(1, log.getFoods().size());
        assertTrue(log.getFoods().contains("Pasta"));
        assertEquals(1, log.getCals().size());
        assertTrue(log.getCals().contains(300));
    }

    @Test
    public void removeFirstItemInBigList() {
        log.addFood("Pasta");
        log.addNumOfCals(300);
        log.addFood("Chicken");
        log.addNumOfCals(600);
        log.addFood("Apple");
        log.addNumOfCals(100);

        log.removeFoodAndCals(0);
        assertEquals(2, log.getFoods().size());
        assertFalse(log.getFoods().contains("Pasta"));
        assertEquals(2, log.getCals().size());
        assertFalse(log.getCals().contains(300));

        assertEquals("Chicken", log.getFoods().get(0));
        assertEquals(600, log.getCals().get(0));
        assertEquals("Apple", log.getFoods().get(1));
        assertEquals(100, log.getCals().get(1));
    }

    @Test
    public void removeLastItemInBigList() {
        log.addFood("Pasta");
        log.addNumOfCals(300);
        log.addFood("Chicken");
        log.addNumOfCals(600);
        log.addFood("Apple");
        log.addNumOfCals(100);

        log.removeFoodAndCals(2);
        assertEquals(2, log.getFoods().size());
        assertFalse(log.getFoods().contains("Apple"));
        assertEquals(2, log.getCals().size());
        assertFalse(log.getCals().contains(100));

        assertEquals("Pasta", log.getFoods().get(0));
        assertEquals(300, log.getCals().get(0));
        assertEquals("Chicken", log.getFoods().get(1));
        assertEquals(600, log.getCals().get(1));
    }

    @Test
    public void totalCalsTest() {
        assertEquals(0, log.totalCals());
        log.addFood("Pasta");
        log.addNumOfCals(300);
        log.addFood("Chicken");
        log.addNumOfCals(600);
        log.addFood("Apple");
        log.addNumOfCals(100);
        assertEquals(300 + 600 + 100, log.totalCals());
        log.removeFoodAndCals(0);
        assertEquals(600 + 100, log.totalCals());
    }

    @Test
    public void setWeightTest() {
        log.setWeight(153.2);
        assertEquals(153.2, log.getWeight());
    }

    @Test
    public void setDateTest() {
        log.setDate(1, "January", 2023);
        assertEquals(1, log.getDate().getDay());
        assertEquals("January", log.getDate().getMonth());
        assertEquals(2023, log.getDate().getYear());
    }
}
