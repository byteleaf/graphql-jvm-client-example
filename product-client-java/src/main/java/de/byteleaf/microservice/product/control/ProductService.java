package de.byteleaf.microservice.product.control;

import de.byteleaf.microservice.product.client.ProductClient;
import de.byteleaf.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductClient productClient;

    public List<Product> products() {
        return productClient.allProducts();
    }

    public Product product(String id) {
        return productClient.getProduct(id);
    }
}
