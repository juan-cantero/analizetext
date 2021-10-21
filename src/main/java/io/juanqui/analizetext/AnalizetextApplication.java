package io.juanqui.analizetext;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnalizetextApplication {


	public static void main(String[] args) {
		SpringApplication.run(AnalizetextApplication.class, args);
	}

}
