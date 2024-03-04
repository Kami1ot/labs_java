import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        Helper help = new Helper();
        help.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "oper";
        int input;
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1 - вывод таблиц");
            System.out.println("2 - создание таблицы");
            System.out.println("3 - Excel");
            System.out.println("4 - Операции");
            System.out.println("5 - выход");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");
            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }
            input = in.nextInt();
            if (input == 5) {

                break;
            }
            if (input > 5) {
                System.out.println("Неверный ввод числа!");
                continue;
            }
            switch (input) {
                case (1):
                    help.show_table();
                    break;
                case (2):
                    help.create_table();
                    break;
                case (3):
                    help.to_excel("oper", "output");
                    break;
                case (4):
                    //            вычисление
                    while (true) {
                        System.out.println("--------------------------------------------------");
                        System.out.println("1. Сложение");
                        System.out.println("2. Вычитание");
                        System.out.println("3. Умножение");
                        System.out.println("4. Деление");
                        System.out.println("5. Деление чисел по модулю");
                        System.out.println("6. Возведение числа в степень");
                        System.out.println("7. Возведение числа в модуль");
                        System.out.println("8. Выйти");
                        System.out.println("--------------------------------------------------");
                        System.out.print("Введите операцию: ");
                        while (!in.hasNextInt()) {
                            System.out.print("Ошибка! Введите корректное число: ");
                            in.next();
                        }
                        input = in.nextInt();
                        if (input > 8) {
                            System.out.println("Неверный ввод числа!");
                            continue;
                        }
                        if (input == 8) break;
                        float input1, input2;
                        float end;
                        System.out.print("Введите число: ");
                        while (!in.hasNextInt()) {
                            System.out.print("Ошибка! Введите корректное число: ");
                            in.next();
                        }
                        input1 = (float) in.nextInt();
                        if (input == 7) {
                            end = (Math.abs(input1));

                            System.out.println("Ответ равен: " + end);
                            in.nextLine();
                            help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", 'abs', " + null + ", " + end + ")");

                        } else {
                            System.out.print("Введите второе число: ");
                            while (!in.hasNextInt()) {
                                System.out.print("Ошибка! Введите корректное число: ");
                                in.next();
                            }
                            input2 = (float) in.nextInt();
                            switch (input) {
                                case (1):
                                    end = input1 + input2;
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + (end));

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", '+', " + input2 + ", " + end + ")");



                                    break;
                                case (2):
                                    end = input1 - input2;
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + (end));

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", '-', " + input2 + ", " + end + ")");

                                    break;
                                case (3):
                                    end = input1 * input2;
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + end);

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", '*', " + input2 + ", " + end + ")");

                                    break;
                                case (4):
                                    end = input1 / input2;
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + end);

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", '/', " + input2 + ", " + end + ")");

                                    break;
                                case (5):
                                    end = input1 % input2;
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + end);

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", '%', " + input2 + ", " + end + ")");

                                    break;
                                case (6):
                                    end = (float) Math.pow(input1, input2);
                                    System.out.println("--------------------------------------------------");
                                    System.out.println("Ответ равен: " + end);

                                    in.nextLine();
                                    help.execute_Update("INSERT INTO " + tablename + " (num1, operation, num2, endd) VALUES (" + input1 + ", 'pow', " + input2 + ", " + end + ")");

                                    break;
                            }
                        }
                    }
                    break;
                }
            }
        }
    }




