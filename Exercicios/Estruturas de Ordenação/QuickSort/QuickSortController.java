package controller;

public class QuickSortController {

    public void ordenar(int[] vetor) {
        quick(vetor, 0, vetor.length - 1);
    }

    private void quick(int[] vetor, int inicio, int fim) {

        if (inicio < fim) {

            int pivoIndex = particionar(vetor, inicio, fim);

            quick(vetor, inicio, pivoIndex - 1);
            quick(vetor, pivoIndex + 1, fim);
        }
    }

    private int particionar(int[] vetor, int inicio, int fim) {

        int pivo = vetor[inicio];
        int esquerda = inicio + 1;
        int direita = fim;

        while (esquerda <= direita) {

            while (esquerda <= direita && vetor[esquerda] <= pivo) {
                esquerda++;
            }

            while (vetor[direita] > pivo) {
                direita--;
            }

            if (esquerda < direita) {
                trocar(vetor, esquerda, direita);
            }
        }

        trocar(vetor, inicio, direita);

        return direita;
    }

    private void trocar(int[] vetor, int i, int j) {

        int temp = vetor[i];
        vetor[i] = vetor[j];
        vetor[j] = temp;
    }
}
