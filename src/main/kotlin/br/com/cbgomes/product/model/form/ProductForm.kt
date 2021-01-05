package br.com.cbgomes.product.model.form

import br.com.cbgomes.product.model.entity.Product
import br.com.cbgomes.promotion.model.entity.Promotion
import java.math.BigDecimal

data  class ProductForm(
        val stock: Int,
        val price: BigDecimal,
        val price_promotion: BigDecimal?,
        val category: String,
        val code: Int,
        val promotion: Promotion?
)

fun ProductForm.toEntity() = Product(
        id = null,
        stock = this.stock,
        price = this.price,
        price_promotion = this.price_promotion,
        category = this.category,
        code = this.code,
        promotion = this.promotion,
)

