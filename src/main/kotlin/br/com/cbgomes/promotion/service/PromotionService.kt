package br.com.cbgomes.promotion.service

import br.com.cbgomes.promotion.model.dto.PromotionDto
import br.com.cbgomes.promotion.model.form.PromotionForm
import java.time.LocalDate

interface PromotionService {

    fun save(form : PromotionForm) : PromotionDto

    fun getAllPromotions(): List<PromotionDto>

    fun getByIdPromotion(id: Long) : PromotionDto

    fun deletePromotionById(id: Long)

    fun getPromotionsByInitialDataAndFinalDate(initial_data: LocalDate, final_date: LocalDate): List<PromotionDto>

    fun existsPromotions(): Any?
}