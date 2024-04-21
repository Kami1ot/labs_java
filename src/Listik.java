import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Listik {
    protected List<Integer> randomList;
    protected List<Integer> inputList;

    public List<Integer> getRandomList(){
        this.randomList = new ArrayList<>(1000);

        for (int i = 0; i < 100; i++){
            this.randomList.add((int) (Math.random()*6));

        }
        return this.randomList;
    }

    protected String inputList(int length){
        inputList = new ArrayList<>(length);
        Scanner in = new Scanner(System.in);
        for (int i =0; i < length; i++){
            System.out.print("Введите " + i + " элемент списка: ");
            inputList.add(in.nextInt());
        }
        return inputList.toString();
    }
    protected String inputList(int a, int b, int length){
        Random random = new Random();
        inputList = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            int x = random.nextInt(a, b);
            this.inputList.add(x);
        }
        return inputList.toString();
    }


    public List<Integer> getInputList() {
        return inputList;
    }

}

