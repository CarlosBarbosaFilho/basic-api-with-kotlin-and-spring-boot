package br.com.cbgomes.user.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

/**
 * Associate the user application with UserDetails of Spring Security
 */

class UserDetailsImpl(private val user: User): UserDetails {

    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword() = this.user.password

    override fun getUsername() = this.user.email

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

}