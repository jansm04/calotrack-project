package model;

import org.json.JSONObject;
import persistence.Writable;

// Date class: keeps track of the date of a CalorieLog
public class Date implements Writable {

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

    @Override
    public JSONObject toJSonObject() {
        JSONObject json = new JSONObject();
        json.put("day", day);
        json.put("month", month);
        json.put("year", year);
        return json;
    }
}
