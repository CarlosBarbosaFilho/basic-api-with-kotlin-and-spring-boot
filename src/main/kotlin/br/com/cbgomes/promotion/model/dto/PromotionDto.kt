package br.com.cbgomes.promotion.model.dto

import br.com.cbgomes.product.model.entity.Product
import br.com.cbgomes.promotion.model.entity.Promotion
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonInclude
import org.springframework.hateoas.RepresentationModel
import java.time.LocalDate

@JsonInclude(JsonInclude.Include.NON_NULL)
data class PromotionDto(
        val id: Long?,

        @JsonFormat(pattern = "dd-MM-yyyy")
        val initial_date: LocalDate?,

        @JsonFormat(pattern = "dd-MM-yyyy")
        val final_date: LocalDate?,

        val productsInPromotion: List<Product>? = emptyList()
): RepresentationModel<PromotionDto>()

fun Promotion.toDTO() = PromotionDto(
        id = this.id!!,
        initial_date = this.initial_date,
        final_date = this.final_date,
        productsInPromotion = this.productsInPromotion
)


