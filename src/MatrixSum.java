public class MatrixSum extends ArrayPI {
    public MatrixSum(int matrix_lenth) {
        super(matrix_lenth);
    }

    public int[][] sumMatrices() {
        int[][] resultMatrix = new int[matrix_lenth][matrix_lenth];
        for (int i = 0; i < matrix_lenth; i++) {
            for (int j = 0; j < matrix_lenth; j++) {
                resultMatrix[i][j] = firstMatrix[i][j] + secondMatrix[i][j];
            }
        }
        return resultMatrix;
    }
}

