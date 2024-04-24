package ro.sek1.RESTful.model.request

data class AuthRegisterRequest (
    var username: String,
    var password: String,
    var email: String,
)