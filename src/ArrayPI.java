import java.util.Scanner;

public class ArrayPI {
    protected int[][] firstMatrix;
    protected int[][] secondMatrix;
    protected int matrix_lenth;
    public ArrayPI(int matrix_lenth) {
        this.matrix_lenth = matrix_lenth;
        Scanner in = new Scanner(System.in);
        firstMatrix = new int[matrix_lenth][matrix_lenth];
        secondMatrix = new int[matrix_lenth][matrix_lenth];
        System.out.println("--------------------------------------------------");
        System.out.println("Введите первую матрицу:");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                firstMatrix[i][j] = in.nextInt();
            }
        }
        System.out.println("--------------------------------------------------");
        System.out.println("Введите вторую матрицу:");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                secondMatrix[i][j] = in.nextInt();
            }
        }

    }
    public ArrayPI(int matrix_lenth, boolean flag) {
        this.matrix_lenth = matrix_lenth;
        Scanner in = new Scanner(System.in);
        firstMatrix = new int[matrix_lenth][matrix_lenth];
        secondMatrix = new int[matrix_lenth][matrix_lenth];
        System.out.println("--------------------------------------------------");
        System.out.println("Введите первую матрицу:");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                firstMatrix[i][j] = in.nextInt();
            }
        }
    }

    public int[][] getFirstMatrix() {
        return firstMatrix;
    }

    public int[][] getSecondMatrix() {
        return secondMatrix;
    }
}

