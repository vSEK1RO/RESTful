package ro.sek1.RESTful.model.request.admin

import com.fasterxml.jackson.annotation.JsonProperty
import ro.sek1.RESTful.model.maps.MappedSurvey

data class AdminPostSurveyRequest(
    @JsonProperty("list_surveys")
    var list_surveys: MutableList<MappedSurvey>
)