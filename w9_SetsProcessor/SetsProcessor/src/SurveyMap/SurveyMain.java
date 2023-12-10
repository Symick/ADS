package SurveyMap;

import java.util.*;

public class SurveyMain {
    private static int NUM_ITEMS = 10000;

    public static void main(String[] args) {
        System.out.println("Welcome to Survey processing with a Map");

        List<Survey> surveys = new ArrayList<>();
        for (int i = 0; i < NUM_ITEMS; i++) {
            surveys.add(new Survey());
        }

        Map<Integer, Integer> numSurveysByZipCode = new TreeMap<>();
        Map<Integer, Integer> numSurveysByAge = new TreeMap<>();
        Map<Integer, Integer> peopleByZipCode = new TreeMap<>();
        Map<Integer, Double> maxIncomeByAge = new TreeMap<>();
        Map<Integer, Double> minIncomePersonInHousehold = new TreeMap<>();
        Map<Double, Integer> zipCodesByMinIncomePerPersonInHouseHold = new TreeMap<>();

        for (Survey survey: surveys) {
            numSurveysByZipCode.put(survey.zipCodeDigits, numSurveysByZipCode.getOrDefault(survey.zipCodeDigits, 0) + 1);

            numSurveysByAge.merge(survey.age, 1, Integer::sum);

            peopleByZipCode.merge(survey.zipCodeDigits, survey.numPeopleInHouseHold, Integer::sum);

            maxIncomeByAge.merge(survey.age, survey.netIncome, Double::max);

            double income = survey.netIncome / survey.numPeopleInHouseHold;
            minIncomePersonInHousehold.merge(survey.zipCodeDigits, income, Double::min);
        }

        minIncomePersonInHousehold.forEach((zipCode, income) -> zipCodesByMinIncomePerPersonInHouseHold.put(income, zipCode));

        System.out.println(numSurveysByZipCode);
        System.out.println(numSurveysByAge);
        System.out.println(peopleByZipCode);
        System.out.println(maxIncomeByAge);
        System.out.println(minIncomePersonInHousehold);
        System.out.println(zipCodesByMinIncomePerPersonInHouseHold);
    }
}
