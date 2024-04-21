import java.util.Scanner;

public class Factorial {
    private int num;
    private long evenFactorial;
    private long oddFactorial;

    public Factorial() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите число: ");
        int n = in.nextInt();
        this.num = n;

        System.out.println("Четный факториал: " + evenFactorial(n));
        System.out.println("Нечетный факториал: " + oddFactorial(n));
    }

    private long evenFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        long result = 1;
        for (int i = 2; i <= n; i += 2) {
            result *= i;
        }
        this.evenFactorial = result;
        return result;
    }

    private long oddFactorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Число не может быть отрицательным");
        }
        long result = 1;
        for (int i = 1; i <= n; i += 2) {
            result *= i;
        }
        this.oddFactorial = result;
        return result;
    }

    public long getEvenFactorial() {
        return evenFactorial;
    }

    public long getOddFactorial() {
        return oddFactorial;
    }

    public int getNum() {
        return num;
    }
}
