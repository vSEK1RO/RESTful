package ro.sek1.RESTful.model.request

data class AuthLoginRequest (
    var username: String,
    var password: String,
)