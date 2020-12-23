package de.byteleaf.microservice.product.config


import com.expediagroup.graphql.client.GraphQLWebClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GraphQLWebClientConfig {

    @Value("\${graphql.client.url}")
    private lateinit var url: String

    @Bean
    fun getGraphQLWebClient() = GraphQLWebClient(url)
}
