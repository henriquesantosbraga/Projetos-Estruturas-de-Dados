package controller;

import model.NoDuplo;

public class ListaDuplaController {

    private NoDuplo inicio;

    public void inserir(int valor) {
        NoDuplo novo = new NoDuplo(valor);

        if (inicio == null) {
            inicio = novo;
        } else {
            NoDuplo atual = inicio;

            while (atual.proximo != null) {
                atual = atual.proximo;
            }

            atual.proximo = novo;
            novo.anterior = atual;
        }
    }

    public void remover(int valor) {
        NoDuplo atual = inicio;

        while (atual != null && atual.dado != valor) {
            atual = atual.proximo;
        }

        if (atual == null) return;

        if (atual.anterior != null) {
            atual.anterior.proximo = atual.proximo;
        } else {
            inicio = atual.proximo;
        }

        if (atual.proximo != null) {
            atual.proximo.anterior = atual.anterior;
        }
    }

    public void mostrar() {
        NoDuplo atual = inicio;

        while (atual != null) {
            System.out.print(atual.dado + " <-> ");
            atual = atual.proximo;
        }
        System.out.println("null");
    }
}
