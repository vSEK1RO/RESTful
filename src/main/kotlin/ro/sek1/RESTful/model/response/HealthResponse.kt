package ro.sek1.RESTful.model.response

import ro.sek1.RESTful.model.Response

data class HealthResponse (
    var message: String
) : Response("healthService")