package model;

// Calculator class: inputs all user variables and calculates required daily caloric intake
public class Calculator {

    String gender;
    int age;
    int heightFeet;
    int heightInches;
    double weight;
    double weightGoal; // pounds desired to either gain or lose
    int levelOfActivity; // weekly level of activity from 1-5 (1 being the lowest)
    String objective; //gain, lose, or maintain
    int time; //in weeks

    // EFFECTS: constructs a calculator
    public Calculator() {
    }

    // REQUIRES: gender is one of:
    //                  - "male"
    //                  - "female"
    //           age > 0
    //           heightFeet > 0
    //           heightInches > 0
    //           weight > 0
    // EFFECTS: calculates BMR (Basal Metabolic Rate) based on gender (rounds to int)
    public int bmrCalculator() {
        double heightInCm = (heightFeet * 12 + heightInches) * 2.54;
        double weightInKg = weight * 0.453592;
        int bmr;
        if (gender.equals("male")) {
            bmr = (int) (10 * weightInKg + 6.25 * heightInCm - 5 * age + 5);
        } else {
            bmr = (int) (10 * weightInKg + 6.25 * heightInCm - 5 * age - 161);
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
                return 0;
        }
    }




    // REQUIRES: weightGoal > 0
    //           time > 0
    // EFFECTS: calculates daily cals needed to be consumed/subtracted to reach weight goal
    private int differenceCalculator() {
        int days = time * 7;
        double calsNeeded = weightGoal * 3500;
        return (int) (calsNeeded / days);
    }


    // REQUIRES: objective is one of:
    //                  - "gain"
    //                  - "lose"
    // EFFECTS: calculates and returns total daily caloric requirement
    public int totalDailyCaloricRequirement() {
        int total = 0;
        switch (objective) {
            case "gain":
                total = bmrCalculator() + differenceCalculator() + activityBonus();
                break;
            case "lose":
                total = bmrCalculator() - differenceCalculator() + activityBonus();
                break;
            case "maintain":
                total = bmrCalculator() + activityBonus();
                break;
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

    public void setHeightFeet(int heightFeet) {
        this.heightFeet = heightFeet;
    }

    public void setHeightInches(int heightInches) {
        this.heightInches = heightInches;
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
