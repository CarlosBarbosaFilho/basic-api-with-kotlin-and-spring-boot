package br.com.cbgomes.adviced.model

import org.springframework.http.HttpStatus

data class ApiErrorCustomHandler(
        val status: HttpStatus,
        val message: String,
        val errors: String?,
)
