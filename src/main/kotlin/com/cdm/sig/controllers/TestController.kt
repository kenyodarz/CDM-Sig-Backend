package com.cdm.sig.controllers

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RequestMapping("/api")
@RestController
class TestController {
    @GetMapping("/test/all")
    fun allAccess(): String? {
        return "Public Content."
    }

    @GetMapping("/test/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    fun userAccess(): String? {
        return "User Content."
    }

    @GetMapping("/test/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    fun moderatorAccess(): String? {
        return "Moderator Board."
    }

    @GetMapping("/test/admin")
    @PreAuthorize("hasRole('ADMIN')")
    fun adminAccess(): String? {
        return "Admin Board."
    }
}