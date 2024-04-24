package ro.sek1.RESTful.database.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ro.sek1.RESTful.database.entity.SurveyItem

@Repository
interface SurveyItemsDao : JpaRepository<SurveyItem, Long>{
}