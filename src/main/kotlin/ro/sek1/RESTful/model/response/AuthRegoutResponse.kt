package ro.sek1.RESTful.model.response

import ro.sek1.RESTful.model.Response

data class AuthRegoutResponse (
    var message: String,
): Response("authService")
