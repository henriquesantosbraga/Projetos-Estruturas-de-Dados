package controller;

import model.No;

public class ListaSimplesController {

    private No inicio;

    public void inserir(int valor) {
        No novo = new No(valor);

        if (inicio == null) {
            inicio = novo;
        } else {
            No atual = inicio;
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    public void remover(int valor) {
        if (inicio == null) return;

        if (inicio.dado == valor) {
            inicio = inicio.proximo;
            return;
        }

        No atual = inicio;
        while (atual.proximo != null && atual.proximo.dado != valor) {
            atual = atual.proximo;
        }

        if (atual.proximo != null) {
            atual.proximo = atual.proximo.proximo;
        }
    }

    public void mostrar() {
        No atual = inicio;
        while (atual != null) {
            System.out.print(atual.dado + " -> ");
            atual = atual.proximo;
        }
        System.out.println("null");
    }
}
