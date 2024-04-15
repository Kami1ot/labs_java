import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class testing {
    public static void main(String args[]) throws SQLException, IOException {
        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        System.out.println("Введите длину массива:");
        int size = in.nextInt();
        Sort Arr = new Sort();
        Arr.createarray(size);
        for (int i = 0; i < size; i++) {
            System.out.print(Arr.Array[i]+ " ");
        }
        System.out.println(Arr);
    }
}
