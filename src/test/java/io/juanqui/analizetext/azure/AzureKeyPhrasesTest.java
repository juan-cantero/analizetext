package io.juanqui.analizetext.azure;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AzureKeyPhrasesTest {
    @Value("${AZURE_API_KEY}")
    private String azureApiKey;

    private static final String AZURE_ENDPOINT = "https://boca-juniors-feedback.cognitiveservices.azure.com/";

    private static final String AZURE_ENDPOINT_PATH = "/text/analytics/v3.0/keyPhrases";

    private static final String API_KEY_HEADER_NAME = "Ocp-Apim-Subscription-Key";

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String APPLICATION_JSON = "application/json";


    private static final String textForAnalysis = "In an e360 interview, Carlos Nobre, Brazil’s leading expert on the Amazon and climate change, discusses the key perils facing the world’s largest rainforest, where a record number of fires are now raging, and lays out what can be done to stave off a ruinous transformation of the region.";

    @Autowired
    public ObjectMapper mapper;

    @Test
    public void getEntities() throws IOException, InterruptedException {

        TextDocument document = new TextDocument("1", textForAnalysis, "en");
        TextAnalyticsRequest requestBody = new TextAnalyticsRequest();
        requestBody.getDocuments().add(document);

        // 1.  Create a client
        HttpClient httpClient = HttpClient.newHttpClient();

        // 2.  Create the request
        HttpRequest request = HttpRequest.newBuilder()
                .header(CONTENT_TYPE,APPLICATION_JSON)
                .header(API_KEY_HEADER_NAME,azureApiKey)
                .uri(URI.create(AZURE_ENDPOINT + AZURE_ENDPOINT_PATH))
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody)))
                .build();
        // 3.  Send the request and receive response
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // 4.  Work with the response
        assertEquals(200,response.statusCode());

        System.out.println(response.body());

    }
}
