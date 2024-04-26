package ro.sek1.RESTful.model.response.index

import ro.sek1.RESTful.model.Response
import ro.sek1.RESTful.model.maps.MappedSurvey
import ro.sek1.RESTful.model.maps.MappedSurveyItem

data class IndexGetVoteResponse (
    val surveys_wh_voted: MutableList<MappedSurvey>,
    val surveyitems_wh_chosen: MutableList<MappedSurveyItem>,
): Response("indexService")