package ui;

import model.Calculator;
import model.Calendar;
import model.EventLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.CalculatorPanel;
import ui.panels.CalendarPanel;
import ui.panels.CalorieLogPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

// GUI class: constructs the GUI of the application
public class MainWindow extends JFrame {

    private static final String JSON_STORE = "./data/calendar.json";
    private static final JsonReader READER = new JsonReader(JSON_STORE);
    private static final JsonWriter WRITER = new JsonWriter(JSON_STORE);

    Calendar calendar;
    Calculator calculator;
    LogPrinter logPrinter;

    CalculatorPanel calculatorPanel;
    CalorieLogPanel calorieLogPanel;
    CalendarPanel calendarPanel;

    JPanel optionsPanel;
    JButton startCalculator;
    JButton newDailyLog;
    JButton viewCalendar;
    JButton saveCalendar;
    JButton loadCalendar;

    JLabel saved;
    JLabel loaded;

    // MODIFIES: this
    // EFFECTS: constructs a calendar with an empty list of entries
    public MainWindow() {
        this.calendar = Calendar.getInstance();
        this.calculator = Calculator.getInstance();
        this.calculatorPanel = new CalculatorPanel();
        this.calendarPanel = new CalendarPanel();
        this.calorieLogPanel = new CalorieLogPanel();
        this.logPrinter = new LogPrinter();
        this.optionsPanel = new JPanel();
        setupJFrame();
    }





    // MODIFIES: this
    // EFFECTS: sets up the JFrame object
    public void setupJFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("CALOTRACK");
        setBounds(150, 50, 1000, 700);
        pack();
        setSize(1000, 700);
    }


    public void addLeftRectangle() {
        JPanel leftRect = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.fillRoundRect(10, 50, 640, 480, 20, 20);
            }
        };
        leftRect.setBounds(10, 50, 680, 550);
        leftRect.setForeground(Color.getHSBColor(0f, 0f, 0.1f));
        optionsPanel.add(leftRect);
    }


    // MODIFIES: this
    // EFFECTS: sets up the calculator JPanel
    public void openOptionsPanel() {
        optionsPanel.setBorder(BorderFactory.createEmptyBorder());
        optionsPanel.setBackground(Color.getHSBColor(0f, 0f, 0.03f));
        optionsPanel.setLayout(null);
        calculatorPanel.setVisible(false);
        calorieLogPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(true);
        add(optionsPanel);
        loadedText();
        saveText();
        addButtons();
        addCalotrackLogo();
        addLeftRectangle();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a JLabel of a DietKing logo
    public void addCalotrackLogo() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("data/calotrack.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(40, 230, 800, 400);
        optionsPanel.add(picLabel);
    }


    // MODIFIES: this
    // EFFECTS: adds buttons to optionsPanel
    public void addButtons() {
        calculatorButton();
        newDailyLogButton();
        viewCalendarButton();
        saveCalendarButton();
        loadCalendarButton();
        quitButton();
    }

    // MODIFIES: this
    // EFFECTS: opens calcPanel
    public void calculatorButton() {
        startCalculator = new JButton("Calculate Daily Requirement");
        startCalculator.setBounds(700, 190, 250, 60);
        startCalculator.addActionListener(e -> {
            openCalculatorPanel();
            optionsPanel.removeAll();
        });
        optionsPanel.add(startCalculator);
    }

    // MODIFIES: this
    // EFFECTS: opens logPanel
    public void newDailyLogButton() {
        newDailyLog = new JButton("New Daily Log");
        newDailyLog.setBounds(700, 247, 250, 60);
        newDailyLog.addActionListener(e -> {
            openCalorieLogPanel();
            optionsPanel.removeAll();
        });
        optionsPanel.add(newDailyLog);
    }

    // MODIFIES: this
    // EFFECTS: opens calendarPanel
    public void viewCalendarButton() {
        viewCalendar = new JButton("View Calendar");
        viewCalendar.setBounds(700, 304, 250, 60);
        viewCalendar.addActionListener(e -> {
            openCalendarPanel();
            optionsPanel.removeAll();
        });
        optionsPanel.add(viewCalendar);
    }

    // MODIFIES: this
    // EFFECTS: loads saved calendar
    public void loadCalendarButton() {
        loadCalendar = new JButton("Load Calendar");
        loadCalendar.setBounds(700, 361, 250, 60);
        loadCalendar.addActionListener(e -> {
            try {
                this.calendar = READER.read();
                loaded.setText("Your previous calendar has been loaded!");
                loaded.setForeground(Color.white);
                loaded.setVisible(true);
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        });
        optionsPanel.add(loadCalendar);

    }

    // MODIFIES: this
    // EFFECTS: saves this
    public void saveCalendarButton() {
        saveCalendar = new JButton("Save Calendar");
        saveCalendar.setBounds(700, 418, 250, 60);
        saveCalendar.addActionListener(e -> {
            try {
                WRITER.open();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            WRITER.write(calendar);
            WRITER.close();
            saved.setText("Your calendar has been saved!");
            saved.setForeground(Color.white);
            saved.setVisible(true);
        });
        optionsPanel.add(saveCalendar);
    }

    // MODIFIES: this
    // EFFECTS: quits the program
    public void quitButton() {
        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(920, 20, 70, 30);
        quitButton.addActionListener(e -> {
            logPrinter.printLog(EventLog.getInstance());
            dispose();
        });
        optionsPanel.add(quitButton);

    }


    // MODIFIES: this
    // EFFECTS: JLabel to confirm saved calendar
    public void saveText() {
        saved = new JLabel();
        saved.setBounds(407, 600, 200, 40);
        saved.setVisible(false);
        optionsPanel.add(saved);
    }

    // MODIFIES: this
    // EFFECTS: JLabel to confirm loaded calendar
    public void loadedText() {
        loaded = new JLabel();
        loaded.setBounds(372, 600, 280, 40);
        loaded.setVisible(false);
        optionsPanel.add(loaded);
    }


    // MODIFIES: this
    // EFFECTS: sets up the calculator JPanel
    public void openCalculatorPanel() {
        this.add(calculatorPanel);
        optionsPanel.setVisible(false);
        calendarPanel.setVisible(false);
        calorieLogPanel.setVisible(false);
        calculatorPanel.setPanelLayout();
        this.returnFromCalculatorButton();
        calculatorPanel.setup();
    }

    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnFromCalculatorButton() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            calculatorPanel.removeAll();
            this.openOptionsPanel();
        });
        calculatorPanel.add(returnToOptions);
    }


    // MODIFIES: this
    // EFFECTS: sets up the daily log tracker
    public void openCalorieLogPanel() {
        this.add(calorieLogPanel);
        calculatorPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calorieLogPanel.setPanelLayout();
        this.returnFromLogButton();
        this.finishLogButton();
        calorieLogPanel.setup();
    }

    // MODIFIES: this
    // EFFECTS: adds a 'finish log' button to logPanel
    public void finishLogButton() {
        JButton finishLog = new JButton("Save and Quit Log");
        finishLog.setBounds(440, 520, 150, 50);
        finishLog.addActionListener(e -> {
            calorieLogPanel.collectFirstFiveFoods();
            calorieLogPanel.collectLastFiveFoods();
            calorieLogPanel.collectFirstFiveCals();
            calorieLogPanel.collectLastFiveCals();
            calorieLogPanel.collectWeightAndDate();
            calorieLogPanel.addLog();
            calorieLogPanel.removeAll();
            this.openOptionsPanel();
        });
        calorieLogPanel.add(finishLog);
    }


    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnFromLogButton() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openOptionsPanel();
            calorieLogPanel.removeAll();
        });
        calorieLogPanel.add(returnToOptions);
    }

    // MODIFIES: this
    // EFFECTS: sets up the calendar panel
    public void openCalendarPanel() {
        this.calendarPanel = new CalendarPanel();
        this.add(calendarPanel);
        calculatorPanel.setVisible(false);
        calorieLogPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calendarPanel.setPanelLayout();
        this.returnButtonForCalendar();
        this.addRemoveLogButtons();
        calendarPanel.setup();

    }


    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButtonForCalendar() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openOptionsPanel();
            this.remove(calendarPanel);
        });
        calendarPanel.add(returnToOptions);
    }

    // MODIFIES: this
    // EFFECTS: turns all CalorieLogs into boxes with summary
    public void addRemoveLogButtons() {
        int num = 0;
        int removeX = 203;
        int removeY = 59;
        for (int i = 0; i < calendar.getDays().size(); i++) {
            this.createRemoveLogButton(i, removeX, removeY);
            removeX += 180;
            if ((num + 1) % 5 == 0) {
                removeX = 203;
                removeY += 182;
            }
            num++;
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a remove log button for CalorieLog at given index at given x and y to calendarPanel
    public void createRemoveLogButton(int index, int x, int y) {
        JButton removeLog = new JButton("X");
        removeLog.setBounds(x, y, 20, 20);
        removeLog.addActionListener(e -> {
            calendar.removeEntry(index);
            calendarPanel.setVisible(false);
            calendarPanel.removeAll();
            openCalendarPanel();
        });
        calendarPanel.add(removeLog);
    }

}
