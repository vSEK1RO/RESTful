package ro.sek1.RESTful.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ro.sek1.RESTful.model.request.AuthLoginRequest
import ro.sek1.RESTful.model.request.AuthRegisterRequest
import ro.sek1.RESTful.model.request.AuthRegoutRequest
import ro.sek1.RESTful.service.AuthService

@RestController
@RequestMapping("/auth")
class AuthController (
    var authService: AuthService,
){
    @PostMapping("/register")
    fun authRegister(
        @RequestBody request: AuthRegisterRequest
    ) = authService.authRegister(request)
    @PostMapping("/regout")
    fun authRegister(
        @RequestBody request: AuthRegoutRequest
    ) = authService.authRegout(request)
    @PostMapping("/login")
    fun authRegister(
        @RequestBody request: AuthLoginRequest
    ) = authService.authLogin(request)
    @GetMapping("/logout")
    fun authRegister(
        @RequestHeader("Authorization") token: String
    ) = authService.authLogout(token)
}