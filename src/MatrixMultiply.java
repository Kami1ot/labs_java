public class MatrixMultiply extends ArrayPI {
    public MatrixMultiply(int matrix_lenth) {
        super(matrix_lenth);
    }

    public int[][] multiplyMatrices() {
        int[][] resultMatrix = new int[matrix_lenth][matrix_lenth];
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                for (int k = 0; k < matrix_lenth; k++) {
                    resultMatrix[i][j] += firstMatrix[i][k] * secondMatrix[k][j];
                }
            }
        }
        return resultMatrix;
    }
}
