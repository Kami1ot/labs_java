import java.sql.SQLException;
import java.util.Scanner;

public class Laba6 {

    public static void main(String[] args) throws SQLException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "matrix";
        while (true) {

            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Ввод двух матриц и их умножение");
            System.out.println("4 - Выход");
            System.out.println("--------------------------------------------------");
            System.out.print("");
            System.out.print("Введите операцию: ");

            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }

            int input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input > 4) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 4) {
                break;
            }

            switch (input) {
                case 1:
                    helper.show_table();
                case 3:
                    Matrix matrix = new Matrix(3);
                    matrix.multiplyMatrices();
            }

        }
    }
}
