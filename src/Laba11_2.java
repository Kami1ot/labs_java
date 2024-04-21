import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba11_2 {

    public static void main (String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba11_2";
        int input;
        while (true){
            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Загрузить все данные о гидростанциях (долго)");
            System.out.println("4 - Вывод по коду поста");
            System.out.println("5 - Вывод по коду поста и дате");
            System.out.println("6 - Восстановить все пропущенные данные");
            System.out.println("7 - Выход");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");
            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }
            input = in.nextInt();
            if (input == 7) {
                break;
            }
            if (input > 7 | input < 1) {
                System.out.println("Неверный ввод числа!");
                continue;
            }
            switch (input){
                case (1):
                    helper.show_table();
                    break;
                case (2):
                    helper.create_table();
                    break;
                case (3):
                    helper.importExcelToDatabase("Urovni2_1_1_new_1_2.xlsx",tablename);
                    break;
                case (4):
                    System.out.print("Введите код: ");
                    in.nextLine();
                    String code = in.nextLine();
                    helper.rs_to_console("SELECT * FROM " + tablename + " WHERE `Код поста` = " + code);
                    break;
                case (5):
                    System.out.print("Введите код: ");
                    in.nextLine();
                    String code1 = in.nextLine();
                    System.out.print("Введите дату (формат: yyyy-mm-dd): ");
                    String date = in.nextLine();
                    String query = "SELECT * FROM " + tablename + " WHERE `Код поста` = " + code1 + " AND `Дата - время` = '" + date + "'";
                    helper.rs_to_console(query);

                    break;


            }
        }
    }
}

