package de.byteleaf.microservice.product.common.graphql.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class GraphQLClient {

    @Value("${graphql.client.url}")
    private String url;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public <T> T execute(String query, String data, Class<T> clazz) {
        return execute(query, null, data, clazz);
    }

    public <T> T execute(String query, String data, TypeReference<T> typeReference) {
        return execute(query, null, data, typeReference);
    }

    public <T, U> T execute(String query, Map<String, U> variables, String data, Class<T> clazz) {
        JsonNode values = prepare(query, variables, data);
        return objectMapper.convertValue(values, clazz);
    }

    public <T, U> T execute(String query, Map<String, U> variables, String data, TypeReference<T> typeReference) {
        JsonNode values = prepare(query, variables, data);
        return objectMapper.convertValue(values, typeReference);
    }

    private <U> JsonNode prepare(String query, Map<String, U> variables, String data) {
        GraphQLRequest graphQLRequest = new GraphQLRequest();
        graphQLRequest.setQuery(query);
        graphQLRequest.setVariables(readAsString(variables));

        String json = readAsString(graphQLRequest);

        HttpEntity<Object> request = RequestFactory.forJson(json, new HttpHeaders());

        return postRequest(request).getData().get(data);
    }

    private <T> String readAsString(T t) {
        try {
            return objectMapper.writeValueAsString(t);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private GraphQLResponse postRequest(HttpEntity<Object> request) {
        ResponseEntity<String> response = restTemplate
                .exchange(url, HttpMethod.POST, request, String.class);
        return parseBody(response);
    }

    private GraphQLResponse parseBody(ResponseEntity<String> responseEntity) {
        try {
            return objectMapper.readValue(responseEntity.getBody(), GraphQLResponse.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
