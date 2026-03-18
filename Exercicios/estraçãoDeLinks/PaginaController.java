package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaginaController {

    private Pattern pattern = Pattern.compile("https?://[^\\s\"'>]+");

    private String dominio;

    private ArrayList<String> visitei = new ArrayList<>();

    public PaginaController(String dominio) {
        this.dominio = dominio;
    }

    private String pagina(String url) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
	
    public void links(String url) throws IOException, InterruptedException {

        // ponto de parada
        if (visitei.contains(url) || !url.contains(dominio)) {
            return;
        }

        visitei.add(url);

        System.out.println(url);

        String body = pagina(url);

        Matcher matcher = pattern.matcher(body);

        while (matcher.find()) {

            String link = matcher.group();

            if (link.endsWith(".pdf") ||
                link.endsWith(".zip") ||
                link.endsWith(".exe") ||
                link.endsWith(".js")) {
                continue;
            }

            links(link);
        }
    }
}
