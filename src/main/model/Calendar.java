package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Calendar class: allows the user to add an arbitrary number of CalorieLogs to a 'calendar'
public class Calendar implements Writable {

    private final List<CalorieLog> days;

    private static final Calendar calendar = new Calendar();

    public static Calendar getInstance() {
        return calendar;
    }

    // MODIFIES: this
    // EFFECTS: constructs a calendar with an empty list of entries
    private Calendar() {
        this.days = new ArrayList<>();
    }


    // MODIFIES: this
    // EFFECTS: adds a CalorieLog to the list of CalorieLogs
    public void addEntry(CalorieLog log) {
        days.add(log);
        EventLog.getInstance().logEvent(new Event("Added CalorieLog: Day "
                + days.size()
                + " (" + log.totalCals() + " Cals)"));
    }

    // REQUIRES: 0 <= index <= days.size()
    // MODIFIES: this
    // EFFECTS: removes a CalorieLog from the list of CalorieLogs
    public void removeEntry(int index) {
        for (int i = 0; i < days.size(); i++) {
            if (i == index) {
                EventLog.getInstance().logEvent(new Event("Removed CalorieLog: Day "
                        + (i + 1)
                        + " (" + days.get(i).totalCals() + " Cals)"));
                days.remove(i);
                break;
            }
        }
    }


    public List<CalorieLog> getDays() {
        return days;
    }


    // EFFECTS: converts a calendar to a Json object
    @Override
    public JSONObject toJSonObject() {
        JSONObject json = new JSONObject();
        json.put("days", daysToJson());
        json.put("calculator", Calculator.getInstance().toJSonObject());
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



}
