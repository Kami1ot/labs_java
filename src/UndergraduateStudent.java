import java.util.Scanner;

class UndergraduateStudent extends Student10 {
    final String practise;

    public UndergraduateStudent() {
        super();
        Scanner in = new Scanner(System.in);
        System.out.print("Введите место практики: ");
        practise = in.nextLine();
    }

    public String getPractise() {
        return practise;
    }

    @Override
    public void printStudentInfo() {
        super.printStudentInfo();
        System.out.println("Место практики: " + practise);
    }
}
