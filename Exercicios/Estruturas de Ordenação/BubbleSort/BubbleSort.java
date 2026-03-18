package view;

import controller.BubbleSortController;

public class BubbleSort {

    public static void main(String[] args) {

        int[] vetor = {5, 3, 8, 4, 2};

        BubbleSortController controller = new BubbleSortController();
        controller.ordenar(vetor);

        for (int num : vetor) {
            System.out.print(num + " ");
        }
    }
}
