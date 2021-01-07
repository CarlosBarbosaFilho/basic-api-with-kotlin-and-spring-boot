package br.com.cbgomes.promotion.service

import br.com.cbgomes.promotion.exceptions.DateInvalidToCreatePromotion
import br.com.cbgomes.promotion.exceptions.PromotionNotFoundException
import br.com.cbgomes.promotion.model.dto.PromotionDto
import br.com.cbgomes.promotion.model.dto.toDTO
import br.com.cbgomes.promotion.model.form.PromotionForm
import br.com.cbgomes.promotion.model.form.toEntity
import br.com.cbgomes.promotion.repository.PromotionRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Service
class PromotionServiceImpl(val repository: PromotionRepository) : PromotionService {

    @Transactional
    override fun save(form: PromotionForm): PromotionDto {
        if (validateDateCreatePromotion(form)){
            return this.repository.save(form.toEntity()).toDTO()
        }else {
            throw DateInvalidToCreatePromotion(form.initial_date, form.final_date)
        }
    }

    override fun getAllPromotions(): List<PromotionDto> {
        return this.repository.findAll().map { it.toDTO() }
    }

    override fun getByIdPromotion(id: Long): PromotionDto {
        return try {
            this.repository.findById(id).get().toDTO()
        }catch (ex: NoSuchElementException){
            throw PromotionNotFoundException(id)
        }
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

    private fun validateDateCreatePromotion(form: PromotionForm): Boolean {
        val beginDate = LocalDate.parse(form.initial_date.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        val endDate = LocalDate.parse(form.final_date.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))

        return !(beginDate.isBefore(LocalDate.now()) && endDate.isBefore(LocalDate.now()))

    }
}