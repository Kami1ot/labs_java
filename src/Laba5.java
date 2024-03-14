import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba5 {


    public static void main(String[] args) throws SQLException, IOException {

        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba5";



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
            if (input > 4) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 4) {
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
                    System.out.println("Введите две строки далее");
                    System.out.println("--------------------------------------------------");
                    in.nextLine();
                    StringBuffer first_str = new StringBuffer(in.nextLine());
                    StringBuffer second_str = new StringBuffer(in.nextLine());
                    System.out.println("--------------------------------------------------");
                    helper.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + first_str + "')");
                    helper.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + second_str + "')");

                    while(true) {
                        System.out.println(first_str);
                        System.out.println(second_str);
                        System.out.println("--------------------------------------------------");
                        System.out.println("1. Изменить порядок символов строки на обратный и сохранить в MySQL");
                        System.out.println("2. Добавить одну строку в другую и сохранить в MySQL");
                        System.out.println("3. Excel");
                        System.out.println("4. Назад");
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
                        if (input == 4) {
                            break;
                        }

                        switch (input) {
                            case 1:
                                StringBuffer reversed_str_1 = new StringBuffer(first_str).reverse();
                                StringBuffer reversed_str_2 = new StringBuffer(second_str).reverse();
                                System.out.println("Обратный порядок символов: " + reversed_str_1);
                                System.out.println("Обратный порядок символов: " + reversed_str_2);
                                System.out.println("--------------------------------------------------");

                                helper.execute_Update("UPDATE " + tablename + " SET string_reverse = '" + reversed_str_1 + "' WHERE string = '" + first_str + "';");
                                helper.execute_Update("UPDATE " + tablename + " SET string_reverse = '" + reversed_str_2 + "' WHERE string = '" + second_str + "';");

                                break;
                            case 2:
                                StringBuffer merged_str = new StringBuffer(first_str).append(second_str);
                                System.out.println("Объединенная строка: " + merged_str);
                                System.out.println("--------------------------------------------------");
                                helper.execute_Update("UPDATE " + tablename + " SET string_merge = '" + merged_str + "' WHERE string = '" + first_str + "';");
                                helper.execute_Update("UPDATE " + tablename + " SET string_merge = '" + merged_str + "' WHERE string = '" + second_str + "';");
                                break;

                            case 3:
                                helper.to_excel(tablename, "output.xlsx");
                                break;


                        }
                    }
            }
        }
    }
}



