import java.util.Scanner;

public class  Array_one_dimensional {

    public int[] Array;

    protected int arr_size;

    public void createarray(int arr_size){
        this.arr_size = arr_size;
        Scanner in = new Scanner(System.in);
        Array = new int[arr_size];
        for (int i = 0; i < arr_size; i++) {
            System.out.print("Введите элемент под номером " + i + ": ");
            Array[i] = in.nextInt();

        }



    }
}
