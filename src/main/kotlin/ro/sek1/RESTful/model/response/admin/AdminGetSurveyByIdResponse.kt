package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.model.Response

data class AdminGetSurveyByIdResponse (
    var survey: Survey
): Response("adminService")