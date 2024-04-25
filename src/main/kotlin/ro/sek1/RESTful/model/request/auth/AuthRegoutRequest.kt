package ro.sek1.RESTful.model.request.auth

data class AuthRegoutRequest (
    var username: String,
    var password: String,
)