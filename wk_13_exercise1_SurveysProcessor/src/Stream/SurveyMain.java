package Stream;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SurveyMain {
    private static final int NUM_ITEMS = 10000;
    public static void main(String[] args) {
        System.out.println("Welcome to Survey processing with streams");

        List<Survey> surveys = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS; i++) {
            surveys.add(new Survey());
        }

        //1. Calculate and print the total number of people in the households of all respondents.
        System.out.println("Total number of people in all househoulds: " +
                surveys.stream().mapToInt(Survey::getNumPeopleInHouseHold).sum());
        //2. Count and print how many surveys have been submitted by respondents from the area with
        //zipCodeDigits equal to 1091.
        System.out.println("Number of responses in 1091: " +
                surveys.stream().filter(survey -> survey.zipCodeDigits == 1091).count());
        //3. Find and print the average income from all survey responses in the area with zipCodeDigits equal
        //to 1091.
        System.out.printf("The average income in €1091: %.2f%n",
                surveys.stream().filter(survey -> survey.zipCodeDigits == 1091).mapToDouble(Survey::getNetIncome)
                        .average().orElse(0.0));

        //4. Find and print the Survey response with the lowest income of all.
        System.out.println("Survey with minimum income: " +
                surveys.stream().min(Comparator.comparingDouble(Survey::getNetIncome)).orElse(null));

        //5. Collect into a map and print how many surveys have been submitted for each zip code.
        //Make sure the output shows an ordered result by increasing zipCode
        //6. Calculate and print the map with minimum average income per person in the household by zip
        //code. (I.e. calculate the average income per person for each survey and find the minimum value
        //for each zip code).
        //7. Calculate the average number of people in the household by responsent age category of ten
        //years. (I.e. category=10 covers ages 10-19, category=20 covers ages 20-29, etc.)
        //8. Calculate the average income per person in the household by zip code.
        //This equals the sum of all incomes of all surveys within a zip code area divided by the total
        //number of people in the corresponding households of that zip code area... Tough challenge?
        //Hint1: Stream all surveys and use flatmap to replace each survey in the stream by a substream of
        //(zipcode,incomePerPerson) entries, one for each person in the household. Then collect the
        //stream of entries into a map...
        //Hint2: Investigate Stream.generate(), Map.entry() and .limit()
    }
}
