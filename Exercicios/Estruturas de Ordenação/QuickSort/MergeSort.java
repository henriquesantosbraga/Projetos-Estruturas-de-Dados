package view;

import controller.QuickSortController;

public class QuickSort {

    public static void main(String[] args) {

        int[] vetor = {5, 3, 8, 4, 2};

        QuickSortController controller = new QuickSortController();
        controller.ordenar(vetor);

        for (int num : vetor) {
            System.out.print(num + " ");
        }
    }
}
