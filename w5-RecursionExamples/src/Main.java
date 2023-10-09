import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        System.out.println(Math.pow(1.0001, 5));
//        System.out.println(powerByTailRecursion(1.0001, 5));
//        System.out.println(powerBiSection(1.0001, 5));
////        System.out.println(bigPower(new BigDecimal(1.0001), 10000));
//        int pos = generateWallDesigns(5, "");
//        System.out.println("Amount of designs " + pos);
//        System.out.println(calculateWallDesigns(45));
//        System.out.println(calculateWallDesignsIterative(45));

        Scanner input = new Scanner(System.in);
        System.out.print("How many groups do you need? ");
        int nGroups = input.nextInt();
        System.out.print("How many students do you have? ");
        int nStudents = input.nextInt();
        System.out.println("\nYour groups:");
        printGroups(nGroups, nStudents);
        System.out.print("\nHow many additional international students do you have? ");
        int nInternational = input.nextInt();
        System.out.println("\nYour mixed groups:");
        printGroups(nGroups, nStudents, nInternational);
        System.out.println("\nYour labeled groups:");

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

    static void printGroups(int nGroups, int nStudents) {
        if (nGroups % 2 ==0) {
            int halfGroup = nGroups / 2;
            int firstHalfOfStudents = nStudents / 2;
            int secondHalfOfStudents = nStudents - firstHalfOfStudents;
            printGroupHelper(halfGroup, firstHalfOfStudents, 0, 0);
            printGroupHelper(halfGroup, secondHalfOfStudents, 0, firstHalfOfStudents);
        } else {
            printGroupHelper(nGroups, nStudents, 0, 0);
        }
    }

    static void printGroupHelper(int remainingGroup, int remainingStudents, int nStudentInGroup, int studentNumber) {
        if (remainingStudents <= 0) return; //base case if all groups are printed


        int studentsInGroup = (int) Math.ceil(remainingStudents / (double) remainingGroup);
        if (nStudentInGroup < studentsInGroup) {
            System.out.print("s" + (remainingStudents - nStudentInGroup + studentNumber) + " ");
            printGroupHelper(remainingGroup, remainingStudents, nStudentInGroup + 1, studentNumber);
        } else {
            System.out.println();
            printGroupHelper(remainingGroup - 1, remainingStudents - studentsInGroup, 0, studentNumber);
        }

    }
    static void printGroups(int nGroups, int nStudents, int nInternationals) {}
}