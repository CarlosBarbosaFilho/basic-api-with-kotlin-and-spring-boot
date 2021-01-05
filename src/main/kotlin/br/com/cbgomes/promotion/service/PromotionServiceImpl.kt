package br.com.cbgomes.promotion.service

import br.com.cbgomes.promotion.model.dto.PromotionDto
import br.com.cbgomes.promotion.model.dto.toDTO
import br.com.cbgomes.promotion.model.form.PromotionForm
import br.com.cbgomes.promotion.model.form.toEntity
import br.com.cbgomes.promotion.repository.PromotionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class PromotionServiceImpl(val repository: PromotionRepository) : PromotionService {

    @Transactional
    override fun save(form: PromotionForm): PromotionDto {
        return this.repository.save(form.toEntity()).toDTO()
    }

    override fun getAllPromotions(): List<PromotionDto> {
        return this.repository.findAll().map { it.toDTO() }
    }

    override fun getByIdPromotion(id: Long): PromotionDto {
        return this.repository.findById(id).get().toDTO()
    }

    @Transactional
    override fun deletePromotionById(id: Long) {
        this.repository.deleteById(id)
    }

    override fun getPromotionsByInitialDataAndFinalDate(initial_data: LocalDate, final_date: LocalDate): List<PromotionDto> {
        return this.repository.getPromotionByDate(initial_data, final_date).map { it.toDTO() }
    }

    override fun existsPromotions(): Any? {
        return repository.checksExistsPromotions(LocalDate.now()) ?: "No exists promotion active in this date"
    }
}