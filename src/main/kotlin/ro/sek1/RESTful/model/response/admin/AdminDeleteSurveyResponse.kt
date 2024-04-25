package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.model.Response

data class AdminDeleteSurveyResponse (
    var message: String,
    var survey: Survey,
): Response("adminService")