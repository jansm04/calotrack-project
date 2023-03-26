package ui;

import model.Calculator;
import model.CalorieLog;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Calendar class: allows the user to add an arbitrary number of CalorieLogs to a 'calendar'
public class Calendar implements Writable {

    List<CalorieLog> days;
    Calculator calc;
    Scanner scanner;
    CalorieLog log;
    JFrame jframe;
    JPanel calcPanel;
    JPanel optionsPanel;
    JPanel logPanel;
    JPanel calendarPanel;

    JButton enterObjective;
    JButton enterSpecifics;

    JTextField genderAnswer;
    JTextField ageAnswer;
    JTextField heightAnswer;
    JTextField weightAnswer;
    JTextField loaAnswer;
    JTextField objectiveAnswer;

    JLabel weightGoalQuestion;
    JLabel timeQuestion;
    JTextField weightGoalAnswer;
    JTextField timeAnswer;

    String gender;
    int age;
    double height;
    double weight;
    double weightGoal;
    int levelOfActivity;
    String objective;
    int time;

    JButton startCalculator;
    JButton newDailyLog;
    JButton viewCalendar;
    JButton saveCalendar;

    JLabel result = new JLabel("Daily Caloric Requirement: ");


    // MODIFIES: this
    // EFFECTS: constructs a calendar with an empty list of entries
    public Calendar() {
        this.days = new ArrayList<>();
        this.calc = new Calculator();
        this.scanner = new Scanner(System.in);
        this.jframe = new JFrame();
        this.calcPanel = new JPanel();
        this.optionsPanel = new JPanel();
        this.logPanel = new JPanel();
        this.calendarPanel = new JPanel();
        setupJFrame();
    }

    // MODIFIES: this
    // EFFECTS: sets up the JFrame object
    public void setupJFrame() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("DietKing");
        jframe.pack();
        jframe.setSize(1000, 700);
    }



    // MODIFIES: this
    // EFFECTS: sets up the calculator JPanel
    public void openOptionsPanel() {
        optionsPanel.setBorder(BorderFactory.createEmptyBorder());
        optionsPanel.setBackground(Color.orange);
        optionsPanel.setLayout(null);

        calcPanel.setVisible(false);
        logPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(true);

        jframe.add(optionsPanel);

        addButtons();

        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to optionsPanel
    public void addButtons() {
        calculatorButton();
        newDailyLogButton();
    }

    // MODIFIES: this
    // EFFECTS: opens calcPanel
    public void calculatorButton() {
        startCalculator = new JButton("Calculate Daily Caloric Requirement");
        startCalculator.setBounds(250, 100, 500, 40);
        startCalculator.addActionListener(e -> {
            openCalculatorPanel();
        });
        optionsPanel.add(startCalculator);
    }

    // MODIFIES: this
    // EFFECTS: opens logPanel
    public void newDailyLogButton() {
        newDailyLog = new JButton("New Daily Log");
        newDailyLog.setBounds(250, 150, 500, 40);
        newDailyLog.addActionListener(e -> {
            openLogPanel();
        });
        optionsPanel.add(newDailyLog);
    }






    // MODIFIES: this
    // EFFECTS: sets up the calculator JPanel
    public void openCalculatorPanel() {
        calcPanel.setBorder(BorderFactory.createEmptyBorder());
        calcPanel.setBackground(Color.getHSBColor(50, 20, 140));
        calcPanel.setLayout(null);

        logPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calcPanel.setVisible(true);


        result.setBounds(500, 500, 400, 30);
        calcPanel.add(result);

        jframe.add(calcPanel);
        getInfo();
        enterButton1();
        enterButton2();
        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up the daily log tracker
    public void openLogPanel() {
        logPanel.setBorder(BorderFactory.createEmptyBorder());
        logPanel.setBackground(Color.pink);
        logPanel.setLayout(null);

        calcPanel.setVisible(false);
        logPanel.setVisible(true);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(false);

        jframe.add(logPanel);

        jframe.setVisible(true);
    }









    // MODIFIES: this
    // EFFECTS: gets the user's physical information
    public void getInfo() {
        genderPrompt();
        agePrompt();
        heightPrompt();
        weightPrompt();
        loaPrompt();
        objectivePrompt();
        weightGoalPrompt();
        timePrompt();
    }


    // MODIFIES: this
    // EFFECTS: prompts user to enter gender
    public void genderPrompt() {
        JLabel genderQuestion = new JLabel("What is your gender? (Enter either \"male\" or \"female\")");
        genderQuestion.setBounds(50, 50, 400, 30);
        calcPanel.add(genderQuestion);

        genderAnswer = new JTextField(20);
        genderAnswer.setBounds(50, 80, 200, 30);
        calcPanel.add(genderAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter age
    public void agePrompt() {
        JLabel ageQuestion = new JLabel("What is your age?");
        ageQuestion.setBounds(50, 120, 400, 30);
        calcPanel.add(ageQuestion);

        ageAnswer = new JTextField(20);
        ageAnswer.setBounds(50, 150, 200, 30);
        calcPanel.add(ageAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter height
    public void heightPrompt() {
        JLabel heightQuestion = new JLabel("What is your height (in cm)?");
        heightQuestion.setBounds(50, 190, 400, 30);
        calcPanel.add(heightQuestion);

        heightAnswer = new JTextField(20);
        heightAnswer.setBounds(50, 220, 200, 30);
        calcPanel.add(heightAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weight
    public void weightPrompt() {
        JLabel weightQuestion = new JLabel("What is your weight (in kg)?");
        weightQuestion.setBounds(50, 260, 400, 30);
        calcPanel.add(weightQuestion);

        weightAnswer = new JTextField(20);
        weightAnswer.setBounds(50, 290, 200, 30);
        calcPanel.add(weightAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter levelOfActivity
    public void loaPrompt() {
        JLabel loaQuestion1 = new JLabel("What is your weekly level of activity?");
        JLabel loaQuestion2 = new JLabel("(Enter the corresponding number from 1-5)");
        JLabel loaQuestion3 = new JLabel("1 - None (Exercise 0 times a week)");
        JLabel loaQuestion4 = new JLabel("2 - Light (Exercise 1-2 times a week)");
        JLabel loaQuestion5 = new JLabel("3 - Moderate (Exercise 3-5 times a week)");
        JLabel loaQuestion6 = new JLabel("4 - Hard (Exercise 6-7 times a week)");
        JLabel loaQuestion7 = new JLabel("5 - Extreme (Exercise over 7 times a week)");
        loaQuestion1.setBounds(50, 330, 400, 20);
        loaQuestion2.setBounds(50, 350, 400, 20);
        loaQuestion3.setBounds(70, 370, 400, 20);
        loaQuestion4.setBounds(70, 390, 400, 20);
        loaQuestion5.setBounds(70, 410, 400, 20);
        loaQuestion6.setBounds(70, 430, 400, 20);
        loaQuestion7.setBounds(70, 450, 400, 20);
        calcPanel.add(loaQuestion1);
        calcPanel.add(loaQuestion2);
        calcPanel.add(loaQuestion3);
        calcPanel.add(loaQuestion4);
        calcPanel.add(loaQuestion5);
        calcPanel.add(loaQuestion6);
        calcPanel.add(loaQuestion7);

        loaAnswer = new JTextField(20);
        loaAnswer.setBounds(50, 480, 200, 30);
        calcPanel.add(loaAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter objective
    public void objectivePrompt() {
        JLabel objectiveQuestion1 = new JLabel("Would you like to gain, lose or maintain your current weight?");
        JLabel objectiveQuestion2 = new JLabel("(Enter either \"gain\", \"lose\" or \"maintain\")");
        objectiveQuestion1.setBounds(500, 50, 400, 20);
        objectiveQuestion2.setBounds(500, 70, 400, 20);
        calcPanel.add(objectiveQuestion1);
        calcPanel.add(objectiveQuestion2);

        objectiveAnswer = new JTextField(20);
        objectiveAnswer.setBounds(500, 100, 200, 30);
        calcPanel.add(objectiveAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weightGoal
    public void weightGoalPrompt() {
        weightGoalQuestion = new JLabel("How much weight would you like to gain/lose? (In kg)");
        weightGoalQuestion.setBounds(500, 160, 400, 30);
        weightGoalQuestion.setVisible(false);
        calcPanel.add(weightGoalQuestion);

        weightGoalAnswer = new JTextField(20);
        weightGoalAnswer.setBounds(500, 190, 200, 30);
        weightGoalAnswer.setVisible(false);
        calcPanel.add(weightGoalAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter time
    public void timePrompt() {
        timeQuestion = new JLabel("In how many weeks would you like to achieve this goal?");
        timeQuestion.setBounds(500, 230, 400, 30);
        timeQuestion.setVisible(false);
        calcPanel.add(timeQuestion);

        timeAnswer = new JTextField(20);
        timeAnswer.setBounds(500, 260, 200, 30);
        timeAnswer.setVisible(false);
        calcPanel.add(timeAnswer);
    }

    // MODIFIES: this
    // EFFECTS: sets up the first "enter" button
    public void enterButton1() {
        enterObjective = new JButton("Enter");
        enterObjective.setBounds(800, 80, 90, 30);
        calcPanel.add(enterObjective);
        enterObjective.addActionListener(e -> {
            collectGoalAnswers();
            if (!objective.equals("maintain")) {
                weightGoalQuestion.setVisible(true);
                weightGoalAnswer.setVisible(true);
                timeQuestion.setVisible(true);
                timeAnswer.setVisible(true);
                enterSpecifics.setVisible(true);
            } else {
                result.setText("Daily Caloric Requirement: " + calc.totalDailyCaloricRequirement() + " Cals");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for goal
    public void collectGoalAnswers() {
        gender = genderAnswer.getText();
        calc.setGender(gender);
        age = Integer.parseInt(ageAnswer.getText());
        calc.setAge(age);
        height = Integer.parseInt(heightAnswer.getText());
        calc.setHeight(height);
        weight = Integer.parseInt(weightAnswer.getText());
        calc.setWeight(weight);
        levelOfActivity = Integer.parseInt(loaAnswer.getText());
        calc.setLevelOfActivity(levelOfActivity);
        objective = objectiveAnswer.getText();
        calc.setObjective(objective);
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for specifics
    public void collectSpecificsAnswers() {
        weightGoal = Integer.parseInt(weightGoalAnswer.getText());
        calc.setWeightGoal(weightGoal);
        time = Integer.parseInt(timeAnswer.getText());
        calc.setTime(time);
    }

    // MODIFIES: this
    // EFFECTS: sets up the second "enter" button
    public void enterButton2() {
        enterSpecifics = new JButton("Enter");
        enterSpecifics.setBounds(800, 300, 90, 30);
        enterSpecifics.setVisible(false);
        calcPanel.add(enterSpecifics);
        enterSpecifics.addActionListener(e -> {
            collectGoalAnswers();
            collectSpecificsAnswers();
            result.setText("Daily Caloric Requirement: " + calc.totalDailyCaloricRequirement() + " Cals");
        });

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





    // EFFECTS: prints results of goal info and calculations
    public void getResults() {
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
            System.out.println("Weight: " + log.getWeight() + " kg");
        }
        if (log.getDate().getDay() != 0) {
            System.out.println("Date: "
                    + log.getDate().getMonth() + " "
                    + log.getDate().getDay() + ", "
                    + log.getDate().getYear());
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
