import java.util.Arrays;

public final class Matrix extends ArrayPI {


    public Matrix(int matrix_lenth) {
        super(matrix_lenth);
    }
    protected int[][] resultMatrix;
    public int[][] multiplyMatrices() {
        resultMatrix = new int[matrix_lenth][matrix_lenth];
        System.out.println("--------------------------------------------------");
        System.out.println("Первая матрица: ");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                System.out.print(firstMatrix[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println(Arrays.deepToString(firstMatrix));
        System.out.println("--------------------------------------------------");
        System.out.println("Вторая матрица: ");
        for (int i = 0; i < matrix_lenth; i++) {
                for (int j = 0; j < matrix_lenth; j++) {
                    System.out.print(secondMatrix[i][j] + " ");
                }
            System.out.println();
        }


        System.out.println(Arrays.deepToString(secondMatrix));
        System.out.println("--------------------------------------------------");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                for (int k = 0; k < matrix_lenth; k++) {
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        System.out.println("Результат перемножения: ");
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                System.out.print(resultMatrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("--------------------------------------------------");
        return resultMatrix;
    }
}



