import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba2 {


    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "strings";

        while(true) {

            System.out.println("--------------------------------------------------");
            System.out.println("1 - вывод таблиц");
            System.out.println("2 - создание таблицы");
            System.out.println("3 - Ввод двух строк и операции с ними");
            System.out.println("4 - выход");
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
            if (input == 4) {
                return;
            }

                switch (input) {
                    case 1:
                        helper.show_table();
                        break;
                    case 2:
                        helper.create_table();
                        break;
                    case 3:
                        System.out.println("Введите две строки далее");
                        System.out.println("--------------------------------------------------");
                        in.nextLine();
                        String first_str = in.nextLine();
                        String second_str = in.nextLine();
                        System.out.println("--------------------------------------------------");
                        helper.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + first_str + "')");
                        helper.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + second_str + "')");

                        while(true) {
                            System.out.println(first_str);
                            System.out.println(second_str);
                            System.out.println("--------------------------------------------------");
                            System.out.println("1 - Подсчитать размер");
                            System.out.println("2 - Объединить две строки в единое целое");
                            System.out.println("3 - Сравнить две ранее введенные строки");
                            System.out.println("4 - Сохранить все данные в Excel");
                            System.out.println("5 - Назад");
                            System.out.println("--------------------------------------------------");

                            while(!in.hasNextByte()) {
                                System.out.print("Ошибка! Введите корректное число: ");
                                in.next();
                            }

                            input = in.nextInt();
                            System.out.println("--------------------------------------------------");
                            if (input > 5) {
                                System.out.println("Неверный ввод числа!");
                            }
                            if (input == 5) {
                                break;
                            }

                                switch (input) {
                                    case 1:
                                        System.out.println("Размер первой строки: " + first_str.length());
                                        System.out.println("Размер второй строки: " + second_str.length());
                                        System.out.println("--------------------------------------------------");
                                        helper.execute_Update("UPDATE " + tablename + " SET lenth = " + first_str.length() + " WHERE string = '" + first_str + "';");
                                        helper.execute_Update("UPDATE " + tablename + " SET lenth = " + second_str.length() + " WHERE string = '" + second_str + "';");
                                        break;
                                    case 2:
                                        System.out.println("Объединенная строка: " + first_str + " " + second_str);
                                        System.out.println("--------------------------------------------------");
                                        helper.execute_Update("UPDATE " + tablename + " SET together = '" + first_str + " " + second_str + "' WHERE string = '" + first_str + "';");
                                        helper.execute_Update("UPDATE " + tablename + " SET together = '" + second_str + " " + second_str + "' WHERE string = '" + second_str + "';");
                                        break;
                                    case 3:
                                        String difference = "";
                                        if (first_str.equals(second_str)) {
                                            System.out.println("Строки одинаковы");
                                            System.out.println("--------------------------------------------------");
                                            difference = difference + " Строки одинаковы";
                                        } else {
                                            System.out.println("Строки различны");
                                            System.out.println("--------------------------------------------------");
                                            difference = difference + " Строки различны";
                                        }

                                        int result = first_str.compareTo(second_str);
                                        if (result == 0) {
                                            System.out.println("Строки одинаковы по длине");
                                            System.out.println("--------------------------------------------------");
                                            difference = difference + " Строки одинаковы по длине";
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = '" + difference + "' WHERE string = '" + first_str + "';");
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = '" + difference + "' WHERE string = '" + second_str + "';");
                                        } else if (result < 0) {
                                            System.out.println("Строка 1 меньше строки 2");
                                            System.out.println("--------------------------------------------------");
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = 'Эта строка меньше строки ниже" + difference + "' WHERE string = '" + first_str + "';");
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = 'Эта строка больше строки выше" + difference + "' WHERE string = '" + second_str + "';");
                                        } else {
                                            System.out.println("Строка 1 больше строки 2");
                                            System.out.println("--------------------------------------------------");
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = 'Эта строка больше строки ниже" + difference + "' WHERE string = '" + first_str + "';");
                                            helper.execute_Update("UPDATE " + tablename + " set sravnenie = 'Эта строка меньше строки выше" + difference + "' WHERE string = '" + second_str + "';");
                                        }
                                        break;
                                    case 4:
                                        helper.to_excel(tablename, "output.xlsx");
                                        break;
                                }
                        }
                }
        }
    }
}



