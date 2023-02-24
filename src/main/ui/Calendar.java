package ui;

import java.util.ArrayList;
import java.util.List;

import static ui.Main.calc;

// Calendar class: allows the user to add an arbitrary number of CalorieLogs to a 'calendar'
public class Calendar {

    List<CalorieLog> days;

    public Calendar() {
        this.days = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a CalorieLog to the list of CalorieLogs
    public void addEntry(CalorieLog log) {
        days.add(log);
    }

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

    // EFFECTS: prints "YES" if goal was met, "NO" if not
    private String outcome(CalorieLog log) {
        String outcome = "";
        if (calc.getObjective().equals("gain") || calc.getObjective().equals("maintain")) {
            if (log.totalCals() >= calc.totalDailyCaloricRequirement()) {
                outcome = "YES";
            } else {
                outcome = "NO";
            }
        } else if (calc.getObjective().equals("lose")) {
            if (log.totalCals() <= calc.totalDailyCaloricRequirement()) {
                outcome = "YES";
            } else {
                outcome = "NO";
            }
        }
        return outcome;
    }

    // EFFECTS: prints out correct amount of space between columns
    private String space1(String s) {
        int length = 19 - s.length();
        String tab = "";
        for (int i = 0; i < length; i++) {
            tab += " ";
        }
        return tab;
    }

    // EFFECTS: prints out correct amount of space between columns
    private String space2(String s) {
        int length = 10 - s.length();
        String tab = "";
        for (int i = 0; i < length; i++) {
            tab += " ";
        }
        return tab;
    }

    // EFFECTS: prints out the calendar (a list of all the calorie logs)
    public void viewCalendar() {
        System.out.println("Entry #      Total Calories     Goal      Weight");
        for (int i = 0; i < days.size(); i++) {
            String weight = "";
            if (days.get(i).getWeight() != 0) {
                weight = Double.toString(days.get(i).getWeight());
            }
            System.out.println(i + "            " +
                    days.get(i).totalCals() + space1(Integer.toString(days.get(i).totalCals())) +
                    outcome(days.get(i)) + space2(outcome(days.get(i))) +
                    weight);
        }
    }

    // EFFECTS: prints out log for selected day
    public void viewLogInCalendar(int index) {
        for (int i = 0; i < days.size(); i++) {
            if (index == i) {
                days.get(i).viewLog();
                break;
            }
        }
    }

    public List<CalorieLog> getDays() {
        return days;
    }
}
