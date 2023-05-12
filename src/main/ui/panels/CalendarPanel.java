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
        dayEntries();
        addRectangle();
        //shapeRectangleForCalendar();
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
    public void dayEntries() {
        loopThroughDays(0, 60, 60, 63, 63,  62, 200, 203, 59);
    }

    // MODIFIES: this
    // EFFECTS: turns all CalorieLogs into boxes with summary
    public void loopThroughDays(int num, int rectX, int rectY, int labelX, int labelY,
                                int buttonX, int buttonY, int removeX, int removeY) {
        for (int i = 0; i < calendar.getDays().size(); i++) {
            createDayLabel(i + 1, labelX, labelY);
            createCalorieLabel(i, labelX, labelY + 30);
            createWeightLabel(i, labelX, labelY + 60);
            createViewLogButton(i, buttonX, buttonY);
            createBox(rectX, rectY);
            rectX += 180;
            labelX += 180;
            buttonX += 180;
            removeX += 180;
            if ((num + 1) % 5 == 0) {
                rectX = 60;
                rectY += 182;
                labelX = 63;
                labelY += 182;
                buttonX = 62;
                buttonY += 182;
                removeX = 203;
                removeY += 182;
            }
            num++;
        }
    }


    // REQUIRES: 720 > x > 60, 424 > y > 60
    // MODIFIES: this
    // EFFECTS: adds a box at given x and y to calendarPanel
    public void createBox(int x, int y) {
        JLabel box = new JLabel();
        box.setBounds(x, y, 160, 162);
        box.setBackground(Color.getHSBColor(0.57f, 0.8f, 0.6f));
        box.setBorder(BorderFactory.createCompoundBorder());
        box.setOpaque(true);
        box.setForeground(Color.white);
        this.add(box);
    }

    // MODIFIES: this
    // EFFECTS: adds a day label with given index at given x and y to calendarPanel
    public void createDayLabel(int index, int x, int y) {
        JLabel day = new JLabel("Day " + index);
        day.setBounds(x, y, 150, 20);
        this.add(day);
    }

    // MODIFIES: this
    // EFFECTS: adds a total cal label for CalorieLog at given index at given x and y to calendarPanel
    public void createCalorieLabel(int index, int x, int y) {
        JLabel calLabel = new JLabel("Total Calories: " + calendar.getDays().get(index).totalCals());
        calLabel.setBounds(x, y, 150, 20);
        this.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds a weight label for CalorieLog at given index at given x and y to calendarPanel
    public void createWeightLabel(int index, int x, int y) {
        if (calendar.getDays().get(index).getWeight() != 0) {
            JLabel weightLabel = new JLabel("Weight: "
                    + calendar.getDays().get(index).getWeight()
                    + " kg");
            weightLabel.setBounds(x, y, 150, 20);
            this.add(weightLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a view log button for CalorieLog at given index at given x and y to calendarPanel
    public void createViewLogButton(int index, int x, int y) {
        JButton viewLog = new JButton("View Log");
        viewLog.setBounds(x, y, 156, 20);
        viewLog.addActionListener(e -> {
            this.pastLogPanel.setVisible(true);
            this.pastLogPanel.setCalorieLog(calendar.getDays().get(index));
            this.pastLogPanel.viewLog();
        });
        this.add(viewLog);
    }

    public PastLogPanel getPastLogPanel() {
        return pastLogPanel;
    }
}
