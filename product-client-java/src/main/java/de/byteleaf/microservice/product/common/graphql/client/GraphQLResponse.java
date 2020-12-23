package de.byteleaf.microservice.product.common.graphql.client;

import com.fasterxml.jackson.databind.JsonNode;

public class GraphQLResponse {
    private JsonNode data;
    private JsonNode error;

    public JsonNode getData() {
        return data;
    }

    public void setData(JsonNode data) {
        this.data = data;
    }

    public JsonNode getError() {
        return error;
    }

    public void setError(JsonNode error) {
        this.error = error;
    }
}
