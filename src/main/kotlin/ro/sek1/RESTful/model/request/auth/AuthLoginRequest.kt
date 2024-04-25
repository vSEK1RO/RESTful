package ro.sek1.RESTful.model.request.auth

data class AuthLoginRequest (
    var username: String,
    var password: String,
)