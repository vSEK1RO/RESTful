package ro.sek1.RESTful.model.response

import ro.sek1.RESTful.model.Response

data class AuthRegisterResponse (
    var token: String,
    var message: String,
): Response("authService")