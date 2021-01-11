package br.com.cbgomes.security.config

import br.com.cbgomes.security.filter.JWTAuthenticationFilter
import br.com.cbgomes.security.filter.JWTAuthorizationFilter
import br.com.cbgomes.security.jwt.JWTUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    override fun configure(web: WebSecurity?) {
        web!!.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }

    override fun configure(http: HttpSecurity) {
       http.csrf().disable().authorizeRequests()
               .antMatchers(HttpMethod.POST, "/api/v1/users/create-user").permitAll()
               .antMatchers(HttpMethod.GET, "/api/vi/me").permitAll()
               .antMatchers(HttpMethod.GET, "/swagger-ui.html#/").permitAll()
               .anyRequest().authenticated()

        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil = jwtUtil))
        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil = jwtUtil, userDetailService = userDetailsService))
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

}