import java.util.Scanner;

class GraduateStudent extends Student10 {
    final String thesis;

    public GraduateStudent() {
        super();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите тему дипломной работы: ");
        thesis = in.nextLine();
    }

    public String getThesis() {
        return thesis;
    }

    @Override
    public void printStudentInfo() {
        super.printStudentInfo();
        System.out.println("Тема дипломной работы: " + thesis);
    }
}