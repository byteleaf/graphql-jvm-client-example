package de.byteleaf.microservice.product.config;

import com.expediagroup.graphql.client.GraphQLWebClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLWebClientConfig {

    @Value("${graphql.client.url}")
    private String url;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public GraphQLWebClient getGraphQLWebClient() {
        return new GraphQLWebClient(url, objectMapper, WebClient.builder());
    }
}
