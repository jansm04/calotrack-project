package model;

// Date class: keeps track of the date of a CalorieLog
public class Date {

    int day;
    String month;
    int year;

    // EFFECTS: constructs a date with a day, month and year
    public Date(int day, String month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
}
