package ro.sek1.RESTful.model.request.admin

import com.fasterxml.jackson.annotation.JsonProperty

data class AdminDeleteSurveyRequest(
    @JsonProperty("list_ids")
    var list_ids: MutableList<Long>
)