package ro.sek1.RESTful.database.dao

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.database.entity.User

@Repository
interface SurveysDao : JpaRepository<Survey, Long> {
    override fun findAll(pageable: Pageable): Page<Survey>
}