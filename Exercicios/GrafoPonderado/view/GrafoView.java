package view;

import controller.RoteirizacaoController;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GrafoView {

    public static void main(String[] args) {

        RoteirizacaoController controller = new RoteirizacaoController();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        System.out.println("\n===== MENU =====");
        System.out.println("1. Consultar entrega para uma cidade");
        System.out.println("2. Listar todas as cidades e centros de atendimento");
        System.out.println("3. Exibir grafo completo de conexões");
        System.out.println("0. Sair");
        System.out.print("Escolha: ");
        
        while (opcao != 0) {
            JOptionPane.showMessageDialog(null, "╔══════════════════════════════════════════════════════════╗ \n"
                    + "║        SISTEMA DE ROTEIRIZAÇÃO - FIAT BH                ║ \n"
                    + "║  Fábrica: Belo Horizonte                                ║ \n"
                    + "║  Centro Logístico 1: Rio de Janeiro                     ║ \n"
                    + "║  Centro Logístico 2: Uberlândia                         ║ \n"
                    + "╚══════════════════════════════════════════════════════════╝");
            opcao = Integer.parseInt(JOptionPane.showInputDialog("\n===== MENU ===== \n"
                    + "1. Consultar entrega para uma cidade \n"
                    + "2. Listar todas as cidades e centros de atendimento"
                    + "3. Exibir grafo completo de conexões \n"
                    + "0. Sair"
                    ));
        }

        switch (opcao) {
            case 1:
                System.out.print("\nDigite o nome da cidade de destino: ");
                String cidade = scanner.nextLine().trim();
                System.out.println();
                System.out.println(controller.consultarEntrega(cidade));
                break;

            case 2:
                System.out.println();
                System.out.println(controller.listarCidades());
                break;

            case 3:
                System.out.println("\n=== GRAFO DE CONEXÕES ===");
                System.out.println(controller.grafoToString());
                break;

            case 0:
                System.out.println("Encerrando sistema...");
                break;

            default:
                System.out.println("Opção inválida!");
        }
    }
}
