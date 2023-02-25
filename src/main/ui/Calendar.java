package ui;

import model.Calculator;
import model.CalorieLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Calendar class: allows the user to add an arbitrary number of CalorieLogs to a 'calendar'
public class Calendar {

    List<CalorieLog> days;
    Calculator calc;
    Scanner scanner;
    CalorieLog log;

    // EFFECTS: constructs a calendar with an empty list of entries
    public Calendar() {
        this.days = new ArrayList<>();
        this.calc = new Calculator();
        this.scanner = new Scanner(System.in);
    }


    // EFFECTS: prompts the user to calculate their required cals
    public void calculateCals() {
        System.out.println("Let's calculate your daily caloric requirement!");
        getInfo();
        getGoal();
        getResults();
    }

    // MODIFIES: this
    // EFFECTS: creates a new CalorieLog object and prompts user to add foods + see other options. Once the user
    //          is finished logging their food and calories, then the log gets added to the list of logs in the
    //          calendar
    public void logFoods() {
        log = new CalorieLog();
        while (true) {
            chooseNextOptionLog();
            char answer2 = scanner.next().charAt(0);
            if (answer2 == 'n') {
                optionN();
            } else if (answer2 == 'r') {
                optionR();
            } else if (answer2 == 's') {
                optionS();
            } else if (answer2 == 'd') {
                optionD();
            } else if (answer2 == 'v') {
                optionV();
            } else {
                addEntry(log);
                break;
            }
        }
    }

    // EFFECTS: prompt user to choose next option
    public void options() {
        while (true) {
            chooseNextOptionCalendar();
            char answer3 = scanner.next().charAt(0);
            if (answer3 == 'c') {
                getInfo();
                getGoal();
                getResults();
            } else if (answer3 == 'd') {
                logFoods();
            } else if (answer3 == 'v') {
                viewCalendar();
            } else if (answer3 == 'i') {
                viewCalendar();
                System.out.println("Please enter the index of the log you would like to view:");
                int index = scanner.nextInt();
                viewLogInCalendar(index);
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: gets the user's physical information
    private void getInfo() {
        System.out.println("What is your gender? (Enter either \"male\" or \"female\")");
        String gender = scanner.next();
        calc.setGender(gender);

        System.out.println("What is your age?");
        int age = scanner.nextInt();
        calc.setAge(age);

        System.out.println("What is your height in cm?");
        int height = scanner.nextInt();
        calc.setHeight(height);
    }

    // MODIFIES: this
    // EFFECTS: gets the user's goal + details
    private void getGoal() {
        System.out.println("What is your weight in lbs?");
        double weight = scanner.nextDouble();
        calc.setWeight(weight);

        System.out.println("What is your weekly level of activity? (Enter the corresponding number from 1-5)"
                + "\n1 - None (Exercise 0 times a week)"
                + "\n2 - Light (Exercise 1-2 times a week)"
                + "\n3 - Moderate (Exercise 3-5 times a week)"
                + "\n4 - Hard (Exercise 6-7 times a week)"
                + "\n5 - Extreme (Exercise over 7 times a week)");
        int levelOfActivity = scanner.nextInt();
        calc.setLevelOfActivity(levelOfActivity);

        System.out.println("Would you like to gain, lose or maintain your current weight? "
                + "(Enter either \"gain\", \"lose\" or \"maintain\")");
        String objective = scanner.next();
        calc.setObjective(objective);

        if (!objective.equals("maintain")) {
            System.out.println("How much weight would you like to " + objective + "? (In lbs)");
            double weightGoal = scanner.nextDouble();
            calc.setWeightGoal(weightGoal);
            System.out.println("In how many weeks would you like to achieve this goal?");
            int weeks = scanner.nextInt();
            calc.setTime(weeks);
        }
    }

    // EFFECTS: prints results of goal info and calculations
    private void getResults() {
        System.out.println("Your daily caloric requirement is...");
        System.out.println(calc.totalDailyCaloricRequirement() + " cals!");
    }

    //EFFECTS: print next options for logging food
    private void chooseNextOptionLog() {
        System.out.println();
        System.out.println("Choose an option: "
                + "\nn - new food"
                + "\nr - remove a food"
                + "\ns - set today's weight"
                + "\nd - set today's date"
                + "\nv - view current log"
                + "\nq - end today's log");
    }

    // MODIFIES: log
    // EFFECTS: collects new food + calories
    private void optionN() {
        System.out.print("Food: ");
        String food = scanner.next();
        log.addFood(food);
        System.out.print("Calories: ");
        int calNum = scanner.nextInt();
        log.addNumOfCals(calNum);
    }

    // MODIFIES: log
    // EFFECTS: prompts user to remove food at specific index
    private void optionR() {
        viewLog(log);
        System.out.println("Enter the index of the food you would like to remove: ");
        int index = scanner.nextInt();
        log.removeFoodAndCals(index);
    }

    // MODIFIES: log
    // EFFECTS: collects users weight
    private void optionS() {
        System.out.print("Today's weight: ");
        double weight = scanner.nextDouble();
        log.setWeight(weight);
    }

    // MODIFIES: log
    // EFFECTS: prompts the user to enter the date and then sets the date for the current log
    private void optionD() {
        System.out.print("Month: ");
        String month = scanner.next();
        System.out.print("Day: ");
        int day = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        log.setDate(day, month, year);
    }

    // EFFECTS: prints log as a 3xn table, where n is the number of foods in the list
    private void optionV() {
        viewLog(log);
    }

    // EFFECTS: prints out next options in calendar
    private void chooseNextOptionCalendar() {
        System.out.println();
        System.out.println("Choose an option: "
                + "\nc - calculate new daily caloric requirement"
                + "\nd - new daily log"
                + "\nv - view calendar"
                + "\ni - view a past log"
                + "\nq - quit program");
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

    // EFFECTS: prints "YES" if goal was met, "NO" if not
    private String outcome(CalorieLog log) {
        String outcome = "";
        if (calc.getObjective().equals("gain") || calc.getObjective().equals("maintain")) {
            // goal is met if total for the day exceeds daily required amount of cals
            if (log.totalCals() >= calc.totalDailyCaloricRequirement()) {
                outcome = "YES";
            } else {
                outcome = "NO";
            }
        } else if (calc.getObjective().equals("lose")) {
            // goal is met if total for the day is below daily cal limit
            if (log.totalCals() <= calc.totalDailyCaloricRequirement()) {
                outcome = "YES";
            } else {
                outcome = "NO";
            }
        }
        return outcome;
    }

    // EFFECTS: prints out correct amount of space between columns. NOTE: this method is just for phase 1, so the
    //          table looks cleaner when printed in the console
    private String space(String s, int spacing) {
        int length = spacing - s.length();
        String tab = "";
        for (int i = 0; i < length; i++) {
            tab += " ";
        }
        return tab;
    }

    // EFFECTS: prints out the calendar (a list of all the calorie logs)
    public void viewCalendar() {
        System.out.println("Entry #      Total Calories     Reached Goal      Weight");
        for (int i = 0; i < days.size(); i++) {
            String weight = "";
            if (days.get(i).getWeight() != 0) {
                weight = Double.toString(days.get(i).getWeight());
            }
            System.out.println(i + "            "
                    + days.get(i).totalCals() + space(Integer.toString(days.get(i).totalCals()), 19)
                    + outcome(days.get(i)) + space(outcome(days.get(i)), 18)
                    + weight);
        }
    }

    // EFFECTS: prints out log for selected day
    public void viewLogInCalendar(int index) {
        for (int i = 0; i < days.size(); i++) {
            if (index == i) {
                viewLog(days.get(i));
                break;
            }
        }
    }

    // EFFECTS: prints out the current log as a 3xn table, where n is the number of elements,
    //          the first column shows the index number, the second column shows the food, and
    //          the third column shows the calories. Below the table, the total calories for
    //          day is included
    public void viewLog(CalorieLog log) {
        System.out.println("#       Food                Calories");
        for (int i = 0; i < log.getFoods().size(); i++) {
            System.out.println(i + "       "
                    + log.getFoods().get(i)
                    + space(log.getFoods().get(i), 20)
                    + log.getCals().get(i));
        }
        System.out.println("Total Cals: " + log.totalCals());
        if (log.getWeight() != 0) {
            System.out.println("Weight: " + log.getWeight() + " lbs.");
        }
        if (log.getDate() != null) {
            System.out.println("Date: "
                    + log.getDate().getMonth() + " "
                    + log.getDate().getDay() + ", "
                    + log.getDate().getYear());
        }

    }

    public List<CalorieLog> getDays() {
        return days;
    }
}
