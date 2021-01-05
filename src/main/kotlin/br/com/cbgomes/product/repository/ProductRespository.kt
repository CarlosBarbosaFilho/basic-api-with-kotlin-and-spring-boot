package br.com.cbgomes.product.repository

import br.com.cbgomes.product.model.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

    @Query(" FROM Product p WHERE p.promotion is not null ")
    fun getAllProductsInPromotion(): List<Product>
}
