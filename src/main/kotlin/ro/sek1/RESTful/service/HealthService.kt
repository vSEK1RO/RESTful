package ro.sek1.RESTful.service

import org.springframework.stereotype.Service
import ro.sek1.RESTful.model.response.health.HealthResponse

@Service
class HealthService {
    fun getHealth(): HealthResponse {
        return HealthResponse(message = "healthy")
    }
}