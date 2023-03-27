package persistence;

import model.Calendar;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// JsonWriter class: writes a calendar into a file stored in json data
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs a JsonWriter
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer and throws FileNotFoundException if destination file cannot
    //          be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of calendar to file
    public void write(Calendar calendar) {
        JSONObject json = calendar.toJSonObject();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }


}
