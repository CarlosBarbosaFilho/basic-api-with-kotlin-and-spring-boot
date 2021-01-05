package br.com.cbgomes.product.model.dto

import br.com.cbgomes.product.model.entity.Product
import com.fasterxml.jackson.annotation.JsonInclude
import java.math.BigDecimal

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProductDto(
        val id: Long,
        val stock: Int?,
        val price: BigDecimal?,
        val price_promotion: BigDecimal?,
        val category: String?,
        val code: Int?,
        val promotion: Long?
)

fun Product.toDTO() = ProductDto(
        id = this.id!!,
        stock = this.stock,
        price = this.price,
        price_promotion = this.price_promotion,
        category = this.category,
        code = this.code,
        promotion = this.promotion?.id
)