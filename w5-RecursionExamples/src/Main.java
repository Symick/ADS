import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
//        System.out.println(Math.pow(1.0001, 5));
//        System.out.println(powerByTailRecursion(1.0001, 5));
//        System.out.println(powerBiSection(1.0001, 5));
////        System.out.println(bigPower(new BigDecimal(1.0001), 10000));
//        int pos = generateWallDesigns(5, "");
//        System.out.println("Amount of designs " + pos);
        System.out.println(calculateWallDesigns(45));
        System.out.println(calculateWallDesignsIterative(45));
    }

    static int factorial(int n) {
        if (n <= 1) {
            return n;
        }
        return n * factorial(n - 1);
    }

    static void printFor(String message, int n) {
        if (n == 0) {
            return;
        }
        System.out.println(message);
        printFor(message, n - 1);
    }

    static double powerByTailRecursion(double base, long exponent) {
        try {
            if (exponent == 0) return 1.0;

            if (exponent > 0) {
                return base * powerByTailRecursion(base, exponent - 1);
            } else {
                return 1.0 / (powerByTailRecursion(base, -exponent));
            }
        } catch (StackOverflowError ex) {
            return Double.NaN;
        }
    }

    static double powerBiSection(double base, long exponent) {
        if (exponent == 0) return 1;

        if (exponent < 0) {
            return 1.0 / powerBiSection(base, -exponent);
        }
        if (exponent % 2 == 0) {
            double x = powerBiSection(base, exponent / 2);
            return x * x;
        } else {
            return base * powerBiSection(base, exponent - 1);
        }
    }

    static BigDecimal bigPower(BigDecimal base, long exponent) {
        if (exponent == 0) return BigDecimal.ONE;

        if (exponent < 0) {
            return BigDecimal.ONE.divide(bigPower(base, -exponent));
        }
        if (exponent % 2 == 0) {
            BigDecimal x = bigPower(base, exponent / 2);
            return x.multiply(x);
        } else {
            return base.multiply(bigPower(base, exponent - 1));
        }
    }

    static int generateWallDesigns(int n, String currentDesign) {
        if (n == 0) {
            // Base case: print the current wall design when N becomes 0
            System.out.println(currentDesign);
            return 1;
        } else {
            int count = 0;
            if (n >= 1) {
                // Recursive case 1: add an upright brick ('O') and reduce N by 1
                count += generateWallDesigns(n - 1, currentDesign + "O");
            }
            // Recursive case 2: add a horizontal brick ('--') and reduce N by 2
            if (n >= 2) {
                count += generateWallDesigns(n - 2, currentDesign + "--");
            }
            return count;
        }
    }

    static int calculateWallDesigns(int n) {
        if (n == 0) {
            return 1;
        } else {
            int count = 0;
            if (n >= 1) {
                count += calculateWallDesigns(n - 1);
            }
            if (n >= 2) {
                count += calculateWallDesigns(n - 2);
            }
            return count;
        }
    }

    static int calculateWallDesignsIterative(int n) {
        //array for each nth wall size, which contains all the possible designs for a wall nn long.
        int[] designsPossibility = new int[n + 1];
        designsPossibility[0] = 1; // there is one wall design, an empty design
        designsPossibility[1] = 1; // is wall is on foot long, only an upright design is possible. i.e. one

        for (int i = 2; i <= n; i++) {
            designsPossibility[i] = designsPossibility[i-1] + designsPossibility[i-2]; // designs of i should be the sum of designs of previous to wall sizes
        }
        return designsPossibility[n];
    }
}