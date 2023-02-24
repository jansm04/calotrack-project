package model;

// Calculator class: inputs all user variables and calculates required daily caloric intake
public class Calculator {

    String gender; // male or female
    int age;
    double height; // in cm
    double weight; // in lbs
    double weightGoal; // pounds desired to either gain or lose
    int levelOfActivity; // weekly level of activity from 1-5 (1 being the lowest)
    String objective; // gain, lose, or maintain
    int time; //in weeks

    // REQUIRES: gender is one of:
    //                  - "male"
    //                  - "female"
    //           age > 0
    //           heightFeet > 0
    //           heightInches > 0
    //           weight > 0
    // EFFECTS: calculates BMR (Basal Metabolic Rate) based on gender (rounds to int)
    public int bmrCalculator() {
        double weightInKg = weight * 0.453592; // converts weight from lbs to kg
        int bmr;
        if (gender.equals("male")) {
            bmr = (int) (10 * weightInKg + 6.25 * height - 5 * age + 5); // calculates bmr for men
        } else {
            bmr = (int) (10 * weightInKg + 6.25 * height - 5 * age - 161); // calculates bmr for women
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
        double calsNeeded = weightGoal * 3500; // one pound of weight equates to roughly 3500 calories
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
}
