package br.com.cbgomes.product.exception

data class ProductNotFoundException (val id: Long): RuntimeException()
