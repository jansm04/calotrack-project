package persistence;

import model.Calculator;
import model.Calendar;
import model.CalorieLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// JsonReader class: reads a calendar from a file stored in Json data
public class JsonReader {

    private String source;

    // EFFECTS: constructs a JsonReader to read a calendar from the source
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads calendar from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Calendar read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCalendar(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();
        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }
        return contentBuilder.toString();
    }

    // EFFECTS: parses calendar from JSON object and returns it
    private Calendar parseCalendar(JSONObject jsonObject) {
        Calendar calendar = Calendar.getInstance();
        JSONObject jsonCalc = (JSONObject) jsonObject.get("calculator");
        addCalculator(calendar, jsonCalc);
        addCalorieLogs(calendar, jsonObject);
        return calendar;
    }

    // MODIFIES: calendar
    // EFFECTS: parses calorieLogs from JSON object and adds them to calendar
    private void addCalorieLogs(Calendar calendar, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {
            JSONObject nextCL = (JSONObject) json;
            addCalorieLog(calendar, nextCL);
        }
    }

    // MODIFIES: calendar
    // EFFECTS: parses calorieLog from JSON object and adds it to calendar
    private void addCalorieLog(Calendar calendar, JSONObject jsonObject) {
        CalorieLog calorieLog = new CalorieLog();
        //addFoods(calorieLog)
        JSONArray foodsObjects = jsonObject.getJSONArray("foods");
        for (Object json : foodsObjects) {
            JSONObject food = (JSONObject) json;
            String name = food.getString("food");
            calorieLog.addFood(name);
        }
        JSONArray calsObjects = jsonObject.getJSONArray("cals");
        for (Object json : calsObjects) {
            JSONObject cal = (JSONObject) json;
            int num = cal.getInt("num");
            calorieLog.addNumOfCals(num);
        }
        double weight = jsonObject.getDouble("weight");
        JSONObject jsonObject1 = (JSONObject) jsonObject.get("date");
        calorieLog.setWeight(weight);
        calorieLog.setDate(
                jsonObject1.getInt("day"),
                jsonObject1.getString("month"),
                jsonObject1.getInt("year"));
        calendar.addEntry(calorieLog);
    }

    // MODIFIES: calendar
    // EFFECTS: parses date from JSON object and adds it to calorieLog
    private void addCalculator(Calendar calendar, JSONObject jsonObject) {
        Calculator calc = Calculator.getInstance();
        String gender = jsonObject.getString("gender");
        int age = jsonObject.getInt("age");
        double height = jsonObject.getDouble("height");
        double weight = jsonObject.getDouble("weight");
        double weightGoal = jsonObject.getDouble("weightGoal");
        int levelOfActivity = jsonObject.getInt("levelOfActivity");
        String objective = jsonObject.getString("objective");
        int time = jsonObject.getInt("time");
        calc.setGender(gender);
        calc.setAge(age);
        calc.setHeight(height);
        calc.setWeight(weight);
        calc.setWeightGoal(weightGoal);
        calc.setLevelOfActivity(levelOfActivity);
        calc.setObjective(objective);
        calc.setTime(time);
    }


}
