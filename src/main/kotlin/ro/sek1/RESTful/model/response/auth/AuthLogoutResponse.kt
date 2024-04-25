package ro.sek1.RESTful.model.response.auth

import ro.sek1.RESTful.model.Response

data class AuthLogoutResponse (
    var message: String,
): Response("authService")