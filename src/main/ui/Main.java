package ui;

import model.Calculator;
import model.CalorieLog;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    public static Calculator calc = new Calculator();
    private static final Calendar calendar = new Calendar();

    public static void main(String[] args) {

        System.out.println("Let's calculate your daily caloric requirement!");
        getInfo();
        getGoal();
        getResults();

        System.out.println("Now that we have your daily caloric requirement, " +
                "would you like to start keeping track of your calories?");
        String answer = scanner.next();

        if (answer.equals("yes")) {
            logFoods();
        } else {
            System.out.println("You've completed the introduction!");
        }
        options();

    }


    // EFFECTS: gets the user's physical information
    private static void getInfo() {
        System.out.println("What is your gender? (Enter either \"male\" or \"female\")");
        String gender = scanner.next();
        calc.setGender(gender);

        System.out.println("What is your age?");
        int age = scanner.nextInt();
        calc.setAge(age);

        System.out.println("What is your height?");
        System.out.print("Feet: ");
        int heightFeet = scanner.nextInt();
        calc.setHeightFeet(heightFeet);
        System.out.print("Inches: ");
        int heightInches = scanner.nextInt();
        calc.setHeightInches(heightInches);
    }


    // EFFECTS: gets the user's goal + details
    private static void getGoal() {
        System.out.println("What is your weight in lbs?");
        double weight = scanner.nextDouble();
        calc.setWeight(weight);

        System.out.println("What is your weekly level of activity? (Enter the corresponding number from 1-5)" +
                "\n1 - None (Exercise 0 times a week)" +
                "\n2 - Light (Exercise 1-2 times a week)" +
                "\n3 - Moderate (Exercise 3-5 times a week)" +
                "\n4 - Hard (Exercise 6-7 times a week)" +
                "\n5 - Extreme (Exercise over 7 times a week)");
        int levelOfActivity = scanner.nextInt();
        calc.setLevelOfActivity(levelOfActivity);

        System.out.println("Would you like to gain, lose or maintain your current weight? " +
                "(Enter either \"gain\", \"lose\" or \"maintain\")");
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
    private static void getResults() {
        System.out.println("Your daily caloric requirement is...");
        System.out.println(calc.totalDailyCaloricRequirement() + " cals!");
    }

    // EFFECTS: creates a new CalorieLog object and prompts user to add foods
    private static void logFoods() {
        CalorieLog log = new CalorieLog();
        while (true) {
            System.out.println("Choose an option: " +
                    "\nn - new food" +
                    "\nr - remove a food" +
                    "\ns - set today's weight" +
                    "\nv - view current log" +
                    "\nq - end today's log");
            char answer2 = scanner.next().charAt(0);
            if (answer2 == 'n') {
                System.out.print("Food: ");
                String food = scanner.next();
                log.addFood(food);
                System.out.print("Calories: ");
                int calNum = scanner.nextInt();
                log.addNumOfCals(calNum);
            } else if (answer2 == 'r') {
                log.viewLog();
                System.out.println("Enter the index of the food you would like to remove: ");
                int index = scanner.nextInt();
                log.removeFoodAndCals(index);
            } else if (answer2 == 's') {
                System.out.print("Today's weight: ");
                double weight = scanner.nextDouble();
                log.setWeight(weight);
            } else if (answer2 == 'v') {
                log.viewLog();
            } else {
                calendar.addEntry(log);
                break;
            }
        }
    }

    // EFFECTS: prompt user to choose next option
    private static void options() {
        while (true) {
            System.out.println("Choose an option: " +
                    "\nc - calculate new daily caloric requirement" +
                    "\nd - new daily log" +
                    "\nv - view calendar" +
                    "\ni - view a past log" +
                    "\nq - quit program");
            char answer3 = scanner.next().charAt(0);
            if (answer3 == 'c') {
                getInfo();
                getGoal();
                getResults();
            } else if (answer3 == 'd') {
                logFoods();
            } else if (answer3 == 'v') {
                calendar.viewCalendar();
            } else if (answer3 == 'i') {
                System.out.println("Please enter the index of the log you would like to view:");
                int index = scanner.nextInt();
                calendar.viewLogInCalendar(index);
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }
}
