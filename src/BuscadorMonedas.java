import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class BuscadorMonedas {

    public Moneda buscaMoneda(String siglaBase) {

        URI direccion = URI.create(
                "https://v6.exchangerate-api.com/v6/2f3fe01bb5fbbda893e65a8a/latest/" + siglaBase
        );

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            return new Gson().fromJson(response.body(), Moneda.class);

        } catch (Exception e) {
            throw new RuntimeException("No encontré esa moneda");
        }
    }
}

