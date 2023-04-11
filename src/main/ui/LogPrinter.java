package ui;

import model.Event;
import model.EventLog;

// LogPrinter class: prints every Event in EventLog
public class LogPrinter {

    // EFFECTS: prints the Events in EventLog
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.toString() + "\n");
        }
    }

}
