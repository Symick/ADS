public class Main {
    public static void main(String[] args) {
        System.out.println(factorial(4));
        printFor("Hallo", 3);
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
}