package br.com.cbgomes.product.resource

import br.com.cbgomes.product.business.service.ProductService
import br.com.cbgomes.product.model.dto.ProductDto
import br.com.cbgomes.product.model.form.ProductForm
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/products")
class ProductResource(val productService: ProductService) {


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    fun getProductById(@PathVariable("id") id: Long): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(this.productService.getById(id))
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody product: ProductForm): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(this.productService.create(product))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProductById(@PathVariable("id") id: Long) {
        this.productService.deleteProduct(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllProducts(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(this.productService.getAll())
    }


    @PutMapping("/put-in-promotion/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun putProductOnPromotion(@PathVariable("id") id: Long,
                              @RequestParam("promotion_id") promotion_id: Long,
                              @RequestParam("price_promotion") price_promotion: Long): ResponseEntity<ProductDto> {

        return ResponseEntity.ok(this.productService.putProductInPromotion(id,promotion_id, BigDecimal(price_promotion)))
    }

    @GetMapping("/products-in-promotion")
    @ResponseStatus(HttpStatus.OK)
    fun getAllProductsInPromotion(): ResponseEntity<List<ProductDto>>{
        return ResponseEntity.ok(this.productService.getAllProductsInPromotion())
    }

    @GetMapping("/remove-product-on-promotion/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun removeProductOnPromotion(@PathVariable("id") id: Long,
                                 @RequestParam("id_promotion") id_promotion: Long): ResponseEntity<String>{

        TODO()
    }

}