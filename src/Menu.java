import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Menu {
    private Helper helper;
    private SortedStudents students;

    public Menu(Helper helper) {
        this.helper = helper;
        this.students = new SortedStudents();
    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Output all tables from MySQL database");
        System.out.println("2. Create a table in MySQL database");
        System.out.println("3. Enter data about all students and save the list in MySQL");
        System.out.println("4. Output data about the student by ID from MySQL");
        System.out.println("5. Delete student data from MySQL by ID");
        System.out.println("6. Save the final results from MySQL to Excel and output them to the console");
        System.out.print("Enter your choice: ");
    }

    public void executeMenuOption(int choice) {
        switch (choice) {
            case 1:
                try {
                    helper.show_table();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 2:
                try {
                    helper.create_table();
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 3:
                students.addStudent();
                try {
                    helper.execute_Update("INSERT INTO students (id, field_of_study, firstMame, lastName, group) VALUES " + students.getStudentsAsInsertQuery());
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 4:
                Scanner scanner = new Scanner(System.in);
                System.out.print("Enter student ID: ");
                int id = scanner.nextInt();
                try {
                    helper.rs_to_console("SELECT * FROM students WHERE id = " + id);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 5:
                scanner = new Scanner(System.in);
                System.out.print("Enter student ID: ");
                id = scanner.nextInt();
                try {
                    helper.execute_Update("DELETE FROM students WHERE id = " + id);
                } catch (SQLException e) {
                    System.out.println(e);
                }
                break;
            case 6:
                try {
                    helper.to_excel("students", "students.xlsx");
                } catch (IOException | SQLException e) {
                    System.out.println(e);
                }
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}