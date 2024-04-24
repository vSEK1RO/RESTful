package ro.sek1.RESTful.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestHeader
import ro.sek1.RESTful.config.JwtService
import ro.sek1.RESTful.database.dao.TokenDao
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.database.entity.Token
import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.enums.UserRole
import ro.sek1.RESTful.model.request.AuthLoginRequest
import ro.sek1.RESTful.model.request.AuthRegisterRequest
import ro.sek1.RESTful.model.request.AuthRegoutRequest
import ro.sek1.RESTful.model.response.AuthLoginResponse
import ro.sek1.RESTful.model.response.AuthLogoutResponse
import ro.sek1.RESTful.model.response.AuthRegisterResponse
import ro.sek1.RESTful.model.response.AuthRegoutResponse

@Service
class AuthService (
    var usersDao: UsersDao,
    var tokenDao: TokenDao,
    var passwordEncoder: PasswordEncoder,
    var jwtService: JwtService,
    var authenticationManager: AuthenticationManager,
){
    fun authRegister(request: AuthRegisterRequest): AuthRegisterResponse {
        if(!usersDao.findByName(request.username).isEmpty){
            return AuthRegisterResponse(
                token = "nan",
                message = "Username already exists",
            )
        }
        if(!usersDao.findByEmail(request.email).isEmpty){
            return AuthRegisterResponse(
                token = "nan",
                message = "Email already exists",
            )
        }
        if(!isEmailValid(request.email)){
            return AuthRegisterResponse(
                token = "nan",
                message = "Email invalid",
            )
        }
        val user = User(
            name = request.username,
            passhash = passwordEncoder.encode(request.password),
            email = request.email,
            role = UserRole.USER,
        )
        usersDao.save(user)
        val token = Token(
            token = jwtService.generateToken(user),
            loggedOut = false,
            user = user,
        )
        tokenDao.save(token)
        return AuthRegisterResponse(
            token = token.token,
            message = "Register successful",
        )
    }
    fun authRegout(request: AuthRegoutRequest): AuthRegoutResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password,
            )
        )
        val user = usersDao.findByName(request.username).orElse(null)
            ?: return AuthRegoutResponse(
                message = "User doesn't exist"
            )
        deleteAllTokensByUser(user)
        usersDao.delete(user)
        return AuthRegoutResponse(
            message = "Regout successful"
        )
    }
    fun authLogin(request: AuthLoginRequest): AuthLoginResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password,
            )
        )
        val user = usersDao.findByName(request.username).orElse(null)
            ?: return AuthLoginResponse(
                token = "nan",
                message = "User doesn't exist",
            )
        revokeAllTokensByUser(user)
        val token = Token(
            token = jwtService.generateToken(user),
            loggedOut = false,
            user = user,
        )
        tokenDao.save(token)
        return AuthLoginResponse(
            token = token.token,
            message = "Login successful",
        )
    }
    fun authLogout(token: String): AuthLogoutResponse {
        val currentToken = tokenDao.findByToken(
            token.substring(7)
        ).orElse(null)
            ?: return AuthLogoutResponse(
                message = "Token not found"
            )
        currentToken.loggedOut = true
        tokenDao.save(currentToken)
        return AuthLogoutResponse(message = "Logout successful")
    }
    fun revokeAllTokensByUser(user: User){
        val validTokens: MutableList<Token> = tokenDao.findAllByUser(user)
        if(validTokens.isEmpty()){
            return
        }
        validTokens.forEach{
            it.loggedOut = true
        }
        tokenDao.saveAll(validTokens)
    }
    fun deleteAllTokensByUser(user: User){
        val validTokens: MutableList<Token> = tokenDao.findAllByUser(user)
        validTokens.forEach{
            tokenDao.delete(it)
        }
    }
    fun isEmailValid(email: String): Boolean{
        if(email.count{ it == '@' }!=1)return false
        if(email.startsWith('@'))return false
        if(email.endsWith('@'))return false
        return true
    }
}