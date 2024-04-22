package ro.sek1.RESTful.model

import java.time.LocalDateTime

abstract class Response (
    var responder: String,
    var time: LocalDateTime = LocalDateTime.now(),
)