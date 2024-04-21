import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Laba14 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba14";
        String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" +
                "`id` int AUTO_INCREMENT PRIMARY KEY," +
                "`value` int," +
                "list varchar(10000)" +
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
                    Listik listik = new Listik();
                    System.out.print("Введите начало диапазона: ");
                    int a = in.nextInt();
                    System.out.print("Введите конец диапазона: ");
                    int b = in.nextInt();
                    System.out.print("Введите длину списка: ");
                    int length = in.nextInt();
                    String ans = listik.inputList(a, b, length) + ":" + listik.getRandomList();
                    List<Integer> check = listik.getInputList();
                    System.out.print("Введите элемент для проверки на принадлежность: ");
                    int el = in.nextInt();
                    System.out.println(ans);
                    if (check.contains(el)){
                        System.out.println("Элемент есть в списке");
                    }else{
                        System.out.println("Элемента нет в списке");
                    }
                    helper.execute_Update("INSERT INTO " + tablename +
                            " (list, value) values ('" + ans +
                            "', " + el + ");");
                    break;
                case 4:
                    System.out.print("Введите id списка, из которого нужно удалить элемент: ");
                    int id = in.nextInt();
                    String sqle = "SELECT list FROM " + tablename + " WHERE id = " + id;

                    ResultSet rs = helper.stmt.executeQuery(sqle);
                    String listString = "";
                    if (rs.next()) {
                        listString = rs.getString("list");
                    }
                    System.out.println(listString);
                    System.out.print("Введите элемент, который нужно удалить: ");
                    int elm = in.nextInt();

                    String result = helper.removeElementFromArrays(listString,elm);
                    sql = "UPDATE " + tablename + " SET list = '" + result + "' WHERE id = " + id;
                    helper.execute_Update(sql);
                    break;

                case 5:
                    helper.to_excel(tablename, "laba14.xlsx");

            }

        }
    }
}
