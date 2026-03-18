package controller;

public class MergeSortController {

    public void ordenar(int[] vetor) {
        merge(vetor, 0, vetor.length - 1);
    }

    private void merge(int[] vetor, int inicio, int fim) {

        if (inicio < fim) {

            int meio = (inicio + fim) / 2;

            merge(vetor, inicio, meio);
            merge(vetor, meio + 1, fim);

            intercalar(vetor, inicio, meio, fim);
        }
    }

    private void intercalar(int[] vetor, int inicio, int meio, int fim) {

        int[] aux = new int[vetor.length];

        for (int i = inicio; i <= fim; i++) {
            aux[i] = vetor[i];
        }

        int i = inicio;
        int j = meio + 1;
        int k = inicio;

        while (i <= meio && j <= fim) {

            if (aux[i] <= aux[j]) {
                vetor[k] = aux[i];
                i++;
            } else {
                vetor[k] = aux[j];
                j++;
            }
            k++;
        }

        while (i <= meio) {
            vetor[k] = aux[i];
            i++;
            k++;
        }
    }
}
