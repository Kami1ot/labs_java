import java.util.Comparator;
import java.util.List;

public class SortedStudents extends Students {
    public void sortStudents() {
        List<Student11> students = getStudents();
        students.sort(new Comparator<Student11>() {
            @Override
            public int compare(Student11 s1, Student11 s2) {
                return s1.getFullName().compareTo(s2.getFullName());
            }
        });
    }

    public void printStudents() {
        List<Student11> students = getStudents();
        for (Student11 student : students) {
            System.out.println(student);
        }
    }
}
