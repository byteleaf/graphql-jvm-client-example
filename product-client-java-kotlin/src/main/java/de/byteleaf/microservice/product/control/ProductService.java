package de.byteleaf.microservice.product.control;

import de.byteleaf.microservice.product.client.ProductClient;
import de.byteleaf.microservice.product.entity.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private ModelMapper modelMapper;

    public List<Product> products() {
        return productClient.allProducts().stream()
                .map(it -> modelMapper.map(it, Product.class)).collect(Collectors.toList());
    }

    public Product product(String id) {
        return modelMapper.map(productClient.getProduct(id), Product.class);
    }
}
