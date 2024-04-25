package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.model.Response
import ro.sek1.RESTful.model.maps.MappedSurvey

data class AdminGetSurveyResponse (
    var list: MutableList<MappedSurvey>
): Response("adminService")