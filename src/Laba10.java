import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Laba10 {
    public static void main(String[] args) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba10";
        String sql = "CREATE TABLE IF NOT EXISTS " + tablename + " (" +
                "id INT UNIQUE," +
                "major VARCHAR(255)," +
                "full_name VARCHAR(255)," +
                "stud_group VARCHAR(255)," +
                "practice_place VARCHAR(255)," +
                "thesis VARCHAR(255)" +
                ");";
        helper.execute_Update(sql);
        while (true) {
            System.out.println("--------------------------------------------------");
            System.out.println("1. Вывести все таблицы из базы данных MySQL.");
            System.out.println("2. Создать таблицу в базе данных MySQL.");
            System.out.println("3. Ввести данные о студенте и сохранить их в MySQL.");
            System.out.println("4. Вывести данные о студенте по ID из MySQL.");
            System.out.println("5. Удалить данные о студенте из MySQL по ID.");
            System.out.println("6. Сохранить итоговые результаты из MySQL в Excel и вывести их в консоль.");
            System.out.println("7. Выход.");
            System.out.println("--------------------------------------------------");
            System.out.print("Введите операцию: ");

            while (!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }

            int input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input > 7) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 7) {
                break;
            }
            int id;
            switch (input) {
                case 1:
                    helper.show_table();
                    break;
                case 2:
                    helper.create_table();
                    break;
                case 3:
                    String major;
                    String fullName;
                    String practise;
                    String group;
                    String thesis;
                    System.out.println("У студента есть практика? (Д/Н)");
                    in.nextLine();
                    String ans = in.nextLine();
                    if (ans.equalsIgnoreCase("Д")){
                        UndergraduateStudent undergraduateStudent = new UndergraduateStudent();
                        id = undergraduateStudent.getId();
                        fullName = undergraduateStudent.getFullName();
                        practise = undergraduateStudent.getPractise();
                        major = undergraduateStudent.getMajor();
                        group = undergraduateStudent.getGroup();
                        System.out.println("--------------------------------------------------");
                        undergraduateStudent.printStudentInfo();
                        String query = "INSERT INTO " + tablename + " (id, major, full_name, stud_group, practice_place) VALUES (" + id +
                                ", '" + major +
                                "', '" + fullName +
                                "', '" + group +
                                "', '" + practise +
                                "')";
                        helper.execute_Update(query);
                        continue;


                    }
                    System.out.println("Студент пишет дипломную работу? (Д/Н)");
                    ans = in.nextLine();
                    if (ans.equalsIgnoreCase("Д")) {
                        GraduateStudent graduateStudent = new GraduateStudent();
                        id = graduateStudent.getId();
                        fullName = graduateStudent.getFullName();
                        thesis = graduateStudent.getThesis();
                        major = graduateStudent.getMajor();
                        group = graduateStudent.getGroup();
                        System.out.println("--------------------------------------------------");
                        graduateStudent.printStudentInfo();
                        String query = "INSERT INTO " + tablename + " (id, major, full_name, stud_group, thesis) VALUES (" + id +
                                ", '" + major +
                                "', '" + fullName +
                                "', '" + group +
                                "', '" + thesis +
                                "')";
                        helper.execute_Update(query);
                        continue;
                    }else{
                        Student10 student10 = new Student10();
                        id = student10.getId();
                        fullName = student10.getFullName();
                        major = student10.getMajor();
                        group = student10.getGroup();
                        student10.printStudentInfo();
                        String query = "INSERT INTO " + tablename + " (id, major, full_name, stud_group) VALUES (" + id +
                                ", '" + major +
                                "', '" + fullName +
                                "', '" + group +
                                "')";
                        helper.execute_Update(query);
                    }
                    break;
                case 4:
                    System.out.print("Введите id студента для вывода информации о нем: ");
                    id = in.nextInt();
                    helper.rs_to_console("SELECT * FROM " + tablename + " WHERE id = " + id);
                    break;
                case 5:
                    System.out.print("Введите id студента для удаления информации о нем: ");
                    id = in.nextInt();
                    helper.execute_Update("DELETE FROM " + tablename + " WHERE id = " + id);
                    break;
                case 6:
                    helper.to_excel(tablename,"laba10.xlsx");
                    break;
            }
        }
    }
}
