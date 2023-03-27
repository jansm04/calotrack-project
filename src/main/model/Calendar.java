package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Calendar class: allows the user to add an arbitrary number of CalorieLogs to a 'calendar'
public class Calendar implements Writable {

    private Calculator calc;
    private List<CalorieLog> days;

    // MODIFIES: this
    // EFFECTS: constructs a calendar with an empty list of entries
    public Calendar() {
        this.days = new ArrayList<>();
        this.calc = new Calculator();
    }


    // MODIFIES: this
    // EFFECTS: adds a CalorieLog to the list of CalorieLogs
    public void addEntry(CalorieLog log) {
        days.add(log);
    }

    // REQUIRES: 0 <= index <= days.size()
    // MODIFIES: this
    // EFFECTS: removes a CalorieLog from the list of CalorieLogs
    public void removeEntry(int index) {
        for (int i = 0; i < days.size(); i++) {
            if (i == index) {
                days.remove(i);
                break;
            }
        }
    }


    public List<CalorieLog> getDays() {
        return days;
    }

    public Calculator getCalculator() {
        return calc;
    }

    // EFFECTS: converts a calendar to a Json object
    @Override
    public JSONObject toJSonObject() {
        JSONObject json = new JSONObject();
        json.put("days", daysToJson());
        json.put("calculator", calc.toJSonObject());
        return json;
    }

    // EFFECTS: returns days in this calendar as a JSON array
    private JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CalorieLog calorieLog : days) {
            jsonArray.put(calorieLog.toJSonObject());
        }

        return jsonArray;
    }

    public void setCalc(Calculator calc) {
        this.calc = calc;
    }


}
