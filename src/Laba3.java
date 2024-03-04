import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class Laba3 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper lala = new Helper();
        lala.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba3";
        int input;
        while (true){
            System.out.println("--------------------------------------------------");
            System.out.println("1 - вывод таблиц");
            System.out.println("2 - создание таблицы");
            System.out.println("3 - ввод чисел и проверка их на целостность");
            System.out.println("4 - Excel");
            System.out.println("5 - exit");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");
            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }
            input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input == 5) {
                break;
            }
            if (input > 5) {
                System.out.println("Неверный ввод числа!");
                continue;
            }
            switch (input){
                case (1):
                    lala.show_table();
                    break;
                case (2):
                    lala.create_table();
                    break;
                case (3):
                    System.out.println("Вводите числа, введите stop для оставновки");
                    in.nextLine();
                    int inp2;
                    while (true){
                        String check_val = in.nextLine();
                        if (check_val.equals("stop")) break;

                        try {
                            inp2 = Integer.parseInt(check_val);
                            System.out.println("Целочисленный формат ввода");
                            if (inp2 % 2 == 0){
                                System.out.println("Четное число");
                                lala.execute_Update("INSERT INTO " + tablename + " (num,type) VALUES ('" + inp2 + "','Целочисленный формат ввода четное число')");
                            }
                            else {
                                System.out.println("Нечетное число");
                                lala.execute_Update("INSERT INTO " + tablename + " (num,type) VALUES ('" + inp2 + "','Целочисленный формат ввода нечетное число')");
                            }
                        }catch (NumberFormatException e){
                            System.out.println("Это не целочисленный формат ввода");
                            lala.execute_Update("INSERT INTO " + tablename + " (num,type) VALUES ('" + check_val + "','Не целочисленный формат ввода')");

                        }
                    }
                    break;
                case(4):
                    lala.to_excel("laba3","out_test.xlsx");
                    break;

            }





        }



//        lala1.show_table();


    }
}
