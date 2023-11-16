package SurveyMap;

import java.util.Random;

public class Survey {
    private static Random random = new Random(1L);

    public int zipCodeDigits;
    public int age;
    public double netIncome;
    public int numPeopleInHouseHold;

    public Survey() {
        zipCodeDigits = 1000 + random.nextInt(101);
        age = 15 + random.nextInt(66);
        int a = random.nextInt(98);
        netIncome = 591 + Math.pow(a, 2);
        double x = random.nextDouble();
        numPeopleInHouseHold = (int) (1 + 7 * Math.pow(x, 3));
    }

}
