package ui;

import java.util.LinkedList;
import java.util.List;

// CalorieLog class: handles the user's food and calories for each food item
public class CalorieLog {

    List<String> foods;
    List<Integer> cals;
    double weight;
    // INVARIANT: the list of foods and the list of cals always have the same length, as the nth element
    //            in the list of calories is the number of calories contained by the nth element of the
    //            list of foods

    // EFFECTS: constructs a calorie log with an empty list of foods and an empty list of calories
    public CalorieLog() {
        this.foods = new LinkedList<>();
        this.cals = new LinkedList<>();
        this.weight = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a food item to the list of foods
    public void addFood(String food) {
        foods.add(food);
    }

    // MODIFIES: this
    // EFFECTS: adds a calorie number to the list of calories
    public void addNumOfCals(int calNum) {
        cals.add(calNum);
    }

    // MODIFIES: this
    // EFFECTS: removes a food item and its corresponding number of calories from
    //          the list of foods using the index of the item in the list
    public void removeFoodAndCals(int index) {
        for (int i = 0; i < foods.size(); i++) {
            if (i == index) {
                foods.remove(i);
                cals.remove(i);
                break;
            }
        }
    }

    // EFFECTS: prints out correct amount of space between columns
    private String space(String s) {
        int length = 20 - s.length();
        String tab = "";
        for (int i = 0; i < length; i++) {
            tab += " ";
        }
        return tab;
    }


    // EFFECTS: prints out the current log as a 3xn table, where n is the number of elements,
    //          the first column shows the index number, the second column shows the food, and
    //          the third column shows the calories. Below the table, the total calories for
    //          day is included
    public void viewLog() {
        System.out.println("#       Food                Calories");
        for (int i = 0; i < foods.size(); i++) {
            System.out.println(i + "       " + foods.get(i) + space(foods.get(i)) + cals.get(i));
        }
        System.out.println("Total Cals: " + totalCals());
        if (weight != 0) {
            System.out.println("Weight: " + weight + " lbs.");
        }
    }

    // EFFECTS: returns sum of all calories in log
    public int totalCals() {
        int sum = 0;
        for (int c : cals) {
            sum += c;
        }
        return sum;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public List<String> getFoods() {
        return foods;
    }

    public List<Integer> getCals() {
        return cals;
    }

}
