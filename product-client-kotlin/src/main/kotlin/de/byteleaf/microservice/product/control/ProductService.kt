package de.byteleaf.microservice.product.control

import de.byteleaf.microservice.product.client.ProductClient
import de.byteleaf.microservice.product.entity.Product
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    private lateinit var productClient: ProductClient

    @Autowired
    private lateinit var modelMapper: ModelMapper

    fun products(): List<Product> {
        return productClient.allProducts().map { modelMapper.map(it, Product::class.java) }
    }

    fun product(id: String): Product {
        return modelMapper.map(productClient.getProduct(id), Product::class.java)
    }
}
