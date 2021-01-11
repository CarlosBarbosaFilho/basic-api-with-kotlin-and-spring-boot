package br.com.cbgomes.product.business.serviceimpl

import br.com.cbgomes.product.business.service.ProductService
import br.com.cbgomes.product.exception.ProductExistsPromotionNotFoundException
import br.com.cbgomes.product.exception.ProductNotFoundException
import br.com.cbgomes.product.model.dto.ProductDto
import br.com.cbgomes.product.model.dto.toDTO
import br.com.cbgomes.product.model.form.ProductForm
import br.com.cbgomes.product.model.form.toEntity
import br.com.cbgomes.product.repository.ProductRepository
import br.com.cbgomes.promotion.repository.PromotionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.math.BigDecimal

@Service
class ProductServiceImpl(val productRepository: ProductRepository, val promotionRepository: PromotionRepository): ProductService {

    override fun getById(id: Long): ProductDto {
        return try {
            this.productRepository.findById(id).get().toDTO()
        } catch (ex: NoSuchElementException){
            throw ProductNotFoundException(id)
        }
    }

    @Transactional
    override fun create(product: ProductForm): ProductDto {
        return this.productRepository.save(product.toEntity()).toDTO()
    }

    @Transactional
    override fun deleteProductById(id: Long) {
        if (this.productRepository.findById(id).get().promotion != null) {
                throw ProductExistsPromotionNotFoundException(id)
        }else {
            this.productRepository.deleteById(id)
        }
    }


    override fun getAll(): List<ProductDto> {
        return this.productRepository.findAll().map { it.toDTO() }
    }

    @Transactional
    override fun putProductInPromotion(id_Product: Long, id_Promotion: Long, price_promotion: BigDecimal): ProductDto {
        val product = this.productRepository.findById(id_Product).orElseThrow()
        val promotion = this.promotionRepository.findById(id_Promotion).orElseThrow()
        product.promotion = promotion
        product.price_promotion = price_promotion.toString().toBigDecimal()
        return this.productRepository.save(product).toDTO()
    }

    override fun getAllProductsInPromotion(): List<ProductDto> {
       return this.productRepository.getAllProductsInPromotion().map { it.toDTO() }
    }

    @Transactional
    override fun removeProductPromotion(idProduct: Long, id_Promotion: Long): Boolean {
        TODO("Not yet implemented")
    }
}
