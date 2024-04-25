package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.model.Response

data class AdminGetSurveyResponse (
    var list: MutableList<Survey>
): Response("adminService")