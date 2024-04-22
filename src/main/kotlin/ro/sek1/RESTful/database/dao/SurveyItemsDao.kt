package ro.sek1.RESTful.database.dao

import org.springframework.data.jpa.repository.JpaRepository
import ro.sek1.RESTful.database.entity.SurveyItem

interface SurveyItemsDao : JpaRepository<SurveyItem, Long>{
}