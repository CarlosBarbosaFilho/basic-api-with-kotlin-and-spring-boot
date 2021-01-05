package br.com.cbgomes.product.model.entity

import br.com.cbgomes.promotion.model.entity.Promotion
import com.fasterxml.jackson.annotation.JsonBackReference
import java.math.BigDecimal
import javax.persistence.*

@Entity
@Table(name = "tb_products")
data class Product(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = 0,
        val stock: Int? = null,
        val price: BigDecimal? = null,
        var price_promotion: BigDecimal? = null,
        val category: String? = null,
        val code: Int? = null,
        @JsonBackReference
        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "promotion_id")
        var promotion: Promotion? = null
)
