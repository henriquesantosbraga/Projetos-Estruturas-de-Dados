import java.io.*;
import java.net.*;
import java.util.*;

public class PaginaController {
	private static final Pattern urlPattern = Pattern.compile("", pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
	private String dominio;
	private ArrayList<String> visitei;
	
	public PaginaController(String dominio) {
		super();
		this.dominio = dominio;
		this.visitei = new ArrayList<String>();
	}
	
	private String pagina(String url) throws IOException, InterruptedException{
		System.out.println("[DOWNLOAD >>] " + url);
		HttpClient client = HttClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		return response.body();
	}
	
	public void links(String url) throws IOException, InterruptedException{
	
	}
}