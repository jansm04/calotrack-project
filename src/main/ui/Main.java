package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Main class: creates new calendar object
public class Main {

    private static final String JSON_STORE = "./data/calendar.json";
    private static final JsonWriter WRITER = new JsonWriter(JSON_STORE);
    private static final JsonReader READER = new JsonReader(JSON_STORE);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static Calendar calendar;

    public static void main(String[] args) throws IOException {


        System.out.println("Welcome! Please choose an option: ");
        System.out.println("c - new calendar"
                + "\nl - load last calendar");
        char answer = SCANNER.next().charAt(0);
        if (answer == 'c') {
            calendar = new Calendar();
            calendar.calculateCals();
            System.out.println("Now that we have your daily caloric requirement, "
                    + "would you like to start keeping track of your calories? (Enter \"yes\" or \"no\")");
            String answer2 = SCANNER.next();
            if (answer2.equals("yes")) {
                calendar.logFoods();

                options();
            } else {
                System.out.println("You've completed the introduction!");
            }
        } else {
            calendar = READER.read();
            options();
        }
    }

    // EFFECTS: prompt user to choose next option
    private static void options() throws FileNotFoundException {
        while (true) {
            chooseNextOptionCalendar();
            char answer3 = SCANNER.next().charAt(0);
            if (answer3 == 'c') {
                calendar.getInfo();
                calendar.getGoal();
                calendar.getResults();
            } else if (answer3 == 'd') {
                calendar.logFoods();
            } else if (answer3 == 'v') {
                calendar.viewCalendar();
            } else if (answer3 == 'i') {
                calendar.viewCalendar();
                System.out.println("Please enter the index of the log you would like to view:");
                int index = SCANNER.nextInt();
                calendar.viewLogInCalendar(index);
            } else if (answer3 == 's') {
                write();
            } else {
                System.out.println("Goodbye!");
                break;
            }
        }
    }

    // EFFECTS: prints out next options in calendar
    private static void chooseNextOptionCalendar() {
        System.out.println();
        System.out.println("Choose an option: "
                + "\nc - calculate new daily caloric requirement"
                + "\nd - new daily log"
                + "\nv - view calendar"
                + "\ni - view a past log"
                + "\ns - save calendar"
                + "\nq - quit program");
    }

    // EFFECTS: writes a new calendar
    private static void write() throws FileNotFoundException {
        WRITER.open();
        WRITER.write(calendar);
        WRITER.close();
        System.out.println("Your calendar has been saved!");
    }

}