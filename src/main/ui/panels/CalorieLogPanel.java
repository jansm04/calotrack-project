package ui.panels;

import model.Calculator;
import model.Calendar;
import model.CalorieLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CalorieLogPanel extends JPanel implements PanelSetup {

    CalorieLog log;
    Calculator calculator;
    Calendar calendar;

    List<JButton> addFoodButtons;
    List<JLabel> indexLabels;
    List<JTextField> foodTextFields;
    List<JTextField> calTextFields;

    JTextField weightTextField;
    JTextField dateMonthTextField;
    JTextField dateDayTextField;
    JTextField dateYearTextField;

    public CalorieLogPanel() {
        this.calculator = Calculator.getInstance();
        this.calendar = Calendar.getInstance();
    }

    @Override
    public void setPanelLayout() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Color.getHSBColor(0f, 0f, 0.03f));
        this.setLayout(null);
        this.setVisible(true);
    }

    @Override
    public void setup() {
        log = new CalorieLog();
        addFoodButtons = new ArrayList<>(10);
        indexLabels = new ArrayList<>(10);
        foodTextFields = new ArrayList<>(10);
        calTextFields = new ArrayList<>(10);
        addTotalCaloricRequirement();
        startIteration();
        addSmallCalotrackLogo();
    }

    // MODIFIES: this
    // EFFECTS: adds a JLabel of a smaller version of the DietKing logo
    public void addSmallCalotrackLogo() {
        BufferedImage myPicture;
        try {
            myPicture = ImageIO.read(new File("data/calotrack_small.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(660, 200, 300, 200);
        this.add(picLabel);
    }

    // MODIFIES: this
    // EFFECTS: adds 2 text fields to JPanel - 1 for the food and 1 for the calories, in the format of a row
    public void startIteration() {
        JLabel foodLabel = new JLabel("Food:");
        foodLabel.setBounds(100, 70, 300, 30);
        this.add(foodLabel);
        JLabel calLabel = new JLabel("Calories:");
        calLabel.setBounds(400, 70, 150, 30);
        this.add(calLabel);
        this.addFoodEntries();
        this.weightEntry();
        this.dateEntry();
        this.shapeRectangleForLogPanel();
    }

    // MODIFIES: this
    // EFFECTS: opens a rectangle in the background
    public void shapeRectangleForLogPanel() {
        JPanel rect = new JPanel() {
            @Override
            public void paint(Graphics g) {
                g.fillRoundRect(20, 20, 600, 570, 20, 20);
            }
        };
        rect.setBounds(20, 20, 640, 610);
        rect.setForeground(Color.getHSBColor(0f, 0f, 0.2f));
        this.add(rect);
    }



    // MODIFIES: this
    // EFFECTS: adds all buttons, labels and text fields for food entries
    public void addFoodEntries() {
        for (int i = 0; i < 10; i++) {
            createFoodEntry(i);
        }
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 1
    public void createFoodEntry(int i) {
        int h = 100 + (30 * i);
        indexLabels.add(new JLabel(Integer.toString(i+1)));
        indexLabels.get(i).setBounds(80, h, 20, 30);
        this.add(indexLabels.get(i));

        foodTextFields.add(new JTextField(20));
        foodTextFields.get(i).setBounds(100, h, 300, 30);
        this.add(foodTextFields.get(i));

        calTextFields.add(new JTextField(20));
        calTextFields.get(i).setBounds(400, h, 150, 30);
        this.add(calTextFields.get(i));

        addFoodButtons.add(new JButton("+"));
        addFoodButtons.get(i).setBounds(555, h, 30, 30);
        if (i < 9) {
            addFoodButtons.get(i).addActionListener(e -> {
                addFoodButtons.get(i).setVisible(false);
                addFoodButtons.get(i+1).setVisible(true);
                indexLabels.get(i+1).setVisible(true);
                foodTextFields.get(i+1).setVisible(true);
                calTextFields.get(i+1).setVisible(true);
            });
            this.add(addFoodButtons.get(i));
        }

        if (i != 0) {
            indexLabels.get(i).setVisible(false);
            foodTextFields.get(i).setVisible(false);
            calTextFields.get(i).setVisible(false);
            addFoodButtons.get(i).setVisible(false);
        }
    }



    // MODIFIES: this
    // EFFECTS: opens weight entry
    public void weightEntry() {
        JLabel weightLabel = new JLabel("Today's Weight:");
        weightLabel.setBounds(80, 440, 150, 30);
        this.add(weightLabel);

        weightTextField = new JTextField(20);
        weightTextField.setBounds(80, 470, 150, 30);
        this.add(weightTextField);
    }

    // MODIFIES: this
    // EFFECTS: opens date entry
    public void dateEntry() {
        JLabel dateLabel = new JLabel("Today's Date:");
        dateLabel.setBounds(80, 510, 150, 30);
        this.add(dateLabel);

        JLabel month = new JLabel("M:");
        month.setBounds(80, 540, 20, 30);
        this.add(month);

        dateMonthTextField = new JTextField(20);
        dateMonthTextField.setBounds(95, 540, 85, 30);
        this.add(dateMonthTextField);

        JLabel day = new JLabel("D:");
        day.setBounds(185, 540, 20, 30);
        this.add(day);

        dateDayTextField = new JTextField(20);
        dateDayTextField.setBounds(200, 540, 40, 30);
        this.add(dateDayTextField);

        JLabel year = new JLabel("Y:");
        year.setBounds(245, 540, 20, 30);
        this.add(year);

        dateYearTextField = new JTextField(20);
        dateYearTextField.setBounds(260, 540, 70, 30);
        this.add(dateYearTextField);
    }



    // MODIFIES: this
    // EFFECTS: adds a total caloric requirement to logPanel
    public void addTotalCaloricRequirement() {
        JLabel caloricReq = new JLabel("Goal: "
                + calculator.totalDailyCaloricRequirement()
                + " Cals");
        caloricReq.setBounds(80, 580, 300, 25);
        caloricReq.setBackground(Color.white);
        if (calculator.totalDailyCaloricRequirement() > 0) {
            this.add(caloricReq);
        }
    }

    // MODIFIES: this
    // EFFECTS: collects user inputs for foods
    public void collectFoods() {
        for (JTextField foodTextField : foodTextFields) {
            String food = foodTextField.getText();
            if (!food.equals("")) {
                log.addFood(food);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: collects user inputs for calories
    public void collectCals() {
        for (JTextField calTextField : calTextFields) {
            String calString = calTextField.getText();
            if (!calString.equals("")) {
                int cal = Integer.parseInt(calString);
                log.addNumOfCals(cal);
            }
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

    public void addLog() {
        calendar.addEntry(log);  // IMPORTANT: adding an X to Y
    }


}

