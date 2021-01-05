package br.com.cbgomes.promotion.model.entity

import br.com.cbgomes.product.model.entity.Product
import com.fasterxml.jackson.annotation.JsonManagedReference
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "tb_promotions")
data class Promotion(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val initial_date: LocalDate? = null,
        val final_date: LocalDate? = null,
        @JsonManagedReference
        @OneToMany(mappedBy = "promotion", cascade = arrayOf(CascadeType.ALL), fetch = FetchType.EAGER)
        val productsInPromotion: List<Product>? = emptyList(),
)