import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class testing {
    public static void main(String args[]) throws SQLException, IOException {

        List<Integer> list = new ArrayList<>();
//        String ans = listik.inputList();
//        System.out.println(ans);
        Random random = new Random();
        for (int i = 0; i<10; i++) {
            list.add(i);

        }
        System.out.println(list.contains(4));

    }
}
