import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        System.out.println(Math.pow(1.0001, 5));
        System.out.println(powerByTailRecursion(1.0001, 5));
        System.out.println(powerBiSection(1.0001, 5));
//        System.out.println(bigPower(new BigDecimal(1.0001), 10000));
    }

    static int factorial(int n) {
        if (n <= 1) {
            return n;
        }
        return n * factorial(n-1);
    }

    static void printFor(String message, int n) {
        if (n == 0) {
            return;
        }
        System.out.println(message);
        printFor(message, n-1);
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
            return x*x;
        } else {
            return base * powerBiSection(base, exponent -1);
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
            return base.multiply(bigPower(base, exponent -1));
        }
    }
}