package ro.sek1.RESTful.model.request.admin

import ro.sek1.RESTful.database.entity.Survey
data class AdminPostSurveyRequest(
    var list: MutableList<Survey>
)