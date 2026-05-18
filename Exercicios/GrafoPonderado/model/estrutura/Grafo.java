package model.estrutura;

import java.util.*;

public class Grafo<T> {

    private Map<T, Map<T, Integer>> adjacencia;

    public Grafo() {
        this.adjacencia = new LinkedHashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void adicionarVertice(T vertice) {
        adjacencia.putIfAbsent(vertice, new LinkedHashMap<>());
    }

    // Liga dois vértices com um peso (aresta bidirecional ponderada)
    public void link(T vertice1, T vertice2, int peso) {
        adicionarVertice(vertice1);
        adicionarVertice(vertice2);
        adjacencia.get(vertice1).put(vertice2, peso);
        adjacencia.get(vertice2).put(vertice1, peso);
    }

    // Retorna todos os vértices do grafo
    public Set<T> getVertices() {
        return adjacencia.keySet();
    }

    // Retorna os vizinhos de um vértice com seus respectivos pesos
    public Map<T, Integer> getVizinhos(T vertice) {
        return adjacencia.getOrDefault(vertice, new LinkedHashMap<>());
    }

    // Algoritmo de Dijkstra: retorna o caminho mínimo de 'origem' até 'destino'
    public ResultadoCaminho<T> dijkstra(T origem, T destino) {
        Map<T, Integer> distancias = new HashMap<>();
        Map<T, T> anteriores = new HashMap<>();
        Set<T> visitados = new HashSet<>();

        // PriorityQueue ordenada pela distância acumulada de cada vértice
        PriorityQueue<T> fila = new PriorityQueue<>(
                Comparator.comparingInt(v -> distancias.getOrDefault(v, Integer.MAX_VALUE))
        );

        // Inicializa todas as distâncias como "infinito"
        for (T vertice : adjacencia.keySet()) {
            distancias.put(vertice, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        fila.add(origem);

        while (!fila.isEmpty()) {
            T atual = fila.poll();

            if (visitados.contains(atual)) {
                continue;
            }
            visitados.add(atual);

            if (atual.equals(destino)) {
                break;
            }

            for (Map.Entry<T, Integer> vizinho : adjacencia.getOrDefault(atual, new HashMap<>()).entrySet()) {
                T proximo = vizinho.getKey();
                int peso = vizinho.getValue();

                if (!visitados.contains(proximo)) {
                    int novaDistancia = distancias.get(atual) + peso;
                    if (novaDistancia < distancias.getOrDefault(proximo, Integer.MAX_VALUE)) {
                        distancias.put(proximo, novaDistancia);
                        anteriores.put(proximo, atual);
                        fila.add(proximo);
                    }
                }
            }
        }

        // Reconstrói o caminho percorrendo os anteriores de trás para frente
        List<T> caminho = new ArrayList<>();
        T atual = destino;
        while (atual != null) {
            caminho.add(0, atual);
            atual = anteriores.get(atual);
        }

        int distanciaTotal = distancias.getOrDefault(destino, Integer.MAX_VALUE);

        // Caminho inválido: destino inacessível ou origem não chegou até ele
        if (distanciaTotal == Integer.MAX_VALUE || caminho.isEmpty() || !caminho.get(0).equals(origem)) {
            return new ResultadoCaminho<>(null, -1);
        }

        return new ResultadoCaminho<>(caminho, distanciaTotal);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<T, Map<T, Integer>> entry : adjacencia.entrySet()) {
            builder.append(entry.getKey()).append(": ");
            for (Map.Entry<T, Integer> viz : entry.getValue().entrySet()) {
                builder.append(viz.getKey()).append("(").append(viz.getValue()).append("km) ");
            }
            builder.append("\r\n");
        }
        return builder.toString();
    }

    // -------------------------------------------------------------------------
    // Classe interna genérica para encapsular o resultado do Dijkstra
    // -------------------------------------------------------------------------
    public static class ResultadoCaminho<T> {

        public final List<T> caminho;
        public final int distancia;

        public ResultadoCaminho(List<T> caminho, int distancia) {
            this.caminho = caminho;
            this.distancia = distancia;
        }

        public boolean encontrado() {
            return caminho != null && distancia >= 0;
        }

        @Override
        public String toString() {
            if (!encontrado()) {
                return "Rota não encontrada.";
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < caminho.size(); i++) {
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(caminho.get(i));
            }
            sb.append(": ").append(distancia).append(" KM");
            return sb.toString();
        }
    }
}
