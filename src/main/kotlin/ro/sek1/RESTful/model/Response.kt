package ro.sek1.RESTful.model

import org.springframework.http.HttpStatus
import java.util.Date

open class Response (
    var responder: String,
    var time: Date,
    var httpStatus: HttpStatus,
)