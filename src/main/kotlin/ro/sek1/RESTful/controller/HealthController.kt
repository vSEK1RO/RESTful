package ro.sek1.RESTful.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ro.sek1.RESTful.service.HealthService

@RestController
@RequestMapping("/HealthService")
class HealthController (
    var healthService: HealthService,
){
    @GetMapping("")
    fun getHealth() = healthService.getHealth()
}