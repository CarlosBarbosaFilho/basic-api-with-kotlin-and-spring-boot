package br.com.cbgomes.user.business

import br.com.cbgomes.user.UserRepository
import br.com.cbgomes.user.model.User
import br.com.cbgomes.user.model.UserDetailsImpl
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    fun createUser(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun getMe(): String? {
        return this.userRepository.findByEmail(getCurrentUserEmail())?.email
    }

    fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }
}