package ro.sek1.RESTful.model.request.auth

data class AuthRegisterRequest (
    var username: String,
    var password: String,
    var email: String,
)