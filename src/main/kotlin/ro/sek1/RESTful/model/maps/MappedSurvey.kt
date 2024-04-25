package ro.sek1.RESTful.model.maps

import ro.sek1.RESTful.database.entity.Survey
import java.time.LocalDateTime

class MappedSurvey {
    var title: String = ""
    var number_available: Long = 0
    var description: String? = null
    var image_url: String? = null
    var available_until: LocalDateTime? = null
    var items: MutableList<MappedSurveyItem> = mutableListOf()
    var id: Long = 0
    fun fromEntity(survey: Survey): MappedSurvey{
        title = survey.title
        number_available = survey.number_available
        description = survey.description
        image_url = survey.image_url
        available_until = survey.available_until
        id = survey.id
        items = survey.items.map{
            MappedSurveyItem().fromEntity(it)
        }.toMutableList()
        return this
    }
    fun toEntity(): Survey{
        val survey = Survey(
            title = title,
            number_available = number_available,
            description = description,
            image_url = image_url,
            available_until = available_until,
        )
        survey.items = items.map{
            it.toEntity(survey)
        }.toMutableList()
        return survey
    }
}