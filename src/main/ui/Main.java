package ui;

import java.util.Scanner;

// Main class: creates new calendar object
public class Main {

    public static void main(String[] args) {
        Calendar calendar = new Calendar();
        Scanner scanner = new Scanner(System.in);
        calendar.calculateCals();
        System.out.println("Now that we have your daily caloric requirement, "
                + "would you like to start keeping track of your calories? (Enter \"yes\" or \"no\")");
        String answer = scanner.next();
        if (answer.equals("yes")) {
            calendar.logFoods();
        } else {
            System.out.println("You've completed the introduction!");
        }
        calendar.options();

    }

}