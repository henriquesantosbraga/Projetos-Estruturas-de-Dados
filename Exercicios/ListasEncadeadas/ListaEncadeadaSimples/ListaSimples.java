package view;

import controller.ListaSimplesController;

public class ListaSimples {
    public static void main(String[] args) {

        ListaSimplesController lista = new ListaSimplesController();

        lista.inserir(10);
        lista.inserir(20);
        lista.inserir(30);

        lista.mostrar();

        lista.remover(20);
        lista.mostrar();
    }
}
