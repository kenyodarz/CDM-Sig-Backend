package com.cdm.sig.services.components

import com.cdm.sig.models.User
import com.cdm.sig.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    val userRepository: UserRepository? = null

    @Transactional
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails? {
        val user: User = userRepository!!.findByUsername(username)
                .orElseThrow { UsernameNotFoundException("No se ha encontrado el Usuario: $username") }
        return UserDetailsImpl.build(user)
    }
}