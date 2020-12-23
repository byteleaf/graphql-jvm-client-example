package de.byteleaf.microservice.product

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ProductClientKotlinApplication

fun main(args: Array<String>) {
    runApplication<ProductClientKotlinApplication>(*args)
}
