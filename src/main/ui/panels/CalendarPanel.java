package ui.panels;

import model.Calendar;

import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import java.awt.*;


public class CalendarPanel extends JPanel implements PanelSetup {

    Calendar calendar;
    PastLogPanel pastLogPanel;

    public CalendarPanel() {
        this.calendar = Calendar.getInstance();
        this.pastLogPanel = new PastLogPanel();
        this.pastLogPanel.setPanelLayout();
        this.pastLogPanel.setup();
        this.add(pastLogPanel);
    }

    @Override
    public void setPanelLayout() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Color.getHSBColor(0f, 0f, 0.03f));
        this.setLayout(null);
        this.setSize(1000, 5000);
        this.setVisible(true);
    }

    @Override
    public void setup() {
        dayEntries(0, Math.min(calendar.getDays().size(), 14));
        addRectangle();
    }

    public void addRectangle() {
        JPanel rect = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.fillRoundRect(15, 20, 945, 600, 20, 20);
            }
        };
        rect.setBounds(15, 20, 975, 640);
        rect.setForeground(Color.getHSBColor(0f, 0f, 0.1f));
        this.add(rect);
    }


    // MODIFIES: this
    // EFFECTS: turns all CalorieLogs into boxes with summary
    public void dayEntries(int start, int end) {
        int rectY = 60;
        int labelY = 65;
        for (int i = start; i < end; i++) {
            createDayLabel(i + 1, labelY);
            createCalorieLabel(i, labelY);
            createWeightLabel(i, labelY);
            createViewLogButton(i, labelY-4);
            createBox(rectY);
            rectY += 37;
            labelY += 37;
            if (i == 6) {
                rectY += 10;
                labelY += 10;
            }
        }
    }


    // REQUIRES: 720 > x > 60, 424 > y > 60
    // MODIFIES: this
    // EFFECTS: adds a box at given x and y to calendarPanel
    public void createBox(int y) {
        JLabel box = new JLabel() {
            @Override
            public void paint(Graphics g) {
                g.fillRoundRect(0, 0, 880, 30, 20, 20);
            }
        };
        box.setBounds(60, y, 880, 30);
        box.setForeground(Color.getHSBColor(0.59f, 0.9f, 0.75f));
        box.setBorder(BorderFactory.createCompoundBorder());
        box.setOpaque(true);
        this.add(box);
    }

    // MODIFIES: this
    // EFFECTS: adds a day label with given index at given x and y to calendarPanel
    public void createDayLabel(int index, int y) {
        JLabel day = new JLabel("Day " + index);
        day.setBounds(70, y, 150, 20);
        this.add(day);
    }

    // MODIFIES: this
    // EFFECTS: adds a total cal label for CalorieLog at given index at given x and y to calendarPanel
    public void createCalorieLabel(int index, int y) {
        JLabel calLabel = new JLabel("Total Calories: " + calendar.getDays().get(index).totalCals());
        calLabel.setBounds(200, y, 150, 20);
        this.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds a weight label for CalorieLog at given index at given x and y to calendarPanel
    public void createWeightLabel(int index, int y) {
        if (calendar.getDays().get(index).getWeight() != 0) {
            JLabel weightLabel = new JLabel("Weight: "
                    + calendar.getDays().get(index).getWeight()
                    + " kg");
            weightLabel.setBounds(500, y, 150, 20);
            this.add(weightLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a view log button for CalorieLog at given index at given x and y to calendarPanel
    public void createViewLogButton(int index, int y) {
        JButton viewLog = new JButton("View Log");
        viewLog.setBounds(730, y, 150, 28);
        viewLog.addActionListener(e -> {
            this.pastLogPanel.setVisible(true);
            this.pastLogPanel.setCalorieLog(calendar.getDays().get(index));
            this.pastLogPanel.viewLog();
        });
        this.add(viewLog);
    }
}
