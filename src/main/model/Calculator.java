package model;

import org.json.JSONObject;
import persistence.Writable;

// Calculator class: inputs all user variables and calculates required daily caloric intake
public class Calculator implements Writable {

    String gender; // male or female
    int age;
    double height; // in cm
    double weight; // in kg
    double weightGoal; // pounds desired to either gain or lose
    int levelOfActivity; // weekly level of activity from 1-5 (1 being the lowest)
    String objective; // gain, lose, or maintain
    int time; //in weeks

    private static final Calculator calculator = new Calculator();

    public static Calculator getInstance() {
        return calculator;
    }

    // EFFECTS: constructs a calculator
    private Calculator() {
        this.gender = "";
        this.objective = "";
    }

    // REQUIRES: gender is one of:
    //                  - "male"
    //                  - "female"
    //           age > 0
    //           height > 0
    //           weight > 0
    // EFFECTS: calculates BMR (Basal Metabolic Rate) based on gender (rounds to int)
    public int bmrCalculator() {
        int bmr;
        if (gender.equals("male")) {
            bmr = (int) (10 * weight + 6.25 * height - 5 * age + 5); // calculates bmr for men
        } else {
            bmr = (int) (10 * weight + 6.25 * height - 5 * age - 161); // calculates bmr for women
        }
        return bmr;
    }


    // REQUIRES: 1 <= levelOfActivity <= 5
    // EFFECTS: converts levelOfActivity to additional cals needed to be consumed each day
    private int activityBonus() {
        switch (levelOfActivity) {
            case 2:
                return 250;
            case 3:
                return 500;
            case 4:
                return 750;
            case 5:
                return 1000;
            default:
                return 0; // if levelOfActivity is 1, then method returns default value
        }
    }


    // REQUIRES: weightGoal > 0
    //           time > 0
    // EFFECTS: calculates daily cals needed to be consumed/subtracted to reach weight goal
    private int differenceCalculator() {
        int days = time * 7; // converts time from weeks to days
        double calsNeeded = weightGoal * 7700; // one kg of weight equates to roughly 3500 calories
        return (int) (calsNeeded / days); // divided cals (in weight) by number of days, and takes int approx.
    }


    // REQUIRES: objective is one of:
    //                  - "gain"
    //                  - "lose"
    //                  - "maintain"
    // EFFECTS: calculates and returns total daily caloric requirement by adding BMR with additional cals
    //          burned through exercise, and then possible adding/subtracting weight goal difference according
    //          to objective
    public int totalDailyCaloricRequirement() {
        int total;
        if (objective.equals("gain")) {
            total = bmrCalculator() + differenceCalculator() + activityBonus(); // adds difference for weight gain
        } else if (objective.equals("lose")) {
            total = bmrCalculator() - differenceCalculator() + activityBonus(); // subtracts difference for weight loss
        } else {
            total = bmrCalculator() + activityBonus();
        }
        return total;
    }

    public String getObjective() {
        return objective;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public double getWeight() {
        return weight;
    }

    public double getWeightGoal() {
        return weightGoal;
    }

    public int getLevelOfActivity() {
        return levelOfActivity;
    }

    public int getTime() {
        return time;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setLevelOfActivity(int levelOfActivity) {
        this.levelOfActivity = levelOfActivity;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public void setWeightGoal(double weightGoal) {
        this.weightGoal = weightGoal;
    }

    public void setTime(int time) {
        this.time = time;
    }

    // EFFECTS: converts a calc into a Json object
    @Override
    public JSONObject toJSonObject() {
        JSONObject json = new JSONObject();
        json.put("gender", gender);
        json.put("age", age);
        json.put("height", height);
        json.put("weight", weight);
        json.put("weightGoal", weightGoal);
        json.put("levelOfActivity", levelOfActivity);
        json.put("objective", objective);
        json.put("time", time);
        return json;
    }
}
