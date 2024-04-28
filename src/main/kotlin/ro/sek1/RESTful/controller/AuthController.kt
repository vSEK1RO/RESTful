package ro.sek1.RESTful.controller

import io.micrometer.observation.annotation.Observed
import org.springframework.web.bind.annotation.*
import ro.sek1.RESTful.model.request.auth.AuthLoginRequest
import ro.sek1.RESTful.model.request.auth.AuthRegisterRequest
import ro.sek1.RESTful.model.request.auth.AuthRegoutRequest
import ro.sek1.RESTful.service.AuthService

@RestController
@RequestMapping("/auth")
@Observed(name = "AuthController")
class AuthController (
    var authService: AuthService,
){
    @PostMapping("/register")
    fun register(
        @RequestBody request: AuthRegisterRequest
    ) = authService.register(request)
    @PostMapping("/regout")
    fun register(
        @RequestBody request: AuthRegoutRequest
    ) = authService.regout(request)
    @PostMapping("/login")
    fun login(
        @RequestBody request: AuthLoginRequest
    ) = authService.login(request)
    @GetMapping("/logout")
    fun logout(
        @RequestHeader("Authorization") token: String
    ) = authService.logout(token)
}