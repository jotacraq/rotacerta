package br.com.service;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
;
import br.com.model.Entrega;
import com.fasterxml.jackson.databind.ObjectMapper;


public class viaCepService {

    public static Entrega puxarDadosCEP(String cep)
            throws IOException, InterruptedException {

        String url = "https://viacep.com.br/ws/" + cep + "/json/";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(
                request,
                HttpResponse.BodyHandlers.ofString()
        );

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readValue(response.body(), Entrega.class);
    }

}
