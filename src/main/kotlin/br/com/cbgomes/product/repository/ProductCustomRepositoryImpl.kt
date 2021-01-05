package br.com.cbgomes.product.repository

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

class ProductCustomRepositoryImpl {

    @PersistenceContext
    private lateinit var entityManager: EntityManager
}