package br.com.cbgomes.product.resource

import br.com.cbgomes.product.business.service.ProductService
import br.com.cbgomes.product.model.dto.ProductDto
import br.com.cbgomes.product.model.form.ProductForm
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping(value= ["/api/v1/products"])
class ProductResource(val productService: ProductService) {

    private val logger : Logger = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("/{id}")
    @ApiOperation(value = "Return product by id")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Return the product by id"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.FOUND)
    fun getProductById(@PathVariable("id") id: Long): ResponseEntity<ProductDto> {
           return ResponseEntity.ok(this.productService.getById(id))
    }


    @PostMapping
    @ApiOperation(value = "Create product")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Create product"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.CREATED)
    fun createProduct(@RequestBody product: ProductForm): ResponseEntity<ProductDto> {
        return ResponseEntity.ok(this.productService.create(product))
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Remove product by id")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Remove the product by id"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteProductById(@PathVariable("id") id: Long): ResponseEntity<String> {
        this.logger.info("request to delete product with parameter : $id" )
        this.productService.deleteProductById(id)
        return ResponseEntity.ok("\"Product removed with success\"")
    }

    @GetMapping
    @ApiOperation(value = "Return all products")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Return all products"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllProducts(): ResponseEntity<List<ProductDto>> {
        return ResponseEntity.ok(this.productService.getAll())
    }


    @PutMapping("/put-in-promotion/{id}")
    @ApiOperation(value = "Include on product on promotion")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Include on product on promotion"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun putProductOnPromotion(@PathVariable("id") id: Long,
                              @RequestParam("promotion_id") promotion_id: Long,
                              @RequestParam("price_promotion") price_promotion: Long): ResponseEntity<ProductDto> {

        return ResponseEntity.ok(this.productService.putProductInPromotion(id,promotion_id, BigDecimal(price_promotion)))
    }

    @GetMapping("/products-in-promotion")
    @ApiOperation(value = "List all products on promotion")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "List all products on promotion"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun getAllProductsInPromotion(): ResponseEntity<List<ProductDto>>{
        return ResponseEntity.ok(this.productService.getAllProductsInPromotion())
    }

    @GetMapping("/remove-product-on-promotion/{id}")
    @ApiOperation(value = "Remove product on promotion by id")
    @ApiResponses(value = [
        ApiResponse(code = 204, message = "Remove product on promotion by id"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.OK)
    fun removeProductOnPromotion(@PathVariable("id") id: Long,
                                 @RequestParam("id_promotion") id_promotion: Long): ResponseEntity<String>{

        TODO()
    }

}