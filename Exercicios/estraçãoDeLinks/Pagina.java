package view;

import controller.PaginaController;

public class Pagina {

    public static void main(String[] args) {

        try {
            PaginaController controller =
                    new PaginaController("terra.com.br");

            controller.links("https://www.terra.com.br");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
