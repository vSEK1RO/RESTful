package ro.sek1.RESTful.database.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.database.entity.SurveyItem
import java.util.Optional

@Repository
interface SurveyItemsDao : JpaRepository<SurveyItem, Long>{
    fun findAllBySurvey(survey: Survey): MutableList<SurveyItem>
}