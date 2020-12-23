package de.byteleaf.microservice.product.common.graphql.client;

public class GraphQLRequest {
    private String query;
    private String variables;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getVariables() {
        return variables;
    }

    public void setVariables(String variables) {
        this.variables = variables;
    }
}
