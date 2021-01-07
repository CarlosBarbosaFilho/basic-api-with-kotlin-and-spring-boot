package br.com.cbgomes.product.repository

import br.com.cbgomes.product.model.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
interface ProductRepository: JpaRepository<Product, Long> {

    @Query(" FROM Product p WHERE p.promotion is not null ")
    fun getAllProductsInPromotion(): List<Product>

    @Transactional
    @Modifying
    @Query("DELETE FROM Product p WHERE p.id = :id")
    fun removeProductId(@Param("id") id: Long)
}
