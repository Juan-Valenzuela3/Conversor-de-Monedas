import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Api {

    public String obtenerDatos(String moneda) throws IOException, InterruptedException {
        String url = "https://v6.exchangerate-api.com/v6/08d617f260035a0613c8f10e/latest/" + moneda;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());

        // Retorna la respuesta como String
        return response.body();
    }
}
