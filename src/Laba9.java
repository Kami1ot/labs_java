import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Laba9 {

    public static void main (String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba9";
        while (true){
            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Ввести матрицы и операции с ними");
            System.out.println("4 - Сохранить в Exel");
            System.out.println("5 - Выход");
            System.out.println("--------------------------------------------------");
            System.out.print("");
            System.out.print("Введите операцию: ");

            while(!in.hasNextInt()) {
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
                    while (true) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("1. Умножение");
                        System.out.println("2. Сложение");
                        System.out.println("3. Вычитание");
                        System.out.println("4. Возведение числа в степень");
                        System.out.println("5. Выйти");
                        System.out.println("--------------------------------------------------");
                        System.out.print("Введите операцию: ");
                        while (!in.hasNextInt()) {
                            System.out.print("Ошибка! Введите корректное число: ");
                            in.next();
                        }
                        input = in.nextInt();
                        if (input > 5) {
                            System.out.println("Неверный ввод числа!");
                            continue;
                        }
                        if (input == 5) break;
                        int[][] end;
                        switch (input) {
                            case (1):
                                MatrixMultiply matrix = new MatrixMultiply(2);
                                end = matrix.multiplyMatrices();
                                System.out.println("--------------------------------------------------");
                                System.out.println("Ответ равен: " + (Arrays.deepToString(end)));
                                in.nextLine();
                                helper.execute_Update("INSERT INTO " + tablename + " (matrix1, operation, matrix2, result) VALUES ('" + Arrays.deepToString(matrix.getFirstMatrix()) + "', 'Умножение', '" + Arrays.deepToString(matrix.getSecondMatrix()) + "', '" + Arrays.deepToString(end) + "')");
                                break;
                            case (2):
                                MatrixSum matrixSum = new MatrixSum(2);
                                end = matrixSum.sumMatrices();
                                System.out.println("--------------------------------------------------");
                                System.out.println("Ответ равен: " + (Arrays.deepToString(end)));
                                in.nextLine();
                                helper.execute_Update("INSERT INTO " + tablename + " (matrix1, operation, matrix2, result) VALUES ('" + Arrays.deepToString(matrixSum.getFirstMatrix()) + "', 'Сложение', '" + Arrays.deepToString(matrixSum.getSecondMatrix()) + "', '" + Arrays.deepToString(end) + "')");
                                break;
                            case (3):
                                MatrixSubstract matrixSubstract = new MatrixSubstract(2);
                                end = matrixSubstract.subtractMatrices();
                                System.out.println("--------------------------------------------------");
                                System.out.println("Ответ равен: " + (Arrays.deepToString(end)));
                                in.nextLine();
                                helper.execute_Update("INSERT INTO " + tablename + " (matrix1, operation, matrix2, result) VALUES ('" + Arrays.deepToString(matrixSubstract.getFirstMatrix()) + "', 'Вычитание', '" + Arrays.deepToString(matrixSubstract.getSecondMatrix()) + "', '" + Arrays.deepToString(end) + "')");
                                break;
                            case (4):
                                MatrixPower matrixPower = new MatrixPower(2,true);
                                System.out.print("Введите степень: ");
                                while (!in.hasNextInt()) {
                                    System.out.print("Ошибка! Введите корректное число: ");
                                    in.next();
                                }

                                int power = in.nextInt();
                                end = matrixPower.powerMatrix(power);
                                System.out.println("--------------------------------------------------");
                                System.out.println("Ответ равен: " + (Arrays.deepToString(end)));
                                in.nextLine();
                                helper.execute_Update("INSERT INTO " + tablename + " (matrix1, operation, matrix2, result) VALUES ('" + Arrays.deepToString(matrixPower.getFirstMatrix()) + "', 'Степень', '" + power + "', '" + Arrays.deepToString(end) + "')");
                                break;
                        }


                    }
                case 4:
                    helper.to_excel(tablename, "laba9.xlsx");
            }
        }
    }
}



