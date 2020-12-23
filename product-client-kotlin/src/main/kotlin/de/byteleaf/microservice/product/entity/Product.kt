package de.byteleaf.microservice.product.entity

import de.byteleaf.microservice.product.config.NoArgConstructor

@NoArgConstructor
data class Product(val id: String, val name: String, val description: String)
