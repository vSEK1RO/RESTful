package ro.sek1.RESTful.model.response.index

import ro.sek1.RESTful.model.Response
import ro.sek1.RESTful.model.maps.MappedSurveyItem

data class IndexGetVoteByIdResponse (
    val survey_item: MappedSurveyItem?
): Response("indexService")