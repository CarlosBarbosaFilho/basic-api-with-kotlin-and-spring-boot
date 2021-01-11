package br.com.cbgomes.user

import br.com.cbgomes.user.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: JpaRepository<User,Long> {
    fun findByEmail(email:String?): User?
}