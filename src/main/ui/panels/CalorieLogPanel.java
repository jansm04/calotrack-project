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

public class CalorieLogPanel extends JPanel implements PanelSetup {

    CalorieLog log;
    Calculator calculator;
    Calendar calendar;

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
        this.add(indexLabel);

        foodTextField = new JTextField(20);
        foodTextField.setBounds(100, 100, 300, 30);
        this.add(foodTextField);

        calTextField = new JTextField(20);
        calTextField.setBounds(400, 100, 150, 30);
        this.add(calTextField);

        addFood1 = new JButton("+");
        addFood1.setBounds(555, 100, 30, 30);
        addFood1.addActionListener(e -> {
            addFood1.setVisible(false);
            addFood2.setVisible(true);
            indexLabel2.setVisible(true);
            foodTextField2.setVisible(true);
            calTextField2.setVisible(true);
        });
        this.add(addFood1);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 2
    public void foodEntry2() {
        indexLabel2 = new JLabel(Integer.toString(2));
        indexLabel2.setBounds(80, 130, 20, 30);
        indexLabel2.setVisible(false);
        this.add(indexLabel2);

        foodTextField2 = new JTextField(20);
        foodTextField2.setBounds(100, 130, 300, 30);
        foodTextField2.setVisible(false);
        this.add(foodTextField2);

        calTextField2 = new JTextField(20);
        calTextField2.setBounds(400, 130, 150, 30);
        calTextField2.setVisible(false);
        this.add(calTextField2);

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
        this.add(addFood2);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 3
    public void foodEntry3() {
        indexLabel3 = new JLabel(Integer.toString(3));
        indexLabel3.setBounds(80, 160, 20, 30);
        indexLabel3.setVisible(false);
        this.add(indexLabel3);

        foodTextField3 = new JTextField(20);
        foodTextField3.setBounds(100, 160, 300, 30);
        foodTextField3.setVisible(false);
        this.add(foodTextField3);

        calTextField3 = new JTextField(20);
        calTextField3.setBounds(400, 160, 150, 30);
        calTextField3.setVisible(false);
        this.add(calTextField3);

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
        this.add(addFood3);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 4
    public void foodEntry4() {
        indexLabel4 = new JLabel(Integer.toString(4));
        indexLabel4.setBounds(80, 190, 20, 30);
        indexLabel4.setVisible(false);
        this.add(indexLabel4);

        foodTextField4 = new JTextField(20);
        foodTextField4.setBounds(100, 190, 300, 30);
        foodTextField4.setVisible(false);
        this.add(foodTextField4);

        calTextField4 = new JTextField(20);
        calTextField4.setBounds(400, 190, 150, 30);
        calTextField4.setVisible(false);
        this.add(calTextField4);

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
        this.add(addFood4);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 5
    public void foodEntry5() {
        indexLabel5 = new JLabel(Integer.toString(5));
        indexLabel5.setBounds(80, 220, 20, 30);
        indexLabel5.setVisible(false);
        this.add(indexLabel5);

        foodTextField5 = new JTextField(20);
        foodTextField5.setBounds(100, 220, 300, 30);
        foodTextField5.setVisible(false);
        this.add(foodTextField5);

        calTextField5 = new JTextField(20);
        calTextField5.setBounds(400, 220, 150, 30);
        calTextField5.setVisible(false);
        this.add(calTextField5);

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
        this.add(addFood5);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 6
    public void foodEntry6() {
        indexLabel6 = new JLabel(Integer.toString(6));
        indexLabel6.setBounds(80, 250, 20, 30);
        indexLabel6.setVisible(false);
        this.add(indexLabel6);

        foodTextField6 = new JTextField(20);
        foodTextField6.setBounds(100, 250, 300, 30);
        foodTextField6.setVisible(false);
        this.add(foodTextField6);

        calTextField6 = new JTextField(20);
        calTextField6.setBounds(400, 250, 150, 30);
        calTextField6.setVisible(false);
        this.add(calTextField6);

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
        this.add(addFood6);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 7
    public void foodEntry7() {
        indexLabel7 = new JLabel(Integer.toString(7));
        indexLabel7.setBounds(80, 280, 20, 30);
        indexLabel7.setVisible(false);
        this.add(indexLabel7);

        foodTextField7 = new JTextField(20);
        foodTextField7.setBounds(100, 280, 300, 30);
        foodTextField7.setVisible(false);
        this.add(foodTextField7);

        calTextField7 = new JTextField(20);
        calTextField7.setBounds(400, 280, 150, 30);
        calTextField7.setVisible(false);
        this.add(calTextField7);

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
        this.add(addFood7);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 8
    public void foodEntry8() {
        indexLabel8 = new JLabel(Integer.toString(8));
        indexLabel8.setBounds(80, 310, 20, 30);
        indexLabel8.setVisible(false);
        this.add(indexLabel8);

        foodTextField8 = new JTextField(20);
        foodTextField8.setBounds(100, 310, 300, 30);
        foodTextField8.setVisible(false);
        this.add(foodTextField8);

        calTextField8 = new JTextField(20);
        calTextField8.setBounds(400, 310, 150, 30);
        calTextField8.setVisible(false);
        this.add(calTextField8);

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
        this.add(addFood8);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 9
    public void foodEntry9() {
        indexLabel9 = new JLabel(Integer.toString(9));
        indexLabel9.setBounds(80, 340, 20, 30);
        indexLabel9.setVisible(false);
        this.add(indexLabel9);

        foodTextField9 = new JTextField(20);
        foodTextField9.setBounds(100, 340, 300, 30);
        foodTextField9.setVisible(false);
        this.add(foodTextField9);

        calTextField9 = new JTextField(20);
        calTextField9.setBounds(400, 340, 150, 30);
        calTextField9.setVisible(false);
        this.add(calTextField9);

        addFood9 = new JButton("+");
        addFood9.setBounds(555, 340, 30, 30);
        addFood9.setVisible(false);
        addFood9.addActionListener(e -> {
            addFood9.setVisible(false);
            indexLabel10.setVisible(true);
            foodTextField10.setVisible(true);
            calTextField10.setVisible(true);
        });
        this.add(addFood9);
    }

    // MODIFIES: this
    // EFFECTS: opens food entry 10
    public void foodEntry10() {
        indexLabel10 = new JLabel(Integer.toString(10));
        indexLabel10.setBounds(80, 370, 20, 30);
        indexLabel10.setVisible(false);
        this.add(indexLabel10);

        foodTextField10 = new JTextField(20);
        foodTextField10.setBounds(100, 370, 300, 30);
        foodTextField10.setVisible(false);
        this.add(foodTextField10);

        calTextField10 = new JTextField(20);
        calTextField10.setBounds(400, 370, 150, 30);
        calTextField10.setVisible(false);
        this.add(calTextField10);
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

    public void addLog() {
        calendar.addEntry(log);  // IMPORTANT: adding an X to Y
    }


}

