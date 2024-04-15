public class MatrixPower extends ArrayPI {
    public MatrixPower(int matrix_lenth, boolean b) {
        super(matrix_lenth,b);
    }

    public int[][] powerMatrix(int power) {
        int[][] resultMatrix = new int[matrix_lenth][matrix_lenth];
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                resultMatrix[i][j] = (i == j) ? 1 : 0;
            }
        }
        for (int i = 0; i < power; i++) {
            resultMatrix = multiplyMatrices(resultMatrix, firstMatrix);
        }
        return resultMatrix;
    }

    private int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {
        int[][] resultMatrix = new int[matrix_lenth][matrix_lenth];
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                for (int k = 0; k < matrix_lenth; k++) {
                    resultMatrix[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return resultMatrix;
    }
}

