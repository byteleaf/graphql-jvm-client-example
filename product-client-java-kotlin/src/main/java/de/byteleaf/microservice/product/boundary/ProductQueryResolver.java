package de.byteleaf.microservice.product.boundary;

import de.byteleaf.microservice.product.control.ProductService;
import de.byteleaf.microservice.product.entity.Product;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private ProductService productService;

    public List<Product> products() {
        return productService.products();
    }

    public Product product(String id) {
        return productService.product(id);
    }
}
