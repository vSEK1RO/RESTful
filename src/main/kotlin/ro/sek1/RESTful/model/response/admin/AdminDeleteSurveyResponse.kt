package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.model.Response

data class AdminDeleteSurveyResponse (
    var message: String,
    var surveys_ids: MutableList<Long>,
): Response("adminService")