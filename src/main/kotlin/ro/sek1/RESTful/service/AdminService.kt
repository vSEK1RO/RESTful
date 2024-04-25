package ro.sek1.RESTful.service

import org.springframework.stereotype.Service
import ro.sek1.RESTful.database.dao.SurveyItemsDao
import ro.sek1.RESTful.database.dao.SurveysDao
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.model.request.admin.*
import ro.sek1.RESTful.model.response.admin.*

@Service
class AdminService (
    var usersDao: UsersDao,
    var surveysDao: SurveysDao,
    var surveyItemsDao: SurveyItemsDao,
){
    fun getUser(): AdminGetUserResponse {
        return TODO()
    }
    fun getUserById(id: Long): AdminGetUserByIdResponse{
        return TODO()
    }
    fun getSurvey(): AdminGetSurveyResponse {
        return TODO()
    }
    fun getSurveyById(id: Long): AdminGetSurveyByIdResponse {
        return TODO()
    }
    fun postSurvey(request: AdminPostSurveyRequest): AdminPostSurveyResponse {
        return TODO()
    }
    fun deleteSurvey(request: AdminDeleteSurveyRequest): AdminDeleteSurveyResponse {
        return TODO()
    }
}