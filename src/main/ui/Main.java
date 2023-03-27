package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.Scanner;

// Main class: creates new calendar object
public class Main {

    public static void main(String[] args) throws IOException {

        GUI gui = new GUI();
        gui.openOptionsPanel();

    }

}