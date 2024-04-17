import java.util.Scanner;

public class Student10 {
    final int id;
    final String major;
    final String fullName;
    final String group;

    public Student10() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введите ID студента: ");
        id = in.nextInt();
        in.nextLine();
        System.out.print("Введите направление подготовки студента: ");
        major = in.nextLine();
        System.out.print("Введите ФИО студента: ");
        fullName = in.nextLine();
        System.out.print("Введите группу студента: ");
        group = in.nextLine();
        System.out.println();

    }

    public int getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    public String getFullName() {
        return fullName;
    }

    public String getGroup() {
        return group;
    }

    public void printStudentInfo() {
        System.out.println("ID: " + id);
        System.out.println("Направление подготовки: " + major);
        System.out.println("ФИО: " + fullName);
        System.out.println("Группа: " + group);
        System.out.println();
    }
}
