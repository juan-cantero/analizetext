package io.juanqui.analizetext.azure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class AzureSentimentServiceTest {

	@Autowired
	private AzureSentimentService sentimentService;
	
	@Test
	void testPositiveSentiment() throws IOException, InterruptedException {
		
		SentimentAnalysis analysis = this.sentimentService.requestSentimentAnalysis("I love playing videogames!", "en");
		assertNotNull(analysis);
		assertEquals("positive", analysis.getSentiment());
	}

	@Test
	void testNegativeSentiment() throws IOException, InterruptedException {
		
		SentimentAnalysis analysis = this.sentimentService.requestSentimentAnalysis("this is horrible!", "en");
		assertNotNull(analysis);
		assertEquals("negative", analysis.getSentiment());
	}
}
