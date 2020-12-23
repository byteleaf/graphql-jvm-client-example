package de.byteleaf.microservice.product.client;

import com.expediagroup.graphql.client.GraphQLWebClient;
import com.expediagroup.graphql.types.GraphQLResponse;
import de.byteleaf.microservice.generated.AllProducts;
import de.byteleaf.microservice.generated.GetProduct;
import kotlin.Unit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductClient {

    @Autowired
    private GraphQLWebClient graphQLWebClient;

    public List<AllProducts.Product> allProducts() {
        return Optional.ofNullable(fetchAllProducts().getData())
                .map(AllProducts.Result::getProducts)
                .orElseThrow(IllegalStateException::new);
    }

    public GetProduct.Product getProduct(String id) {
        return Optional.ofNullable(fetchProduct(id).getData())
                .map(GetProduct.Result::getProduct)
                .orElseThrow(IllegalStateException::new);
    }

    private GraphQLResponse<AllProducts.Result> fetchAllProducts() {
        try {
            return BuildersKt.runBlocking(Dispatchers.getIO(), (coroutineScope, continuation) ->
                    new AllProducts(graphQLWebClient).execute(requestBodyUriSpec -> Unit.INSTANCE,
                            continuation));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }

    private GraphQLResponse<GetProduct.Result> fetchProduct(String id) {
        try {
            return BuildersKt.runBlocking(Dispatchers.getIO(), (coroutineScope, continuation) ->
                    new GetProduct(graphQLWebClient).execute(new GetProduct.Variables(id),
                            requestBodyUriSpec -> Unit.INSTANCE, continuation));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(e);
        }
    }
}
