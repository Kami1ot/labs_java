public final class Sort extends Array_one_dimensional {
    public void bubble_sort_up() {
        for (int i = 0; i < arr_size - 1; i++) {
            for (int j = 0; j < arr_size - i - 1; j++) {
                if (super.Array[j] > super.Array[j + 1]) {
                    int temp = super.Array[j];
                    super.Array[j] = super.Array[j + 1];
                    super.Array[j + 1] = temp;
                }
            }
        }
    }

    public void bubble_sort_down(){
        for (int i = 0; i < arr_size - 1; i++) {
            for (int j = 0; j < arr_size - i - 1; j++) {
                if (super.Array[j] < super.Array[j + 1]) {
                    int temp = super.Array[j];
                    super.Array[j] = super.Array[j + 1];
                    super.Array[j + 1] = temp;
                }
            }
        }


    }


}
