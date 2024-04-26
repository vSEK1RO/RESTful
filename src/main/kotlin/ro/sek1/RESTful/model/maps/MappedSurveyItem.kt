package ro.sek1.RESTful.model.maps

import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.database.entity.SurveyItem

class MappedSurveyItem {
    var title: String = ""
    var description: String? = null
    var id: Long = 0
    fun fromEntity(surveyItem: SurveyItem): MappedSurveyItem{
        title = surveyItem.title
        description = surveyItem.description
        id = surveyItem.id
        return this
    }
    fun toEntity(survey: Survey): SurveyItem{
        return SurveyItem(
            survey = survey,
            title = title,
            description = description,
        )
    }
}