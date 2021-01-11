package br.com.cbgomes.security.filter

import br.com.cbgomes.security.jwt.JWTUtil
import br.com.cbgomes.user.model.Credentials
import br.com.cbgomes.user.model.UserDetailsImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(authenticationManager: AuthenticationManager, val jwtUtil : JWTUtil) : UsernamePasswordAuthenticationFilter(authenticationManager) {


    /**
     * Process the request with user credentials to authenticated on user details
     */
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication?{
        try {
            val (email, password) = ObjectMapper().readValue(request.inputStream, Credentials::class.java)
            val token = UsernamePasswordAuthenticationToken(email, password)
            return authenticationManager.authenticate(token)

        }catch (ex: Exception){
            throw UsernameNotFoundException("")
        }
    }

    /**
     * Generate the token and response in headers to user
     */

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = username?.let { jwtUtil.generateToken(it) }
        response.addHeader(authorization, "$bearer $token")
    }


}