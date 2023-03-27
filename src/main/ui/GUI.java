package ui;

import model.Calculator;
import model.CalorieLog;
import model.Calendar;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// GUI class: constructs the GUI of the application
public class GUI {

    private static final String JSON_STORE = "./data/calendar.json";
    private static final JsonReader READER = new JsonReader(JSON_STORE);
    private static final JsonWriter WRITER = new JsonWriter(JSON_STORE);


    private final JFrame jframe;

    Calendar calendar;
    Calculator calc;
    CalorieLog log;

    JPanel calcPanel;
    JPanel optionsPanel;
    JPanel logPanel;
    JPanel calendarPanel;
    JPanel viewLogPanel;

    JButton enterObjective;
    JButton enterSpecifics;

    JTextField genderAnswer;
    JTextField ageAnswer;
    JTextField heightAnswer;
    JTextField weightAnswer;
    JTextField loaAnswer;
    JTextField objectiveAnswer;

    JLabel weightGoalQuestion;
    JLabel timeQuestion;
    JTextField weightGoalAnswer;
    JTextField timeAnswer;

    String gender;
    int age;
    double height;
    double weight;
    double weightGoal;
    int levelOfActivity;
    String objective;
    int time;

    JButton startCalculator;
    JButton newDailyLog;
    JButton viewCalendar;
    JButton saveCalendar;
    JButton loadCalendar;

    JButton addFood1;
    JButton addFood2;
    JButton addFood3;
    JButton addFood4;
    JButton addFood5;
    JButton addFood6;
    JButton addFood7;
    JButton addFood8;
    JButton addFood9;

    JLabel indexLabel;
    JLabel indexLabel2;
    JLabel indexLabel3;
    JLabel indexLabel4;
    JLabel indexLabel5;
    JLabel indexLabel6;
    JLabel indexLabel7;
    JLabel indexLabel8;
    JLabel indexLabel9;
    JLabel indexLabel10;

    JTextField foodTextField;
    JTextField foodTextField2;
    JTextField foodTextField3;
    JTextField foodTextField4;
    JTextField foodTextField5;
    JTextField foodTextField6;
    JTextField foodTextField7;
    JTextField foodTextField8;
    JTextField foodTextField9;
    JTextField foodTextField10;

    JTextField weightTextField;
    JTextField dateMonthTextField;
    JTextField dateDayTextField;
    JTextField dateYearTextField;

    JTextField calTextField;
    JTextField calTextField2;
    JTextField calTextField3;
    JTextField calTextField4;
    JTextField calTextField5;
    JTextField calTextField6;
    JTextField calTextField7;
    JTextField calTextField8;
    JTextField calTextField9;
    JTextField calTextField10;


    JLabel result;
    JLabel saved;
    JLabel loaded;

    // MODIFIES: this
    // EFFECTS: constructs a calendar with an empty list of entries
    public GUI() {
        this.calendar = new Calendar();
        this.calc = new Calculator();
        this.jframe = new JFrame();
        this.calcPanel = new JPanel();
        this.optionsPanel = new JPanel();
        this.logPanel = new JPanel();
        this.calendarPanel = new JPanel();
        setupJFrame();
    }

    // MODIFIES: this
    // EFFECTS: sets up the JFrame object
    public void setupJFrame() {
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setTitle("DietKing");
        jframe.setBounds(150, 50, 1000, 700);
        jframe.pack();
        jframe.setSize(1000, 700);
    }

    // MODIFIES: this
    // EFFECTS: sets up the calculator JPanel
    public void openOptionsPanel() {
        optionsPanel.setBorder(BorderFactory.createEmptyBorder());
        optionsPanel.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.7f));
        optionsPanel.setLayout(null);

        calcPanel.setVisible(false);
        logPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(true);

        jframe.add(optionsPanel);
        loadedText();
        saveText();
        addButtons();

        dietKingLogo();

        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a JLabel of a DietKing logo
    public void dietKingLogo() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("data/DietKing_Logo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(185, 100, 690, 240);
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
    }

    // MODIFIES: this
    // EFFECTS: opens calcPanel
    public void calculatorButton() {
        startCalculator = new JButton("Calculate Daily Caloric Requirement");
        startCalculator.setBounds(250, 400, 500, 35);
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
        newDailyLog.setBounds(250, 440, 500, 35);
        newDailyLog.addActionListener(e -> {
            openLogPanel();
            optionsPanel.removeAll();
        });
        optionsPanel.add(newDailyLog);
    }

    // MODIFIES: this
    // EFFECTS: opens calendarPanel
    public void viewCalendarButton() {
        viewCalendar = new JButton("View Calendar");
        viewCalendar.setBounds(250, 480, 500, 35);
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
        loadCalendar.setBounds(250, 520, 500, 35);
        loadCalendar.addActionListener(e -> {
            try {
                this.calendar = READER.read();
                loaded.setText("Your previous calendar has been loaded!");
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
        saveCalendar.setBounds(250, 560, 500, 35);
        saveCalendar.addActionListener(e -> {
            try {
                WRITER.open();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            WRITER.write(calendar);
            WRITER.close();
            saved.setText("Your calendar has been saved!");
            saved.setVisible(true);
        });
        optionsPanel.add(saveCalendar);
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
        calcPanel.setBorder(BorderFactory.createEmptyBorder());
        calcPanel.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.7f));
        calcPanel.setLayout(null);

        logPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calcPanel.setVisible(true);
        jframe.add(calcPanel);

        addResult();
        getInfo();
        enterButton1();
        enterButton2();
        returnButton();
        shapeRectangleForCalculator();

        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets up the daily log tracker
    public void openLogPanel() {
        logPanel.setBorder(BorderFactory.createEmptyBorder());
        logPanel.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.7f));
        logPanel.setLayout(null);

        log = new CalorieLog();

        calcPanel.setVisible(false);
        calendarPanel.setVisible(false);
        optionsPanel.setVisible(false);
        logPanel.setVisible(true);

        jframe.add(logPanel);
        startIteration();
        addSmallDietKingLogo(logPanel);

        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a JLabel of a smaller version of the DietKing logo
    public void addSmallDietKingLogo(JPanel panel) {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("data/DietKing_Logo_Small.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(660, 150, 300, 290);
        panel.add(picLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds 2 text fields to JPanel - 1 for the food and 1 for the calories, in the format of a row
    public void startIteration() {
        JLabel foodLabel = new JLabel("Food:");
        foodLabel.setBounds(100, 70, 300, 30);
        logPanel.add(foodLabel);

        JLabel calLabel = new JLabel("Calories:");
        calLabel.setBounds(400, 70, 150, 30);
        logPanel.add(calLabel);

        addFoodEntries();
        weightEntry();
        dateEntry();
        finishLogButton();
        returnButtonForLog();
        shapeRectangleForLog();
    }

    // MODIFIES: this
    // EFFECTS: adds a rectangle background to list of entries
    public void shapeRectangleForLog() {
        JLabel rectangle = new JLabel();
        rectangle.setBounds(40, 40, 600, 600);
        rectangle.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.9f));
        rectangle.setOpaque(true);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 2));
        rectangle.setForeground(Color.white);
        logPanel.add(rectangle);
    }

    // MODIFIES: this
    // EFFECTS: opens all text fields for food entries
    public void addFoodEntries() {
        foodEntry1();
        foodEntry2();
        foodEntry3();
        foodEntry4();
        foodEntry5();
        foodEntry6();
        foodEntry7();
        foodEntry8();
        foodEntry9();
        foodEntry10();
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 1
    public void foodEntry1() {
        indexLabel = new JLabel(Integer.toString(1));
        indexLabel.setBounds(80, 100, 20, 30);
        logPanel.add(indexLabel);

        foodTextField = new JTextField(20);
        foodTextField.setBounds(100, 100, 300, 30);
        logPanel.add(foodTextField);

        calTextField = new JTextField(20);
        calTextField.setBounds(400, 100, 150, 30);
        logPanel.add(calTextField);

        addFood1 = new JButton("+");
        addFood1.setBounds(555, 100, 30, 30);
        addFood1.addActionListener(e -> {
            addFood1.setVisible(false);
            addFood2.setVisible(true);
            indexLabel2.setVisible(true);
            foodTextField2.setVisible(true);
            calTextField2.setVisible(true);
        });
        logPanel.add(addFood1);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 2
    public void foodEntry2() {
        indexLabel2 = new JLabel(Integer.toString(2));
        indexLabel2.setBounds(80, 130, 20, 30);
        indexLabel2.setVisible(false);
        logPanel.add(indexLabel2);

        foodTextField2 = new JTextField(20);
        foodTextField2.setBounds(100, 130, 300, 30);
        foodTextField2.setVisible(false);
        logPanel.add(foodTextField2);

        calTextField2 = new JTextField(20);
        calTextField2.setBounds(400, 130, 150, 30);
        calTextField2.setVisible(false);
        logPanel.add(calTextField2);

        addFood2 = new JButton("+");
        addFood2.setBounds(555, 130, 30, 30);
        addFood2.setVisible(false);
        addFood2.addActionListener(e -> {
            addFood2.setVisible(false);
            addFood3.setVisible(true);
            indexLabel3.setVisible(true);
            foodTextField3.setVisible(true);
            calTextField3.setVisible(true);
        });
        logPanel.add(addFood2);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 3
    public void foodEntry3() {
        indexLabel3 = new JLabel(Integer.toString(3));
        indexLabel3.setBounds(80, 160, 20, 30);
        indexLabel3.setVisible(false);
        logPanel.add(indexLabel3);

        foodTextField3 = new JTextField(20);
        foodTextField3.setBounds(100, 160, 300, 30);
        foodTextField3.setVisible(false);
        logPanel.add(foodTextField3);

        calTextField3 = new JTextField(20);
        calTextField3.setBounds(400, 160, 150, 30);
        calTextField3.setVisible(false);
        logPanel.add(calTextField3);

        addFood3 = new JButton("+");
        addFood3.setBounds(555, 160, 30, 30);
        addFood3.setVisible(false);
        addFood3.addActionListener(e -> {
            addFood3.setVisible(false);
            addFood4.setVisible(true);
            indexLabel4.setVisible(true);
            foodTextField4.setVisible(true);
            calTextField4.setVisible(true);
        });
        logPanel.add(addFood3);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 4
    public void foodEntry4() {
        indexLabel4 = new JLabel(Integer.toString(4));
        indexLabel4.setBounds(80, 190, 20, 30);
        indexLabel4.setVisible(false);
        logPanel.add(indexLabel4);

        foodTextField4 = new JTextField(20);
        foodTextField4.setBounds(100, 190, 300, 30);
        foodTextField4.setVisible(false);
        logPanel.add(foodTextField4);

        calTextField4 = new JTextField(20);
        calTextField4.setBounds(400, 190, 150, 30);
        calTextField4.setVisible(false);
        logPanel.add(calTextField4);

        addFood4 = new JButton("+");
        addFood4.setBounds(555, 190, 30, 30);
        addFood4.setVisible(false);
        addFood4.addActionListener(e -> {
            addFood4.setVisible(false);
            addFood5.setVisible(true);
            indexLabel5.setVisible(true);
            foodTextField5.setVisible(true);
            calTextField5.setVisible(true);
        });
        logPanel.add(addFood4);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 5
    public void foodEntry5() {
        indexLabel5 = new JLabel(Integer.toString(5));
        indexLabel5.setBounds(80, 220, 20, 30);
        indexLabel5.setVisible(false);
        logPanel.add(indexLabel5);

        foodTextField5 = new JTextField(20);
        foodTextField5.setBounds(100, 220, 300, 30);
        foodTextField5.setVisible(false);
        logPanel.add(foodTextField5);

        calTextField5 = new JTextField(20);
        calTextField5.setBounds(400, 220, 150, 30);
        calTextField5.setVisible(false);
        logPanel.add(calTextField5);

        addFood5 = new JButton("+");
        addFood5.setBounds(555, 220, 30, 30);
        addFood5.setVisible(false);
        addFood5.addActionListener(e -> {
            addFood5.setVisible(false);
            addFood6.setVisible(true);
            indexLabel6.setVisible(true);
            foodTextField6.setVisible(true);
            calTextField6.setVisible(true);
        });
        logPanel.add(addFood5);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 6
    public void foodEntry6() {
        indexLabel6 = new JLabel(Integer.toString(6));
        indexLabel6.setBounds(80, 250, 20, 30);
        indexLabel6.setVisible(false);
        logPanel.add(indexLabel6);

        foodTextField6 = new JTextField(20);
        foodTextField6.setBounds(100, 250, 300, 30);
        foodTextField6.setVisible(false);
        logPanel.add(foodTextField6);

        calTextField6 = new JTextField(20);
        calTextField6.setBounds(400, 250, 150, 30);
        calTextField6.setVisible(false);
        logPanel.add(calTextField6);

        addFood6 = new JButton("+");
        addFood6.setBounds(555, 250, 30, 30);
        addFood6.setVisible(false);
        addFood6.addActionListener(e -> {
            addFood6.setVisible(false);
            addFood7.setVisible(true);
            indexLabel7.setVisible(true);
            foodTextField7.setVisible(true);
            calTextField7.setVisible(true);
        });
        logPanel.add(addFood6);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 7
    public void foodEntry7() {
        indexLabel7 = new JLabel(Integer.toString(7));
        indexLabel7.setBounds(80, 280, 20, 30);
        indexLabel7.setVisible(false);
        logPanel.add(indexLabel7);

        foodTextField7 = new JTextField(20);
        foodTextField7.setBounds(100, 280, 300, 30);
        foodTextField7.setVisible(false);
        logPanel.add(foodTextField7);

        calTextField7 = new JTextField(20);
        calTextField7.setBounds(400, 280, 150, 30);
        calTextField7.setVisible(false);
        logPanel.add(calTextField7);

        addFood7 = new JButton("+");
        addFood7.setBounds(555, 280, 30, 30);
        addFood7.setVisible(false);
        addFood7.addActionListener(e -> {
            addFood7.setVisible(false);
            addFood8.setVisible(true);
            indexLabel8.setVisible(true);
            foodTextField8.setVisible(true);
            calTextField8.setVisible(true);
        });
        logPanel.add(addFood7);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 8
    public void foodEntry8() {
        indexLabel8 = new JLabel(Integer.toString(8));
        indexLabel8.setBounds(80, 310, 20, 30);
        indexLabel8.setVisible(false);
        logPanel.add(indexLabel8);

        foodTextField8 = new JTextField(20);
        foodTextField8.setBounds(100, 310, 300, 30);
        foodTextField8.setVisible(false);
        logPanel.add(foodTextField8);

        calTextField8 = new JTextField(20);
        calTextField8.setBounds(400, 310, 150, 30);
        calTextField8.setVisible(false);
        logPanel.add(calTextField8);

        addFood8 = new JButton("+");
        addFood8.setBounds(555, 310, 30, 30);
        addFood8.setVisible(false);
        addFood8.addActionListener(e -> {
            addFood8.setVisible(false);
            addFood9.setVisible(true);
            indexLabel9.setVisible(true);
            foodTextField9.setVisible(true);
            calTextField9.setVisible(true);
        });
        logPanel.add(addFood8);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 9
    public void foodEntry9() {
        indexLabel9 = new JLabel(Integer.toString(9));
        indexLabel9.setBounds(80, 340, 20, 30);
        indexLabel9.setVisible(false);
        logPanel.add(indexLabel9);

        foodTextField9 = new JTextField(20);
        foodTextField9.setBounds(100, 340, 300, 30);
        foodTextField9.setVisible(false);
        logPanel.add(foodTextField9);

        calTextField9 = new JTextField(20);
        calTextField9.setBounds(400, 340, 150, 30);
        calTextField9.setVisible(false);
        logPanel.add(calTextField9);

        addFood9 = new JButton("+");
        addFood9.setBounds(555, 340, 30, 30);
        addFood9.setVisible(false);
        addFood9.addActionListener(e -> {
            addFood9.setVisible(false);
            indexLabel10.setVisible(true);
            foodTextField10.setVisible(true);
            calTextField10.setVisible(true);
        });
        logPanel.add(addFood9);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 10
    public void foodEntry10() {
        indexLabel10 = new JLabel(Integer.toString(10));
        indexLabel10.setBounds(80, 370, 20, 30);
        indexLabel10.setVisible(false);
        logPanel.add(indexLabel10);

        foodTextField10 = new JTextField(20);
        foodTextField10.setBounds(100, 370, 300, 30);
        foodTextField10.setVisible(false);
        logPanel.add(foodTextField10);

        calTextField10 = new JTextField(20);
        calTextField10.setBounds(400, 370, 150, 30);
        calTextField10.setVisible(false);
        logPanel.add(calTextField10);
    }

    // MODIFIES: this
    // EFFECTS: opens weight entry
    public void weightEntry() {
        JLabel weightLabel = new JLabel("Today's Weight:");
        weightLabel.setBounds(80, 440, 150, 30);
        logPanel.add(weightLabel);

        weightTextField = new JTextField(20);
        weightTextField.setBounds(80, 470, 150, 30);
        logPanel.add(weightTextField);
    }

    // MODIFIES: this
    // EFFECTS: opens date entry
    public void dateEntry() {
        JLabel dateLabel = new JLabel("Today's Date:");
        dateLabel.setBounds(80, 510, 150, 30);
        logPanel.add(dateLabel);

        JLabel month = new JLabel("M:");
        month.setBounds(80, 540, 20, 30);
        logPanel.add(month);

        dateMonthTextField = new JTextField(20);
        dateMonthTextField.setBounds(95, 540, 85, 30);
        logPanel.add(dateMonthTextField);

        JLabel day = new JLabel("D:");
        day.setBounds(185, 540, 20, 30);
        logPanel.add(day);

        dateDayTextField = new JTextField(20);
        dateDayTextField.setBounds(200, 540, 40, 30);
        logPanel.add(dateDayTextField);

        JLabel year = new JLabel("Y:");
        year.setBounds(245, 540, 20, 30);
        logPanel.add(year);

        dateYearTextField = new JTextField(20);
        dateYearTextField.setBounds(260, 540, 70, 30);
        logPanel.add(dateYearTextField);
    }

    // MODIFIES: this
    // EFFECTS: adds a 'finish log' button to logPanel
    public void finishLogButton() {
        JButton finishLog = new JButton("Save and Quit Log");
        finishLog.setBounds(440, 520, 150, 50);
        finishLog.addActionListener(e -> {
            collectFirstFiveFoods();
            collectLastFiveFoods();
            collectFirstFiveCals();
            collectLastFiveCals();
            collectWeightAndDate();
            calendar.addEntry(log);  // IMPORTANT: adding an X to Y
            openOptionsPanel();
            logPanel.removeAll();
        });
        logPanel.add(finishLog);
    }


    // MODIFIES: this
    // EFFECTS: collects user inputs for first five foods
    public void collectFirstFiveFoods() {
        String food = foodTextField.getText();
        if (!food.equals("")) {
            log.addFood(food);
        }
        String food2 = foodTextField2.getText();
        if (!food2.equals("")) {
            log.addFood(food2);
        }
        String food3 = foodTextField3.getText();
        if (!food3.equals("")) {
            log.addFood(food3);
        }
        String food4 = foodTextField4.getText();
        if (!food4.equals("")) {
            log.addFood(food4);
        }
        String food5 = foodTextField5.getText();
        if (!food5.equals("")) {
            log.addFood(food5);
        }
    }


    // MODIFIES: this
    // EFFECTS: collects user inputs for last five foods
    public void collectLastFiveFoods() {
        String food6 = foodTextField6.getText();
        if (!food6.equals("")) {
            log.addFood(food6);
        }
        String food7 = foodTextField7.getText();
        if (!food7.equals("")) {
            log.addFood(food7);
        }
        String food8 = foodTextField8.getText();
        if (!food8.equals("")) {
            log.addFood(food8);
        }
        String food9 = foodTextField9.getText();
        if (!food9.equals("")) {
            log.addFood(food9);
        }
        String food10 = foodTextField10.getText();
        if (!food10.equals("")) {
            log.addFood(food10);
        }
    }

    // MODIFIES: this
    // EFFECTS: collects user inputs for first five foods
    public void collectFirstFiveCals() {
        String calString = calTextField.getText();
        if (!calString.equals("")) {
            int cal = Integer.parseInt(calString);
            log.addNumOfCals(cal);
        }
        String calString2 = calTextField2.getText();
        if (!calString2.equals("")) {
            int cal2 = Integer.parseInt(calString2);
            log.addNumOfCals(cal2);
        }
        String calString3 = calTextField3.getText();
        if (!calString3.equals("")) {
            int cal3 = Integer.parseInt(calString3);
            log.addNumOfCals(cal3);
        }
        String calString4 = calTextField4.getText();
        if (!calString4.equals("")) {
            int cal4 = Integer.parseInt(calString4);
            log.addNumOfCals(cal4);
        }
        collectFifthCal();
    }

    // MODIFIES: this
    // EFFECTS: collects fifth cal
    public void collectFifthCal() {
        String calString5 = calTextField5.getText();
        if (!calString5.equals("")) {
            int cal5 = Integer.parseInt(calString5);
            log.addNumOfCals(cal5);
        }
    }

    // MODIFIES: this
    // EFFECTS: collects user inputs for last five foods
    public void collectLastFiveCals() {
        String calString6 = calTextField6.getText();
        if (!calString6.equals("")) {
            int cal6 = Integer.parseInt(calString6);
            log.addNumOfCals(cal6);
        }
        String calString7 = calTextField7.getText();
        if (!calString7.equals("")) {
            int cal7 = Integer.parseInt(calString7);
            log.addNumOfCals(cal7);
        }
        String calString8 = calTextField8.getText();
        if (!calString8.equals("")) {
            int cal8 = Integer.parseInt(calString8);
            log.addNumOfCals(cal8);
        }
        String calString9 = calTextField9.getText();
        if (!calString9.equals("")) {
            int cal9 = Integer.parseInt(calString9);
            log.addNumOfCals(cal9);
        }
        collectTenthCal();
    }

    // MODIFIES: this
    // EFFECTS: collects 10th food
    public void collectTenthCal() {
        String calString10 = calTextField10.getText();
        if (!calString10.equals("")) {
            int cal10 = Integer.parseInt(calString10);
            log.addNumOfCals(cal10);
        }
    }

    // MODIFIES: this
    // EFFECTS: collects weight and date
    public void collectWeightAndDate() {
        String weightString = weightTextField.getText();
        if (!weightString.equals("")) {
            double weight = Double.parseDouble(weightString);
            log.setWeight(weight);
        }
        String month = dateMonthTextField.getText();
        String day = dateDayTextField.getText();
        String year = dateYearTextField.getText();
        if (!month.equals("") && !day.equals("") && !year.equals("")) {
            log.setDate(Integer.parseInt(day), month, Integer.parseInt(year));
        }
    }

    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButtonForLog() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openOptionsPanel();
            logPanel.removeAll();
        });
        logPanel.add(returnToOptions);
    }


    // MODIFIES: this
    // EFFECTS: adds result to calcPanel
    public void addResult() {
        int total = calendar.getCalculator().totalDailyCaloricRequirement();
        result = new JLabel("Daily Caloric Requirement: ");
        result.setBounds(500, 500, 400, 30);
        if (total > 0) {
            result.setText("Daily Caloric Requirement: "
                    + calendar.getCalculator().totalDailyCaloricRequirement()
                    + " Cals");
        }
        calcPanel.add(result);
    }

    // MODIFIES: this
    // EFFECTS: gets the user's physical information
    public void getInfo() {
        genderPrompt();
        agePrompt();
        heightPrompt();
        weightPrompt();
        loaPrompt();
        objectivePrompt();
        weightGoalPrompt();
        timePrompt();
    }


    // MODIFIES: this
    // EFFECTS: prompts user to enter gender
    public void genderPrompt() {
        JLabel genderQuestion = new JLabel("What is your gender? (Enter either \"male\" or \"female\")");
        genderQuestion.setBounds(50, 50, 400, 30);
        calcPanel.add(genderQuestion);

        genderAnswer = new JTextField(20);
        genderAnswer.setBounds(50, 80, 200, 30);
        calcPanel.add(genderAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter age
    public void agePrompt() {
        JLabel ageQuestion = new JLabel("What is your age?");
        ageQuestion.setBounds(50, 120, 400, 30);
        calcPanel.add(ageQuestion);

        ageAnswer = new JTextField(20);
        ageAnswer.setBounds(50, 150, 200, 30);
        calcPanel.add(ageAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter height
    public void heightPrompt() {
        JLabel heightQuestion = new JLabel("What is your height (in cm)?");
        heightQuestion.setBounds(50, 190, 400, 30);
        calcPanel.add(heightQuestion);

        heightAnswer = new JTextField(20);
        heightAnswer.setBounds(50, 220, 200, 30);
        calcPanel.add(heightAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weight
    public void weightPrompt() {
        JLabel weightQuestion = new JLabel("What is your weight (in kg)?");
        weightQuestion.setBounds(50, 260, 400, 30);
        calcPanel.add(weightQuestion);

        weightAnswer = new JTextField(20);
        weightAnswer.setBounds(50, 290, 200, 30);
        calcPanel.add(weightAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter levelOfActivity
    public void loaPrompt() {
        JLabel loaQuestion1 = new JLabel("What is your weekly level of activity?");
        JLabel loaQuestion2 = new JLabel("(Enter the corresponding number from 1-5)");
        JLabel loaQuestion3 = new JLabel("1 - None (Exercise 0 times a week)");
        JLabel loaQuestion4 = new JLabel("2 - Light (Exercise 1-2 times a week)");
        JLabel loaQuestion5 = new JLabel("3 - Moderate (Exercise 3-5 times a week)");
        JLabel loaQuestion6 = new JLabel("4 - Hard (Exercise 6-7 times a week)");
        JLabel loaQuestion7 = new JLabel("5 - Extreme (Exercise over 7 times a week)");
        loaQuestion1.setBounds(50, 330, 400, 20);
        loaQuestion2.setBounds(50, 350, 400, 20);
        loaQuestion3.setBounds(70, 370, 400, 20);
        loaQuestion4.setBounds(70, 390, 400, 20);
        loaQuestion5.setBounds(70, 410, 400, 20);
        loaQuestion6.setBounds(70, 430, 400, 20);
        loaQuestion7.setBounds(70, 450, 400, 20);
        calcPanel.add(loaQuestion1);
        calcPanel.add(loaQuestion2);
        calcPanel.add(loaQuestion3);
        calcPanel.add(loaQuestion4);
        calcPanel.add(loaQuestion5);
        calcPanel.add(loaQuestion6);
        calcPanel.add(loaQuestion7);
        addLoaAnswer();
    }

    // MODIFIES: this
    // EFFECTS: adds loaAnswer to calcPanel
    public void addLoaAnswer() {
        loaAnswer = new JTextField(20);
        loaAnswer.setBounds(50, 480, 200, 30);
        calcPanel.add(loaAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter objective
    public void objectivePrompt() {
        JLabel objectiveQuestion1 = new JLabel("Would you like to gain, lose or maintain your current weight?");
        JLabel objectiveQuestion2 = new JLabel("(Enter either \"gain\", \"lose\" or \"maintain\")");
        objectiveQuestion1.setBounds(500, 50, 400, 20);
        objectiveQuestion2.setBounds(500, 70, 400, 20);
        calcPanel.add(objectiveQuestion1);
        calcPanel.add(objectiveQuestion2);

        objectiveAnswer = new JTextField(20);
        objectiveAnswer.setBounds(500, 100, 200, 30);
        calcPanel.add(objectiveAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weightGoal
    public void weightGoalPrompt() {
        weightGoalQuestion = new JLabel("How much weight would you like to gain/lose? (In kg)");
        weightGoalQuestion.setBounds(500, 160, 400, 30);
        weightGoalQuestion.setVisible(false);
        calcPanel.add(weightGoalQuestion);

        weightGoalAnswer = new JTextField(20);
        weightGoalAnswer.setBounds(500, 190, 200, 30);
        weightGoalAnswer.setVisible(false);
        calcPanel.add(weightGoalAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter time
    public void timePrompt() {
        timeQuestion = new JLabel("In how many weeks would you like to achieve this goal?");
        timeQuestion.setBounds(500, 230, 400, 30);
        timeQuestion.setVisible(false);
        calcPanel.add(timeQuestion);

        timeAnswer = new JTextField(20);
        timeAnswer.setBounds(500, 260, 200, 30);
        timeAnswer.setVisible(false);
        calcPanel.add(timeAnswer);
    }

    // MODIFIES: this
    // EFFECTS: sets up the first "enter" button
    public void enterButton1() {
        enterObjective = new JButton("Enter");
        enterObjective.setBounds(800, 80, 90, 30);
        calcPanel.add(enterObjective);
        enterObjective.addActionListener(e -> {
            collectGoalAnswers();
            if (!objective.equals("maintain")) {
                weightGoalQuestion.setVisible(true);
                weightGoalAnswer.setVisible(true);
                timeQuestion.setVisible(true);
                timeAnswer.setVisible(true);
                enterSpecifics.setVisible(true);
            } else {
                result.setText("Daily Caloric Requirement: " + calc.totalDailyCaloricRequirement() + " Cals");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for goal
    public void collectGoalAnswers() {
        gender = genderAnswer.getText();
        calc.setGender(gender);
        age = Integer.parseInt(ageAnswer.getText());
        calc.setAge(age);
        height = Integer.parseInt(heightAnswer.getText());
        calc.setHeight(height);
        weight = Integer.parseInt(weightAnswer.getText());
        calc.setWeight(weight);
        levelOfActivity = Integer.parseInt(loaAnswer.getText());
        calc.setLevelOfActivity(levelOfActivity);
        objective = objectiveAnswer.getText();
        calc.setObjective(objective);
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for specifics
    public void collectSpecificsAnswers() {
        weightGoal = Integer.parseInt(weightGoalAnswer.getText());
        calc.setWeightGoal(weightGoal);
        time = Integer.parseInt(timeAnswer.getText());
        calc.setTime(time);
    }

    // MODIFIES: this
    // EFFECTS: sets up the second "enter" button
    public void enterButton2() {
        enterSpecifics = new JButton("Save");
        enterSpecifics.setBounds(800, 300, 90, 30);
        enterSpecifics.setVisible(false);
        calcPanel.add(enterSpecifics);
        enterSpecifics.addActionListener(e -> {
            calendar.setCalc(calc);
            collectGoalAnswers();
            collectSpecificsAnswers();
            result.setText("Daily Caloric Requirement: "
                    + calendar.getCalculator().totalDailyCaloricRequirement()
                    + " Cals");
        });


    }

    // MODIFIES: this
    // EFFECTS: adds a rectangle background to list of entries
    public void shapeRectangleForCalculator() {
        JLabel rectangle = new JLabel();
        rectangle.setBounds(40, 40, 920, 586);
        rectangle.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.9f));
        rectangle.setOpaque(true);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 2));
        rectangle.setForeground(Color.white);
        calcPanel.add(rectangle);
    }

    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButton() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openOptionsPanel();
            calcPanel.removeAll();
        });
        calcPanel.add(returnToOptions);
    }




    // MODIFIES: this
    // EFFECTS: sets up the calendar panel
    public void openCalendarPanel() {
        calendarPanel.setBorder(BorderFactory.createEmptyBorder());
        calendarPanel.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.7f));
        calendarPanel.setLayout(null);

        calcPanel.setVisible(false);
        logPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calendarPanel.setVisible(true);


        jframe.add(calendarPanel);
        returnButtonForCalendar();
        dayEntries();
        shapeRectangleForCalendar();
        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: adds a rectangle background to list of entries
    public void shapeRectangleForCalendar() {
        JLabel rectangle = new JLabel();
        rectangle.setBounds(40, 40, 920, 586);
        rectangle.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.9f));
        rectangle.setOpaque(true);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 2));
        rectangle.setForeground(Color.white);
        calendarPanel.add(rectangle);
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
            createRemoveLogButton(i, removeX, removeY);
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
        box.setBackground(Color.getHSBColor(0.12f, 0.75f, 0.9f));
        box.setOpaque(true);
        box.setForeground(Color.white);
        calendarPanel.add(box);
    }

    // MODIFIES: this
    // EFFECTS: adds a day label with given index at given x and y to calendarPanel
    public void createDayLabel(int index, int x, int y) {
        JLabel day = new JLabel("Day " + index);
        day.setBounds(x, y, 150, 20);
        calendarPanel.add(day);
    }

    // MODIFIES: this
    // EFFECTS: adds a total cal label for CalorieLog at given index at given x and y to calendarPanel
    public void createCalorieLabel(int index, int x, int y) {
        JLabel calLabel = new JLabel("Total Calories: " + calendar.getDays().get(index).totalCals());
        calLabel.setBounds(x, y, 150, 20);
        calendarPanel.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds a weight label for CalorieLog at given index at given x and y to calendarPanel
    public void createWeightLabel(int index, int x, int y) {
        if (calendar.getDays().get(index).getWeight() != 0) {
            JLabel weightLabel = new JLabel("Weight: "
                    + Double.toString(calendar.getDays().get(index).getWeight())
                    + " kg");
            weightLabel.setBounds(x, y, 150, 20);
            calendarPanel.add(weightLabel);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds a view log button for CalorieLog at given index at given x and y to calendarPanel
    public void createViewLogButton(int index, int x, int y) {
        JButton viewLog = new JButton("View Log");
        viewLog.setBounds(x, y, 156, 20);
        viewLog.addActionListener(e -> {
            openViewLogPanel(calendar.getDays().get(index));
            calendarPanel.removeAll();
        });
        calendarPanel.add(viewLog);
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





    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButtonForCalendar() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openOptionsPanel();
            calendarPanel.removeAll();
        });
        calendarPanel.add(returnToOptions);
    }

    // MODIFIES: this
    // EFFECTS: creates a new JPanel to view past CalorieLog
    public void openViewLogPanel(CalorieLog logToView) {
        viewLogPanel = new JPanel();
        viewLogPanel.setBorder(BorderFactory.createEmptyBorder());
        viewLogPanel.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.7f));
        viewLogPanel.setLayout(null);

        calcPanel.setVisible(false);
        logPanel.setVisible(false);
        optionsPanel.setVisible(false);
        calendarPanel.setVisible(false);

        jframe.add(viewLogPanel);

        viewEntries(logToView);
        createWeightLabelForView(logToView);
        createDateLabelForView(logToView);
        shapeRectangleForViewLogPanel();
        returnButtonForViewLogPanel();
        addSmallDietKingLogo(viewLogPanel);

        jframe.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: prints out CalorieLog on viewLogPanel
    public void viewEntries(CalorieLog logToView) {
        header();
        List<String> foods = logToView.getFoods();
        int foodY = 100;
        int calY = 100;
        int index = 0;

        for (String food : foods) {
            createFoodLabel(food, index, foodY);
            index++;
            foodY += 30;
        }
        List<Integer> cals = logToView.getCals();
        for (int cal : cals) {
            createCalLabel(cal, calY);
            calY += 30;
        }
    }

    // MODIFIES: this
    // EFFECTS: prints out Food and Calories header
    public void header() {
        JLabel foodLabel = new JLabel("Food:");
        foodLabel.setBounds(100, 70, 300, 30);
        viewLogPanel.add(foodLabel);

        JLabel calLabel = new JLabel("Calories:");
        calLabel.setBounds(400, 70, 150, 30);
        viewLogPanel.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: prints out all food entries + index
    public void createFoodLabel(String food, int index, int y) {
        JLabel indexLabel = new JLabel(Integer.toString(index + 1));
        indexLabel.setBounds(80, y, 20, 30);
        viewLogPanel.add(indexLabel);

        JLabel foodLabel = new JLabel(" " + food);
        foodLabel.setBounds(100, y, 290, 25);
        foodLabel.setBackground(Color.lightGray);
        foodLabel.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
        foodLabel.setOpaque(true);
        viewLogPanel.add(foodLabel);
    }

    // MODIFIES: this
    // EFFECTS: prints out calorie entries
    public void createCalLabel(int cal, int y) {
        JLabel calLabel = new JLabel(" " + Integer.toString(cal));
        calLabel.setBounds(400, y, 150, 25);
        calLabel.setBackground(Color.lightGray);
        calLabel.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
        calLabel.setOpaque(true);
        viewLogPanel.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: if not null, prints out weight
    public void createWeightLabelForView(CalorieLog logToView) {
        JLabel weightLabel;
        if (logToView.getWeight() != 0) {
            weightLabel = new JLabel("Today's Weight:");
            JLabel weight = new JLabel(" " + logToView.getWeight());
            weight.setBounds(80, 470, 150, 30);
            weight.setBackground(Color.lightGray);
            weight.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
            weight.setOpaque(true);
            viewLogPanel.add(weight);
        } else {
            weightLabel = new JLabel("No weight specified.");
        }
        weightLabel.setBounds(80, 440, 150, 30);
        viewLogPanel.add(weightLabel);
    }

    // MODIFIES: this
    // EFFECTS: if not null, prints out date
    public void createDateLabelForView(CalorieLog logToView) {
        JLabel dateLabel;
        if (logToView.getDate().getDay() != 0) {
            dateLabel = new JLabel("Today's Date:");

            JLabel date = new JLabel(" "
                    + logToView.getDate().getMonth() + " "
                    + logToView.getDate().getDay() + ", "
                    + logToView.getDate().getYear()
            );
            date.setBounds(80, 540, 150, 30);
            date.setBackground(Color.lightGray);
            date.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
            date.setOpaque(true);
            viewLogPanel.add(date);
        } else {
            dateLabel = new JLabel("No date specified.");
        }
        dateLabel.setBounds(80, 510, 150, 30);
        viewLogPanel.add(dateLabel);
    }


    // MODIFIES: this
    // EFFECTS: opens a rectangle in the background
    public void shapeRectangleForViewLogPanel() {
        JLabel rectangle = new JLabel();
        rectangle.setBounds(40, 40, 600, 600);
        rectangle.setBackground(Color.getHSBColor(0.55f, 0.7f, 0.9f));
        rectangle.setOpaque(true);
        rectangle.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 2));
        rectangle.setForeground(Color.white);
        viewLogPanel.add(rectangle);
    }

    // MODIFIES: this
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButtonForViewLogPanel() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            openCalendarPanel();
            jframe.remove(viewLogPanel);
        });
        viewLogPanel.add(returnToOptions);
    }


}
