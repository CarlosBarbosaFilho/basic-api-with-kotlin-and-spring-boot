package br.com.cbgomes.exception.controller

import br.com.cbgomes.exception.presentantion.PresentationFailureJSON
import br.com.cbgomes.product.exception.ProductExistsPromotionNotFoundException
import br.com.cbgomes.product.exception.ProductNotFoundException
import br.com.cbgomes.product.resource.ProductResource
import br.com.cbgomes.promotion.exceptions.DateInvalidToCreatePromotion
import br.com.cbgomes.promotion.exceptions.PromotionNotFoundException
import br.com.cbgomes.promotion.resource.PromotionResource
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice(assignableTypes = [ProductResource::class, PromotionResource::class])
data class ControllerAdvice (private val presenter: PresentationFailureJSON){

    @ExceptionHandler(ProductNotFoundException::class)
    fun productNotFoundException(ex: ProductNotFoundException)
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(presenter.productNotFoundExceptionHandler(ex))

    @ExceptionHandler(PromotionNotFoundException::class)
    fun promotionNotFoundException(ex: PromotionNotFoundException)
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(presenter.promotionNotFoundExceptionHandler(ex))

    @ExceptionHandler(ProductExistsPromotionNotFoundException::class)
    fun productExistsPromotionNotFoundException(ex: ProductExistsPromotionNotFoundException)
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(presenter.productExistsOnPromotionIsNotPossibleRemoveExceptionHandler(ex))

    @ExceptionHandler(DateInvalidToCreatePromotion::class)
    fun productExistsPromotionNotFoundException(ex: DateInvalidToCreatePromotion)
            = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(presenter.dataInvalidToCreatePromotion(ex))
}