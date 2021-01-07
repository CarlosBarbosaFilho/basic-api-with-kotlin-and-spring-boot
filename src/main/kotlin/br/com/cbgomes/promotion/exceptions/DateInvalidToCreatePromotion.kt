package br.com.cbgomes.promotion.exceptions

import java.time.LocalDate

data class DateInvalidToCreatePromotion(val dataBegin: LocalDate, val endDate: LocalDate): RuntimeException()