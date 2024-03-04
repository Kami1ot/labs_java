import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Laba4 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper laba4 = new Helper();
        laba4.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba4";
        int input;
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1 - вывод таблиц");
            System.out.println("2 - создание таблицы");
            System.out.println("3 - работа со строкой");
            System.out.println("4 - exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");
            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }
            input = in.nextInt();
            if (input == 4) {
                break;
            }
            if (input > 4) {
                System.out.println("Неверный ввод числа!");
                continue;
            }
            switch (input){
                case(1):
                    laba4.show_table();
                    break;
                case(2):
                    laba4.create_table();
                    break;
                case(3):
                    System.out.println("Введите строки");
                    System.out.print("Первая строка: ");
                    in.nextLine();
                    String inp1 = in.nextLine();
                    System.out.print("Вторая строка: ");
                    String inp2 = in.nextLine();
                    laba4.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + inp1 + "');");
                    laba4.execute_Update("INSERT INTO " + tablename + " (string) VALUES ('" + inp2 + "');");
                    System.out.println("--------------------------------------------------");
                    while (true){
                        System.out.println(inp1);
                        System.out.println(inp2);
                        System.out.println("--------------------------------------------------");
                        System.out.println("1 - Возвращение подстроки по индексам");
                        System.out.println("2 - Перевод строк в верхний и нижний регистры");
                        System.out.println("3 - Поиск подстроки и определение окончания подстроки");
                        System.out.println("4 - excel");
                        System.out.println("5 - exit");
                        System.out.println("--------------------------------------------------");
                        System.out.print("Bведите операцию: ");
                        while (!in.hasNextInt()) {
                            System.out.print("Ошибка! Введите корректное число: ");
                            in.next();
                        }
                        input = in.nextInt();
                        System.out.print("");
                        if (input == 5) {
                            break;
                        }
                        if (input > 5) {
                            System.out.println("Неверный ввод числа!");
                            continue;
                        }

                        switch (input){
                            case (1):
                                System.out.println("Введите индексы первой строки");
                                System.out.print("От: ");
                                while (!in.hasNextInt()) {
                                    System.out.print("Ошибка! Введите корректное число: ");
                                    in.next();
                                }
                                int index1 = in.nextInt();
                                System.out.print("До: ");
                                while (!in.hasNextInt()) {
                                    System.out.print("Ошибка! Введите корректное число: ");
                                    in.next();
                                }
                                int index2 = in.nextInt();
                                System.out.println("Введите индексы второй строки");
                                System.out.print("От: ");
                                while (!in.hasNextInt()) {
                                    System.out.print("Ошибка! Введите корректное число: ");
                                    in.next();
                                }
                                int index3 = in.nextInt();
                                System.out.print("До: ");
                                while (!in.hasNextInt()) {
                                    System.out.print("Ошибка! Введите корректное число: ");
                                    in.next();
                                }
                                int index4 = in.nextInt();
                                try{
                                    System.out.println(inp1.substring(index1,index2));
                                    System.out.println(inp2.substring(index3,index4));
                                    System.out.println("--------------------------------------------------");
                                    in.nextLine();
                                    laba4.execute_Update("UPDATE " + tablename + " SET slice = '" + inp1.substring(index1,index2) + "' WHERE string = '" + inp1+"';");
                                    laba4.execute_Update("UPDATE " + tablename + " SET slice = '" + inp2.substring(index3,index4) + "' WHERE string = '" + inp2+"';");
                                }catch (StringIndexOutOfBoundsException e){
                                    System.out.println("ERROR : " + e.getMessage());
                                    System.out.println("--------------------------------------------------");
                                }
                                break;
                            case (2):
                                System.out.println("Первая строка в верхнем регистре " + inp1.toUpperCase());
                                System.out.println("Вторая строка в верхнем регистре " + inp2.toUpperCase());

                                System.out.println("--------------------------------------------------");
                                System.out.println("Первая строка в нижнем регистре " + inp1.toLowerCase());
                                System.out.println("Вторая строка в нижнем регистре " + inp2.toLowerCase());
                                System.out.println("--------------------------------------------------");
                                laba4.execute_Update("UPDATE " + tablename + " SET register = '" + inp1.toUpperCase() + " " + inp1.toLowerCase() + "' WHERE string = '" + inp1+"';");
                                laba4.execute_Update("UPDATE " + tablename + " SET register = '" + inp2.toUpperCase() + " " + inp2.toLowerCase() + "' WHERE string = '" + inp2+"';");
                                break;
                            case (3):
                                System.out.print("Введите первую подстроку: ");
                                in.nextLine();
                                String underString = in.nextLine();
                                int index_a = inp1.indexOf(underString);
                                if (index_a == -1){
                                    String message = "Данной подстроки в вашей строке нет " + ", Заканчивается ли строка данной подстрокой: " + inp1.endsWith(underString);
                                    System.out.println(message);
                                    laba4.execute_Update("UPDATE " + tablename + " SET search = '" + message + "' WHERE string = '" + inp1+"';");
                                    System.out.print("");
                                } else {
                                    String message = "Подстрока начинается с индекса: " + index_a + ", Заканчивается ли строка данной подстрокой: " + inp1.endsWith(underString);
                                    System.out.println(message);
                                    laba4.execute_Update("UPDATE " + tablename + " SET search = '" + message + "' WHERE string = '" + inp1+"';");
                                }

                                System.out.print("Введите вторую подстроку: ");
                                underString = in.nextLine();
                                int index_b = inp2.indexOf(underString);
                                if (index_b == -1) {
                                    System.out.print("");
                                    String message = "Данной подстроки в вашей строке нет " + ", Заканчивается ли строка данной подстрокой: " + inp2.endsWith(underString);
                                    System.out.println(message);
                                    laba4.execute_Update("UPDATE " + tablename + " SET search = '" + message + "' WHERE string = '" + inp2+"';");
                                } else {
                                    String message = "Подстрока начинается с индекса: " + index_b + ", Заканчивается ли строка данной подстрокой: " + inp2.endsWith(underString);
                                    System.out.println(message);
                                    laba4.execute_Update("UPDATE " + tablename + " SET search = '" + message + "' WHERE string = '" + inp2+"';");
                                }
                                System.out.println("--------------------------------------------------");
                                break;

                            case (4):
                                laba4.to_excel("laba4", "laba4.xlsx");
                                break;


                        }


                    }


            }




        }
    }

}
