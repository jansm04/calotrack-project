package ui.panels;

import model.Calculator;
import model.Calendar;

import javax.swing.*;
import java.awt.*;

public class CalculatorPanel extends JPanel implements PanelSetup {

    Calendar calendar;
    Calculator calculator;
    String objective;
    int weightGoal;

    JTextField genderAnswer;
    JTextField ageAnswer;
    JTextField weightAnswer;
    JTextField heightAnswer;
    JTextField weightGoalAnswer;
    JTextField objectiveAnswer;
    JTextField loaAnswer;
    JTextField timeAnswer;

    JLabel result;
    JLabel weightGoalQuestion;
    JLabel timeQuestion;
    JButton enterSpecifics;


    public CalculatorPanel() {
        this.calendar = Calendar.getInstance();
        this.calculator = Calculator.getInstance();
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
        this.addResult();
        this.getInfo();
        this.enterButton1();
        this.enterButton2();
        this.addRectangle();
    }



    // MODIFIES: this
    // EFFECTS: adds result to calcPanel
    public void addResult() {
        int total = calculator.totalDailyCaloricRequirement();
        JLabel result = new JLabel("Daily Caloric Requirement: ");
        result.setForeground(Color.white);
        result.setBounds(500, 500, 400, 30);
        if (total > 0) {
            result.setText("Daily Caloric Requirement: "
                    + calculator.totalDailyCaloricRequirement()
                    + " Cals");
        }
        this.add(result);
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
        genderQuestion.setForeground(Color.white);
        add(genderQuestion);

        genderAnswer = new JTextField(20);
        genderAnswer.setBounds(50, 80, 200, 30);
        this.add(genderAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter age
    public void agePrompt() {
        JLabel ageQuestion = new JLabel("What is your age?");
        ageQuestion.setBounds(50, 120, 400, 30);
        ageQuestion.setForeground(Color.white);
        this.add(ageQuestion);

        ageAnswer = new JTextField(20);
        ageAnswer.setBounds(50, 150, 200, 30);
        this.add(ageAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter height
    public void heightPrompt() {
        JLabel heightQuestion = new JLabel("What is your height (in cm)?");
        heightQuestion.setBounds(50, 190, 400, 30);
        heightQuestion.setForeground(Color.white);
        this.add(heightQuestion);

        heightAnswer = new JTextField(20);
        heightAnswer.setBounds(50, 220, 200, 30);
        this.add(heightAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weight
    public void weightPrompt() {
        JLabel weightQuestion = new JLabel("What is your weight (in kg)?");
        weightQuestion.setBounds(50, 260, 400, 30);
        weightQuestion.setForeground(Color.white);
        this.add(weightQuestion);

        weightAnswer = new JTextField(20);
        weightAnswer.setBounds(50, 290, 200, 30);
        this.add(weightAnswer);
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
        loaQuestion1.setForeground(Color.white);
        loaQuestion2.setForeground(Color.white);
        loaQuestion3.setForeground(Color.white);
        loaQuestion4.setForeground(Color.white);
        loaQuestion5.setForeground(Color.white);
        loaQuestion6.setForeground(Color.white);
        loaQuestion7.setForeground(Color.white);
        loaQuestion1.setBounds(50, 340, 400, 20);
        loaQuestion2.setBounds(50, 360, 400, 20);
        loaQuestion3.setBounds(70, 380, 400, 20);
        loaQuestion4.setBounds(70, 400, 400, 20);
        loaQuestion5.setBounds(70, 420, 400, 20);
        loaQuestion6.setBounds(70, 440, 400, 20);
        loaQuestion7.setBounds(70, 460, 400, 20);
        this.add(loaQuestion1);
        this.add(loaQuestion2);
        this.add(loaQuestion3);
        this.add(loaQuestion4);
        this.add(loaQuestion5);
        this.add(loaQuestion6);
        this.add(loaQuestion7);
        addLoaAnswer();
    }

    // MODIFIES: this
    // EFFECTS: adds loaAnswer to calcPanel
    public void addLoaAnswer() {
        loaAnswer = new JTextField(20);
        loaAnswer.setBounds(50, 490, 200, 30);
        this.add(loaAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter objective
    public void objectivePrompt() {
        JLabel objectiveQuestion1 = new JLabel("Would you like to gain, lose or maintain your current weight?");
        JLabel objectiveQuestion2 = new JLabel("(Enter either \"gain\", \"lose\" or \"maintain\")");
        objectiveQuestion1.setBounds(500, 50, 400, 20);
        objectiveQuestion2.setBounds(500, 70, 400, 20);
        objectiveQuestion1.setForeground(Color.white);
        objectiveQuestion2.setForeground(Color.white);
        this.add(objectiveQuestion1);
        this.add(objectiveQuestion2);

        objectiveAnswer = new JTextField(20);
        objectiveAnswer.setBounds(500, 100, 200, 30);
        this.add(objectiveAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter weightGoal
    public void weightGoalPrompt() {
        weightGoalQuestion = new JLabel("How much weight would you like to gain/lose? (In kg)");
        weightGoalQuestion.setBounds(500, 160, 400, 30);
        weightGoalQuestion.setForeground(Color.white);
        weightGoalQuestion.setVisible(false);
        this.add(weightGoalQuestion);

        weightGoalAnswer = new JTextField(20);
        weightGoalAnswer.setBounds(500, 190, 200, 30);
        weightGoalAnswer.setVisible(false);
        this.add(weightGoalAnswer);
    }

    // MODIFIES: this
    // EFFECTS: prompts user to enter time
    public void timePrompt() {
        timeQuestion = new JLabel("In how many weeks would you like to achieve this goal?");
        timeQuestion.setBounds(500, 230, 400, 30);
        timeQuestion.setForeground(Color.white);
        timeQuestion.setVisible(false);
        this.add(timeQuestion);

        timeAnswer = new JTextField(20);
        timeAnswer.setBounds(500, 260, 200, 30);
        timeAnswer.setVisible(false);
        this.add(timeAnswer);
    }

    // MODIFIES: this
    // EFFECTS: sets up the first "enter" button
    public void enterButton1() {
        JButton enterObjective = new JButton("Enter");
        enterObjective.setBounds(800, 80, 90, 30);
        this.add(enterObjective);
        enterObjective.addActionListener(e -> {
            collectGoalAnswers();
            if (!objective.equals("maintain")) {
                weightGoalQuestion.setVisible(true);
                weightGoalAnswer.setVisible(true);
                timeQuestion.setVisible(true);
                timeAnswer.setVisible(true);
                enterSpecifics.setVisible(true);
            } else {
                result.setText("Daily Caloric Requirement: " + calculator.totalDailyCaloricRequirement() + " Cals");
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for goal
    public void collectGoalAnswers() {
        String gender = genderAnswer.getText();
        calculator.setGender(gender);
        int age = Integer.parseInt(ageAnswer.getText());
        calculator.setAge(age);
        int height = Integer.parseInt(heightAnswer.getText());
        calculator.setHeight(height);
        int weight = Integer.parseInt(weightAnswer.getText());
        calculator.setWeight(weight);
        int levelOfActivity = Integer.parseInt(loaAnswer.getText());
        calculator.setLevelOfActivity(levelOfActivity);
        String objective = objectiveAnswer.getText();
        calculator.setObjective(objective);
    }

    // MODIFIES: this
    // EFFECTS: collect all user answers for specifics
    public void collectSpecificsAnswers() {
        weightGoal = Integer.parseInt(weightGoalAnswer.getText());
        calculator.setWeightGoal(weightGoal);
        int time = Integer.parseInt(timeAnswer.getText());
        calculator.setTime(time);
    }

    // MODIFIES: this
    // EFFECTS: sets up the second "enter" button
    public void enterButton2() {
        enterSpecifics = new JButton("Save");
        enterSpecifics.setBounds(800, 300, 90, 30);
        enterSpecifics.setVisible(false);
        this.add(enterSpecifics);
        enterSpecifics.addActionListener(e -> {
            collectGoalAnswers();
            collectSpecificsAnswers();
            result.setText("Daily Caloric Requirement: "
                    + calculator.totalDailyCaloricRequirement()
                    + " Cals");
        });


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





}
