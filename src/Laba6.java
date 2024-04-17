import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Laba6 {

    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "matrix";
        while (true) {

            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Ввод двух матриц и их умножение");
            System.out.println("4 - Excel");
            System.out.println("5 - Выход");
            System.out.println("--------------------------------------------------");
            System.out.print("");
            System.out.print("Введите операцию: ");

            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }

            int input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input > 5) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 5) {
                break;
            }

            switch (input) {
                case 1:
                    helper.show_table();
                    break;
                case 2:
                    helper.create_table();
                    break;
                case 3:
                    Matrix matrix = new Matrix(3);
                    helper.execute_Update("INSERT INTO " + tablename + " (matrix1,matrix2) VALUES ('" + Arrays.deepToString(matrix.getFirstMatrix()) + "','" + Arrays.deepToString(matrix.getSecondMatrix()) + "')");
                    System.out.println("--------------------------------------------------");
                    System.out.println("Перемножить матрицы? 1 - да, 0 - нет");
                    System.out.println("--------------------------------------------------");
                    while (true) {
                        while (!in.hasNextInt()) {
                            System.out.print("Ошибка! Введите корректное число: ");
                            in.next();
                        }

                        int answer = in.nextInt();
                        System.out.println("--------------------------------------------------");
                        if (answer > 1) {
                            System.out.println("Неверный ввод числа!");
                        }
                        if (answer == 1) {
                            int[][] rs =  matrix.multiplyMatrices();
                            helper.execute_Update("UPDATE " + tablename + " SET multiply = '" + Arrays.deepToString(rs) + "' WHERE matrix1= '" + Arrays.deepToString(matrix.getFirstMatrix()) + "';");
                            break;
                        } else if (answer == 0) {
                            break;
                        }
                    }
                    break;
                case 4:
                    helper.to_excel(tablename,"");
                    break;



            }

        }
    }
}
