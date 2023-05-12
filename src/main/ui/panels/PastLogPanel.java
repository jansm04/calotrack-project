package ui.panels;

import model.CalorieLog;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class PastLogPanel extends JPanel implements PanelSetup {

    CalorieLog logToView;

    @Override
    public void setPanelLayout() {
        this.setBorder(BorderFactory.createEmptyBorder());
        this.setBackground(Color.getHSBColor(0f, 0f, 0.03f));
        this.setSize(1000, 700);
        this.setLayout(null);
        this.setVisible(false);
    }

    @Override
    public void setup() {
        this.returnButtonForViewLogPanel();
    }

    public void setCalorieLog(CalorieLog logToView) {
        this.logToView = logToView;
    }

    // MODIFIES: this
    // EFFECTS: adds a JLabel of a smaller version of the DietKing logo
    public void addSmallCalotrackLogo() {
        BufferedImage myPicture = null;
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
    // EFFECTS: sets up a return button that returns user to optionsPanel
    public void returnButtonForViewLogPanel() {
        JButton returnToOptions = new JButton("Return");
        returnToOptions.setBounds(930, 10, 60, 20);
        returnToOptions.addActionListener(e -> {
            this.setVisible(false);
            this.removeAll();
        });
        this.add(returnToOptions);
    }

    public void viewLog() {
        viewEntries();
        createWeightLabelForView();
        createDateLabelForView();
        shapeRectangleForViewLogPanel();
        returnButtonForViewLogPanel();
        addSmallCalotrackLogo();
    }

    // MODIFIES: this
    // EFFECTS: prints out CalorieLog on viewLogPanel
    public void viewEntries() {
        header();
        java.util.List<String> foods = logToView.getFoods();
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
        this.add(foodLabel);

        JLabel calLabel = new JLabel("Calories:");
        calLabel.setBounds(400, 70, 150, 30);
        this.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: prints out all food entries + index
    public void createFoodLabel(String food, int index, int y) {
        JLabel indexLabel = new JLabel(Integer.toString(index + 1));
        indexLabel.setBounds(80, y, 20, 30);
        this.add(indexLabel);

        JLabel foodLabel = new JLabel(" " + food);
        foodLabel.setBounds(100, y, 290, 25);
        foodLabel.setBackground(Color.lightGray);
        foodLabel.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
        foodLabel.setOpaque(true);
        this.add(foodLabel);
    }

    // MODIFIES: this
    // EFFECTS: prints out calorie entries
    public void createCalLabel(int cal, int y) {
        JLabel calLabel = new JLabel(" " + Integer.toString(cal));
        calLabel.setBounds(400, y, 150, 25);
        calLabel.setBackground(Color.lightGray);
        calLabel.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
        calLabel.setOpaque(true);
        this.add(calLabel);
    }

    // MODIFIES: this
    // EFFECTS: if not null, prints out weight
    public void createWeightLabelForView() {
        JLabel weightLabel;
        if (logToView.getWeight() != 0) {
            weightLabel = new JLabel("Today's Weight:");
            JLabel weight = new JLabel(" " + logToView.getWeight());
            weight.setBounds(80, 470, 150, 30);
            weight.setBackground(Color.lightGray);
            weight.setBorder(BorderFactory.createLineBorder(Color.getHSBColor(0.4f, 0.4f, 0.35f), 1));
            weight.setOpaque(true);
            this.add(weight);
        } else {
            weightLabel = new JLabel("No weight specified.");
        }
        weightLabel.setBounds(80, 440, 150, 30);
        this.add(weightLabel);
    }

    // MODIFIES: this
    // EFFECTS: if not null, prints out date
    public void createDateLabelForView() {
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
            this.add(date);
        } else {
            dateLabel = new JLabel("No date specified.");
        }
        dateLabel.setBounds(80, 510, 150, 30);
        this.add(dateLabel);
    }


    // MODIFIES: this
    // EFFECTS: opens a rectangle in the background
    public void shapeRectangleForViewLogPanel() {
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



}
