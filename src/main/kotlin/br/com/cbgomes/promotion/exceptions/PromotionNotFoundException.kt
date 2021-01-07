package br.com.cbgomes.promotion.exceptions


data class PromotionNotFoundException(val id: Long): RuntimeException()
