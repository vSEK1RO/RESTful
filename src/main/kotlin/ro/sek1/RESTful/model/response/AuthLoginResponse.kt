package ro.sek1.RESTful.model.response

import ro.sek1.RESTful.model.Response

data class AuthLoginResponse (
    var token: String,
    var message: String,
): Response("authService")