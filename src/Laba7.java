import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class Laba7 {
    public static void main(String[] args) throws SQLException, IOException {

        Helper helper = new Helper();
        helper.Connection("jdbc:mysql://localhost/test", "root", "ensoxPER324");
        Scanner in = new Scanner(System.in);
        String tablename = "laba7";

        while(true) {

            System.out.println("--------------------------------------------------");
            System.out.println("1 - Вывод таблиц");
            System.out.println("2 - Создание таблицы");
            System.out.println("3 - Ввести одномерный массив");
            System.out.println("4 - Сохранить в Exel");
            System.out.println("5 - Выход");
            System.out.println("--------------------------------------------------");
            System.out.print("");
            System.out.print("Введите операцию: ");

            while(!in.hasNextInt()) {
                System.out.print("Ошибка! Введите корректное число: ");
                in.next();
            }

            int input = in.nextInt();
            System.out.println("--------------------------------------------------");
            if (input > 5) {
                System.out.println("Неверный ввод числа!");
            }
            if (input == 5) {
                break;
            }

            switch (input) {
                case 1:
                    helper.show_table();
                    break;
                case 2:
                    helper.create_table();
                    break;
                case 3:
                    System.out.println("Введите длину массива:");
                    int size = in.nextInt();
                    Sort Arr = new Sort();
                    Arr.createarray(size);
                    for (int i = 0; i < size; i++) {
                        System.out.print(Arr.Array[i]+ " ");
                    }
                    System.out.println();
                    int[] dupl = Arr.Array.clone();
                    System.out.println("Вы хотите отсортировать массив?(1-Да)/(2-Нет)");
                    int input1 = in.nextInt();
                    switch (input1){
                        case 1:
                            System.out.println("По возрастанию(1) или убыванию(2)?");
                            int input2 = in.nextInt();
                            switch (input2){
                                case 1:

                                    Arr.bubble_sort_up();
                                    int[] sorted = Arr.Array;
                                    for (int i = 0; i < size; i++) {
                                        System.out.print(sorted[i]+ " ");
                                    }
                                    helper.execute_Update("INSERT INTO " + tablename + " (Array,Sorted_Array) VALUES ('" + Arrays.toString(dupl)+"','"+ Arrays.toString(Arr.Array)+ "')");
                                    break;
                                case 2:
                                    Arr.bubble_sort_down();
                                    int [] sorted1 = Arr.Array;
                                    for (int i = 0; i < size; i++) {
                                        System.out.print(sorted1[i]+ " ");
                                    }
                                    helper.execute_Update("INSERT INTO " + tablename + " (Array,Sorted_Array) VALUES ('" + Arrays.toString(dupl)+"','"+ Arrays.toString(Arr.Array)+ "')");
                                    break;
                            }
                            break;
                        case 2:
                            helper.execute_Update("INSERT INTO " + tablename + " (Array) VALUES ('" + Arrays.toString(Arr.Array)+"')");
                            break;
                    }
                    break;

                case 4:
                    helper.to_excel(tablename, "output.xlsx");
                    break;


            }
        }

    }
}


