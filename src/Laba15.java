import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Set;

public class Laba15 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba15";
        String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" +
                "`id` int AUTO_INCREMENT PRIMARY KEY," +
                "list varchar(1000)," +
                "strlist varchar(1000)," +
                "setlist varchar(1000)" +
                ");";
        helper.execute_Update(sql);
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1. Вывести все таблицы из базы данных MySQL.");
            System.out.println("2. Создать таблицу в базе данных MySQL.");
            System.out.println("3. Ввести список и сохранить в MySQL.");
            System.out.println("4. Удалить элемент из списка в MySQL по ID.");
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

            switch(input){
                case 1:
                    helper.show_table();
                    break;
                case 2:
                    helper.create_table();
                    break;
                case 3:
                    Listik1 listik1 = new Listik1();
                    String ans = listik1.inputList(50);
                    listik1.converter();
                    Set<Integer> setList = listik1.getSetList();
                    String strList = listik1.getStrList();

                    System.out.println("Список:" + ans);
                    System.out.println("Строка:" + strList);
                    System.out.println("Множество:" + setList);

                    helper.execute_Update( "INSERT INTO " + tablename +
                            " (list, strlist, setlist) VALUES ('" +
                            ans +
                            "', '" + strList +
                            "', '" + setList +
                            "')");
                    break;
                case 4:
                    System.out.print("Введите id списка, из которого нужно удалить элемент: ");
                    int id = in.nextInt();
                    helper.deleteElementById(id,tablename);

                    break;
                case 5:
                    helper.to_excel(tablename, "laba15.xlsx");

            }

        }
    }
}

