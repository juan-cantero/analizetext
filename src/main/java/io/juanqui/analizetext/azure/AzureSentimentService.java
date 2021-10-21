package io.juanqui.analizetext.azure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Service
public class AzureSentimentService {

	@Value("${AZURE_API_KEY}")
	private String azureApiKey;

	@Autowired
	private AzureConfig config;
		
	@Autowired
	private ObjectMapper mapper;
	


	public SentimentAnalysis requestSentimentAnalysis(String text, String language) throws IOException, InterruptedException {

		//Hint:  Find the path for the sentiment endpoint in the Text Analytics API Reference

		
		//Hint:  Use the TextAnalyticsRequest and TextDocument objects to build the request
		
		//Hint:  Use Jackson's ObjectMapper and JsonNode to handle the response

		TextDocument document = new TextDocument("1", text, language);
		TextAnalyticsRequest requestBody = new TextAnalyticsRequest();
		requestBody.getDocuments().add(document);

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder()
				.header(config.getContentType(), config.getApplicationJson())
						.header(config.getApiHeaderName(),azureApiKey)
						.uri(URI.create(config.getBaseUrl() + config.getSentimentEndpoint()))
						.POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(requestBody)))
						.timeout(Duration.ofSeconds(5))
						.build();

		HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

		JsonNode jsonNode = mapper.readValue(response.body(),JsonNode.class);
		String sentiment = jsonNode.get("documents").get(0).get("sentiment").asText();
				
		SentimentAnalysis sentimentAnalysis =  new SentimentAnalysis();
		sentimentAnalysis.setDocument(document);
		sentimentAnalysis.setSentiment(sentiment);
		return sentimentAnalysis;

	}
}
