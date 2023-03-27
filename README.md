# "DietKing" Personal Project

By: Jan S.

## Project Description:

The purpose of this project is to help people gain, lose, or
maintain their current weight by providing a platform to help
them understand the numbers behind their goals, as well as log
their daily progress. The application will include a calorie 
calculator that prompts the user to input several variables, 
such as height, weight, gender, age, weekly level of exercise,
target weight, target time, etc. The calculator then takes all
of these inputs and outputs one number: the daily caloric 
requirement. Users will then be able to save this requirement
so that it appears as a daily goal on the calorie tracker side
of the application. The calorie tracker will then allow users 
to log all the food they eat in a day, as well as the calories
in each item. Once each day is over, users can add the 'day' 
to the application's calendar, which will be a list of all 
the previous days and their statistics. The calendar will 
ultimately allow users to view their progress, and make sure 
they are on track with their goals.

This application can be used by anyone looking to lose weight,
bulk up, or even simply maintain their current weight.

This project is of interest to me because I myself have gone
through the whole process of trying to gain weight and put 
on a bit of muscle. And throughout this process, I had to 
look for and use a ton of different calculators and 
applications to find out and keep track of my numbers. 
If I had an application like this, I would be able to 
access all these things conveniently in one place.

## User Stories:
- As a user, I want to be able to find out how many calories I need to consume a day in order to meet my weight goal
- As a user, I want to be able to log all the food and calories that I've consumed in a day and see my total calorie count for that day
- As a user, I want to be able to add each day's data to a list of all the previous days and their data
- As a user, I want to be able to view the data from previous days, so I can see how I've been progressing  
- As a user, I want to have the option of saving my entire Calendar to file
- As a user, I want to have the option to load a previously saved Calendar and resume logging calories to that specific Calendar 


Data Persistence Model Based On
https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo


# Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by clicking the 'x' button in the top right corner of each day to remove a day from the calendar
- You can generate the second required action related to adding Xs to a Y by clicking the 'View Log' button at the bottom of each day to view more details about a day in the calendar
- You can locate my visual component at the home screen - it is the big "DietKing" logo (imported from a .png file)
- You can save the state of my application by clicking the 'Save Calendar' button on the home screen
- You can reload the state of my application by clicking the 'Load Calendar' button on the home screen