package controller;

import model.estrutura.Grafo;
import model.estrutura.Grafo.ResultadoCaminho;
import java.util.*;

public class GrafoController<T> {
 
    private Grafo<String> grafo;
 
    // Fábrica (origem principal)
    public static final String FABRICA = "Belo Horizonte";
 
    // Centros logísticos escolhidos (devem cobrir regiões distintas)
    // Centro 1: Rio de Janeiro  -> cobre sul/leste (RJ, São Paulo, Campos, Vitória, Juiz de Fora)
    // Centro 2: Uberlândia     -> cobre oeste/norte (Uberaba, Patos de Minas, Ribeirão Preto, São José do Rio Preto)
    public static final String CENTRO1 = "Rio de Janeiro";
    public static final String CENTRO2 = "Uberlândia";
 
    // Cidades atendidas por cada centro (sem sobreposição e sem BH)
    private Set<String> cidadesCentro1;
    private Set<String> cidadesCentro2;
    private Set<String> cidadesFabrica;
 
    public GrafoController() {
        grafo = new Grafo<String>();
        montarGrafo();
        definirZonas();
    }
 
    private void montarGrafo() {
        // Todas as arestas da tabela do documento
        grafo.link("Belo Horizonte",        "Governador Valadares", 313);
        grafo.link("Belo Horizonte",        "Manhuacu",             279);
        grafo.link("Belo Horizonte",        "Uberaba",              476);
        grafo.link("Belo Horizonte",        "Juiz de Fora",         260);
        grafo.link("Uberaba",               "Uberlandia",           105);
        grafo.link("Uberlandia",            "Patos de Minas",       222);
        grafo.link("Governador Valadares",  "Colatina",             216);
        grafo.link("Manhuacu",              "Governador Valadares", 198);
        grafo.link("Manhuacu",              "Vitoria",              233);
        grafo.link("Manhuacu",              "Juiz de Fora",         290);
        grafo.link("Juiz de Fora",          "Rio de Janeiro",       181);
        grafo.link("Rio de Janeiro",        "Campos dos Goytacazes",277);
        grafo.link("Campos dos Goytacazes", "Vitoria",              240);
        grafo.link("Rio de Janeiro",        "Sao Jose dos Campos",  341);
        grafo.link("Sao Jose dos Campos",   "Sao Paulo",            79);
        grafo.link("Sao Paulo",             "Campinas",             111);
        grafo.link("Campinas",              "Sao Carlos",           142);
        grafo.link("Sao Carlos",            "Ribeirao Preto",       99);
        grafo.link("Ribeirao Preto",        "Franca",               89);
        grafo.link("Ribeirao Preto",        "Uberlandia",           278);
        grafo.link("Uberlandia",            "Sao Jose do Rio Preto",285);
        grafo.link("Sao Jose do Rio Preto", "Sao Carlos",           206);
    }
 
    private void definirZonas() {
        // Centro 1: Rio de Janeiro -> atende região sul/leste
        cidadesCentro1 = new LinkedHashSet<>(Arrays.asList(
            "Juiz de Fora",
            "Campos dos Goytacazes",
            "Vitoria",
            "Colatina",
            "Sao Jose dos Campos",
            "Sao Paulo",
            "Campinas",
            "Sao Carlos",
            "Ribeirao Preto",
            "Franca",
            "Sao Jose do Rio Preto"
        ));
 
        // Centro 2: Uberlândia -> atende região oeste/triângulo
        cidadesCentro2 = new LinkedHashSet<>(Arrays.asList(
            "Uberaba",
            "Patos de Minas"
        ));
 
        // Fábrica direta: cidades não cobertas pelos centros
        cidadesFabrica = new LinkedHashSet<>(Arrays.asList(
            "Governador Valadares",
            "Manhuacu"
        ));
    }
 
    // Determina de onde a cidade é atendida e calcula a rota
    public String consultarEntrega(String destino) {
        if (destino.equals(FABRICA)) {
            return "\"" + destino + "\" é a própria fábrica!";
        }
 
        // Verifica se a cidade existe no grafo
        if (!grafo.getVertices().contains(destino)) {
            return "Cidade \"" + destino + "\" não encontrada no sistema.";
        }
 
        StringBuilder sb = new StringBuilder();
        sb.append("=== CONSULTA DE ENTREGA: ").append(destino).append(" ===\n");
 
        if (cidadesCentro1.contains(destino)) {
            // Atendida pelo Centro 1 (Rio de Janeiro)
            ResultadoCaminho<String> rotaCentro = grafo.dijkstra(CENTRO1, destino);
            ResultadoCaminho<String> rotaFabrica = grafo.dijkstra(FABRICA, destino);
 
            sb.append("[FÁBRICA]           ").append(rotaFabrica.toString()).append("\n");
            sb.append("[CENTRO LOGÍSTICO 1 - ").append(CENTRO1).append("] ")
              .append(rotaCentro.toString()).append("\n");
 
        } else if (cidadesCentro2.contains(destino)) {
            // Atendida pelo Centro 2 (Uberlândia)
            ResultadoCaminho<String> rotaCentro = grafo.dijkstra(CENTRO2, destino);
            ResultadoCaminho<String> rotaFabrica = grafo.dijkstra(FABRICA, destino);
 
            sb.append("[FÁBRICA]           ").append(rotaFabrica.toString()).append("\n");
            sb.append("[CENTRO LOGÍSTICO 2 - ").append(CENTRO2).append("] ")
              .append(rotaCentro.toString()).append("\n");
 
        } else {
            // Atendida diretamente pela fábrica
            ResultadoCaminho<String> rotaFabrica = grafo.dijkstra(FABRICA, destino);
            sb.append("[FÁBRICA]           ").append(rotaFabrica.toString()).append("\n");
            sb.append("[Cidade atendida diretamente pela Fábrica - sem centro logístico]\n");
        }
 
        return sb.toString();
    }
 
    // Lista todas as cidades e quem as atende
    public String listarCidades() {
        StringBuilder sb = new StringBuilder();
        sb.append("=== CIDADES ATENDIDAS ===\n");
        sb.append("FÁBRICA: ").append(FABRICA).append("\n");
        sb.append("CENTRO LOGÍSTICO 1: ").append(CENTRO1).append("\n");
        sb.append("CENTRO LOGÍSTICO 2: ").append(CENTRO2).append("\n\n");
 
        sb.append("--- Atendidas diretamente pela Fábrica ---\n");
        for (String c : cidadesFabrica) sb.append("  * ").append(c).append("\n");
 
        sb.append("\n--- Atendidas pelo Centro 1 (").append(CENTRO1).append(") ---\n");
        for (String c : cidadesCentro1) sb.append("  * ").append(c).append("\n");
 
        sb.append("\n--- Atendidas pelo Centro 2 (").append(CENTRO2).append(") ---\n");
        for (String c : cidadesCentro2) sb.append("  * ").append(c).append("\n");
 
        return sb.toString();
    }
 
    public String grafoToString() {
        return grafo.toString();
    }
}
