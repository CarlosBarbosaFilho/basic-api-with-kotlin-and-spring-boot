package br.com.cbgomes.promotion.repository

import br.com.cbgomes.promotion.model.entity.Promotion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface PromotionRepository : JpaRepository<Promotion, Long> {

    @Query(" FROM Promotion p WHERE p.initial_date = :initial_date AND p.final_date = :final_date ")
    fun getPromotionByDate(@Param("initial_date") initial_date: LocalDate,
                           @Param("final_date") final_date: LocalDate): List<Promotion>

    @Query("FROM Promotion p WHERE :date BETWEEN p.initial_date AND p.final_date")
    fun checksExistsPromotions(@Param("date") date: LocalDate ): List<Promotion>?
}