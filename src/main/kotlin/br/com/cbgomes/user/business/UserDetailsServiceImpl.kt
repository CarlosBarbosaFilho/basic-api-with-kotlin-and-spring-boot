package br.com.cbgomes.user.business

import br.com.cbgomes.user.UserRepository
import br.com.cbgomes.user.model.UserDetailsImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(val userRepository: UserRepository): UserDetailsService {

    override fun loadUserByUsername(email: String?): UserDetails {
            val user = this.userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return UserDetailsImpl(user)
    }
}