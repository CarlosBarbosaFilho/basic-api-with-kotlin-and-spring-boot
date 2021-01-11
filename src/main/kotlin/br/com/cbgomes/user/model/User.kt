package br.com.cbgomes.user.model

import javax.persistence.*

@Entity
@Table(name = "tb_users")
data class User(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0,
        val fullName: String = "",
        val email: String = "",
        var password: String = ""
)