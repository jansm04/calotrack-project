package model;

import model.Date;

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
    Date date;

    // EFFECTS: constructs a calorie log with an empty list of foods and an empty list of calories, and with
    //          weight set to 0
    public CalorieLog() {
        this.foods = new LinkedList<>();
        this.cals = new LinkedList<>();
        this.weight = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds a food item to the list of foods
    public void addFood(String food) {
        foods.add(food);
        // NOTE: it is okay to have the same food twice as it is very possible for a user to
        //       eat the same food twice in one day
    }

    // MODIFIES: this
    // EFFECTS: adds a calorie number to the list of calories
    public void addNumOfCals(int calNum) {
        cals.add(calNum);
        // NOTE: it is also okay to have the same number twice, as it is very possible for a user
        //       to eat two foods with the same number of cals in one day
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

    // EFFECTS: returns sum of all calories in log
    public int totalCals() {
        int sum = 0;
        for (int c : cals) {
            sum += c;
        }
        return sum;
    }

    public void setDate(int day, String month, int year) {
        date = new Date(day, month, year);
    }

    public Date getDate() {
        return date;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public List<String> getFoods() {
        return foods;
    }

    public List<Integer> getCals() {
        return cals;
    }

}
