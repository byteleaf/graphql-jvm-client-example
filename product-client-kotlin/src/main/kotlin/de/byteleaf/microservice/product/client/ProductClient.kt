package de.byteleaf.microservice.product.client

import com.expediagroup.graphql.client.GraphQLWebClient
import de.byteleaf.microservice.generated.AllProducts
import de.byteleaf.microservice.generated.GetProduct
import de.byteleaf.microservice.generated.ID
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ProductClient {

    @Autowired
    private lateinit var graphQLWebClient: GraphQLWebClient

    fun allProducts() = runBlocking { AllProducts(graphQLWebClient).execute() }.data!!.products
    fun getProduct(id: ID) = runBlocking {
        GetProduct(graphQLWebClient).execute(GetProduct.Variables(id))
    }.data!!.product

}
