package br.com.cbgomes.promotion.model.form

import br.com.cbgomes.product.model.entity.Product
import br.com.cbgomes.promotion.model.entity.Promotion
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate

data class PromotionForm(

        @JsonFormat(pattern = "dd-MM-yyyy")
        val initial_date: LocalDate,

        @JsonFormat(pattern = "dd-MM-yyyy")
        val final_date: LocalDate,

        val productsInPromotion: List<Product> = emptyList()
)

fun PromotionForm.toEntity() = Promotion(
        id = null,
        initial_date = this.initial_date,
        final_date = this.final_date,
        productsInPromotion = this.productsInPromotion
)