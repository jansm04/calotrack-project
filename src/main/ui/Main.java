package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

// Main class: creates new calendar object
public class Main {

    private static final String JSON_STORE = "./data/calendar.json";
    private static final JsonWriter WRITER = new JsonWriter(JSON_STORE);
    private static final JsonReader READER = new JsonReader(JSON_STORE);
    private static final Scanner SCANNER = new Scanner(System.in);
    private static GUI gui;

    public static void main(String[] args) throws IOException {

        gui = new GUI();
        gui.openOptionsPanel();

    }

}