package view;

import controller.MergeSortController;

public class MergeSort {

    public static void main(String[] args) {

        int[] vetor = {5, 3, 8, 4, 2};

        MergeSortController controller = new MergeSortController();
        controller.ordenar(vetor);

        for (int num : vetor) {
            System.out.print(num + " ");
        }
    }
}
