package de.byteleaf.microservice.product.client;

import com.fasterxml.jackson.core.type.TypeReference;
import de.byteleaf.microservice.product.common.graphql.client.GraphQLClient;
import de.byteleaf.microservice.product.common.graphql.client.ResourceReader;
import de.byteleaf.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
public class ProductClient {

    @Value("classpath:queries/AllProductsQuery.graphql")
    private Resource allProductsQuery;

    @Value("classpath:queries/ProductQuery.graphql")
    private Resource productQuery;

    @Autowired
    private GraphQLClient graphQLClient;

    public List<Product> allProducts() {
        String query = ResourceReader.asString(allProductsQuery);
        return graphQLClient.execute(query, "products", new TypeReference<>() {
        });
    }

    public Product getProduct(String id) {
        String query = ResourceReader.asString(productQuery);
        Map<String, String> variables = Collections.singletonMap("id", id);
        return graphQLClient.execute(query, variables, "product", Product.class);
    }

}
