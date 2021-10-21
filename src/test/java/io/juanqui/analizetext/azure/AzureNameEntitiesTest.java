package io.juanqui.analizetext.azure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AzureNameEntitiesTest {

    @Value("${AZURE_API_KEY}")
    private String azureApiKey;

    private static final String AZURE_ENDPOINT = "https://boca-juniors-feedback.cognitiveservices.azure.com/";

    private static final String AZURE_ENDPOINT_PATH = "/text/analytics/v3.0/entities/recognition/general";

    private static final String API_KEY_HEADER_NAME = "Ocp-Apim-Subscription-Key";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String APPLICATION_JSON = "application/json";

    private static final String EXAMPLE_JSON  = "{"
            + "  \"documents\": ["
            + "    {"
            + "      \"language\": \"en\","
            + "      \"id\": \"1\","
            + "      \"text\": \"Boca junior is love.\""
            + "    }"
            + "  ]"
            + "}";

    @Test
    public void getEntities() throws IOException, InterruptedException {

        // 1.  Create a client
        HttpClient httpClient = HttpClient.newHttpClient();

        // 2.  Create the request
        HttpRequest request = HttpRequest.newBuilder()
                .header(CONTENT_TYPE,APPLICATION_JSON)
                .header(API_KEY_HEADER_NAME,azureApiKey)
                .uri(URI.create(AZURE_ENDPOINT + AZURE_ENDPOINT_PATH))
                .POST(HttpRequest.BodyPublishers.ofString(EXAMPLE_JSON))
                .build();
        // 3.  Send the request and receive response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // 4.  Work with the response
        assertEquals(200,response.statusCode());

    }

}
