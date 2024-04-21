import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba11 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename1 = "laba11_tria";
        String tablename2 = "laba11_fact";
        String sql1 = "CREATE TABLE IF NOT EXISTS " + tablename1 + " (" +
                "`id` int AUTO_INCREMENT primary key," +
                "side_a INT," +
                "side_b INT," +
                "side_c INT," +
                "perimeter INT," +
                "square FLOAT" +
                ");";
        String sql2 = "CREATE TABLE IF NOT EXISTS " + tablename2 + " (" +
                "`id` int AUTO_INCREMENT primary key," +
                "number INT," +
                "factorial_even BIGINT," +
                "factorial_odd BIGINT" +
                ");";
        helper.execute_Update(sql1);
        helper.execute_Update(sql2);
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1. Вывести все таблицы из базы данных MySQL.");
            System.out.println("2. Создать таблицу в базе данных MySQL.");
            System.out.println("3. Решение базового варианта, сохранение результатов в MySQL.");
            System.out.println("4. Вывод данных с условием: вывести данные по ID строки.");
            System.out.println("5. Сохранить итоговые результаты из MySQL в Excel и вывести их в консоль.");
            System.out.println("6. Выход.");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");

            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }

            int input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input > 6) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 6) {
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
                    System.out.println("Выберите подзадачу: 1 - треугольник, 2 - факториал");
                    int choice = in.nextInt();
                    if (choice == 1) {
                        System.out.println("Выберите тип треугольника: 1 - обычный, 2 - прямоугольный");
                        choice = in.nextInt();
                        Triangle triangle;
                        if (choice == 1) {
                            triangle = new Triangle();
                        } else if (choice == 2) {
                            triangle = new RightTriangle();
                        } else {
                            System.out.println("Неверный выбор");
                            break;
                        }
                        triangle.SetTriangle();
                        System.out.println("Периметр треугольника: " + triangle.getPerimeter());
                        System.out.println("Площадь треугольника: " + triangle.getSquare());
                        helper.execute_Update( "INSERT INTO " + tablename1 +
                                " (side_a, side_b, side_c, perimeter, square) VALUES (" +
                                triangle.getSide_a() +
                                ", " + triangle.getSide_b() +
                                ", " + triangle.getSide_c() +
                                ", " + triangle.getPerimeter() +
                                ", " + triangle.getSquare() +
                                ")");
                        break;
                    } else if (choice == 2) {
                        Factorial factorial = new Factorial();
                        helper.execute_Update("INSERT INTO " + tablename2 +
                                " (number, factorial_even, factorial_odd) VALUES (" +
                                factorial.getNum() +
                                ", " + factorial.getEvenFactorial() +
                                ", " + factorial.getOddFactorial() +
                                ")");
                        break;

                    } else {
                        System.out.println("Неверный выбор");
                        break;
                    }
                case 4:
                    System.out.println("Выберите таблицу из которой будем выводить данные: 1 - треугольник, 2 - факториал");

                    int choice2 = in.nextInt();
                    if (choice2 == 1) {
                        System.out.print("Введите id для вывода информации: ");
                        int id = in.nextInt();
                        helper.rs_to_console("SELECT * FROM " + tablename1 + " WHERE id = " + id);
                        break;
                    } else if (choice2 == 2) {
                        System.out.print("Введите id для вывода информации: ");
                        int id = in.nextInt();
                        helper.rs_to_console("SELECT * FROM " + tablename2 + " WHERE id = " + id);
                        break;
                    } else {
                        System.out.println("Неверный выбор");
                        break;
                    }
                case 5:
                    System.out.println("Для треугольника:");
                    helper.to_excel(tablename1,"laba10_tria.xlsx");
                    System.out.println("Для факториала:");
                    helper.to_excel(tablename2, "laba10_fact.xlsx");

            }
        }
    }
}
