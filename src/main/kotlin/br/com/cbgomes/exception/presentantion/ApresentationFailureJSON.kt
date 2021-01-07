package br.com.cbgomes.exception.presentantion

import br.com.cbgomes.product.exception.ProductExistsPromotionNotFoundException
import br.com.cbgomes.product.exception.ProductNotFoundException
import br.com.cbgomes.promotion.exceptions.DateInvalidToCreatePromotion
import br.com.cbgomes.promotion.exceptions.PromotionNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
class PresentationFailureJSON {

    fun productNotFoundExceptionHandler(ex: ProductNotFoundException): FailureResponseException {
        return FailureResponseException(
                message = "Cannot possible found product to this id ${ex.id}",
                description = " The value passed to url is invalid, please inform on valid id in the request",
                status = HttpStatus.BAD_REQUEST,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("pt-BR") ))
        )
    }

    fun productExistsOnPromotionIsNotPossibleRemoveExceptionHandler(ex: ProductExistsPromotionNotFoundException): FailureResponseException {
        return FailureResponseException(
                message = "Cannot possible remove the product with id ${ex.id}, exists a promotion associated this him",
                description = " Please, informed other product to removed",
                status = HttpStatus.BAD_REQUEST,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("pt-BR") ))
        )
    }

    fun promotionNotFoundExceptionHandler(ex: PromotionNotFoundException): FailureResponseException {
        return FailureResponseException(
                message = "Cannot possible found promotion to this id ${ex.id}",
                description = " The value passed to url is invalid, please inform on valid id in the request",
                status = HttpStatus.BAD_REQUEST,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("pt-BR") ))
        )
    }

    fun dataInvalidToCreatePromotion(ex: DateInvalidToCreatePromotion): FailureResponseException {
        return FailureResponseException(
                message = "Cannot possible create a promotion with dates ${ex.dataBegin} and ${ex.endDate}",
                description = " Dates invalids to create a promotion, check the period of dates informed",
                status = HttpStatus.BAD_REQUEST,
                date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale("pt-BR") ))
        )
    }

}

data class FailureResponseException(val message: String, val description: String, val status: HttpStatus, val date: String)