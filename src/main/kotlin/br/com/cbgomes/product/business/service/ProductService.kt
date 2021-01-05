package br.com.cbgomes.product.business.service

import br.com.cbgomes.product.model.dto.ProductDto
import br.com.cbgomes.product.model.form.ProductForm
import java.math.BigDecimal

interface ProductService {

    fun getById(id: Long): ProductDto

    fun create(product: ProductForm): ProductDto

    fun deleteProduct(id: Long)

    fun getAll(): List<ProductDto>

    fun putProductInPromotion(id_Product: Long, id_Promotion: Long, price_promotion: BigDecimal): ProductDto

    fun getAllProductsInPromotion(): List<ProductDto>

    fun removeProductPromotion(idProduct: Long, id_Promotion: Long): Boolean
}