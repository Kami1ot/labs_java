import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Students {
    private List<Student11> students;

    public Students() {
        this.students = new ArrayList<>();
    }

    public void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of students: ");
        int numStudents = scanner.nextInt();
        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter student ID: ");
            int id = scanner.nextInt();
            System.out.print("Enter student's field of study: ");
            String fieldOfStudy = scanner.next();
            System.out.print("Enter student's first name: ");
            String firstName= scanner.next();
            System.out.print("Enter student's last name: ");
            String lastName= scanner.next();
            System.out.print("Enter student's group: ");
            String group = scanner.next();
            Student11 student = new Student11(id, fieldOfStudy, firstName, lastName, group);
            students.add(student);
        }
    }

    public List<Student11> getStudents() {
        return students;
    }
    public String getStudentsAsInsertQuery() {
        StringBuilder query = new StringBuilder();
        for (Student11 student : students) {
            query.append("(");
            query.append(student.getId());
            query.append(", '");
            query.append(student.getFieldOfStudy());
            query.append("', '");
            query.append(student.getFirstName());
            query.append("', '");
            query.append(student.getLastName());
            query.append("', '");
            query.append(student.getGroup());
            query.append("'),");
        }
        query.deleteCharAt(query.length() - 1); // remove the last comma
        return query.toString();
    }
}

class Student11 {
    private int id;
    private String fieldOfStudy;
    private String firstName;
    private String lastName;
    private String group;

    public Student11(int id, String fieldOfStudy, String firstName, String lastName, String group) {
        this.id = id;
        this.fieldOfStudy = fieldOfStudy;
        this.firstName = firstName;
        this.lastName = lastName;
        this.group = group;
    }

    public int getId() {
        return id;
    }

    public String getFieldOfStudy() {
        return fieldOfStudy;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", fieldOfStudy='" + fieldOfStudy + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", group='" + group + '\'' +
                '}';
    }
}

