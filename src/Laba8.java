import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba8 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba8";
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Ввод данных о студенте");
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
                    Worker student = new Worker();
                    System.out.print("Введите имя студента: ");
                    in.nextLine();
                    student.setName(in.nextLine());
                    System.out.println("--------------------------------------------------");
                    System.out.print("Введите возраст студента: ");
                    while (!in.hasNextInt()) {
                        System.out.print("Ошибка! Введите корректное число: ");
                        in.next();
                    }

                    student.setAge(in.nextInt());
                    System.out.println("--------------------------------------------------");
                    System.out.print("Введите размер зарплаты: ");
                    while (!in.hasNextInt()) {
                        System.out.print("Ошибка! Введите корректное число: ");
                        in.next();
                    }

                    student.setSalary(in.nextInt());
                    System.out.println("--------------------------------------------------");
                    System.out.println("Имя студента: " + student.getName() + "\nВозраст: " + student.getAge() + "\nЗарплата:" + student.getSalary());
                    helper.execute_Update("INSERT INTO " + tablename + " (stud_name, stud_age, salary) VALUES ('" + student.getName() + "'," + student.getAge() + "," + student.getSalary() + ")");
                    break;
                case 4:
                    helper.to_excel(tablename,"output.xlsx");
                    break;
            }
        }
    }
}
