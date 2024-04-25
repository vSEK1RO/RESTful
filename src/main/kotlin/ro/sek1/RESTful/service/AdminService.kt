package ro.sek1.RESTful.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import ro.sek1.RESTful.database.dao.SurveyItemsDao
import ro.sek1.RESTful.database.dao.SurveysDao
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.database.entity.SurveyItem
import ro.sek1.RESTful.model.maps.MappedSurvey
import ro.sek1.RESTful.model.maps.MappedUser
import ro.sek1.RESTful.model.request.admin.*
import ro.sek1.RESTful.model.response.admin.*

@Service
class AdminService (
    var usersDao: UsersDao,
    var surveysDao: SurveysDao,
    var surveyItemsDao: SurveyItemsDao,
){
    fun getUser(page: Int, size: Int): AdminGetUserResponse {
        val pageable = PageRequest.of(page,size)
        val usersPage = usersDao.findAll(pageable)
        val users = usersPage.content
        return AdminGetUserResponse(
            list = users.map{
                MappedUser().fromEntity(it)
            }.toMutableList()
        )
    }
    fun getUserById(id: Long): AdminGetUserByIdResponse{
        return AdminGetUserByIdResponse(
            user = MappedUser().fromEntity(
                usersDao.findById(id).orElse(null)
                    ?: return AdminGetUserByIdResponse(
                        user = null
                    )
            )
        )
    }
    fun getSurvey(page: Int, size: Int): AdminGetSurveyResponse {
        val pageable = PageRequest.of(page,size)
        val surveysPage = surveysDao.findAll(pageable)
        val surveys = surveysPage.content
        return AdminGetSurveyResponse(
            list = surveys.map{
                MappedSurvey().fromEntity(it)
            }.toMutableList()
        )
    }
    fun getSurveyById(id: Long): AdminGetSurveyByIdResponse {
        return AdminGetSurveyByIdResponse(
            survey = MappedSurvey().fromEntity(
                surveysDao.findById(id).orElse(null)
                    ?: return AdminGetSurveyByIdResponse(
                        survey = null
                    )
            )
        )
    }
    fun postSurvey(request: AdminPostSurveyRequest): AdminPostSurveyResponse {
        if(request.list_surveys.size==0){
            return AdminPostSurveyResponse(
                message = "Nothing to add",
                surveys_ids = mutableListOf(),
            )
        }
        val surveys_ids = mutableListOf<Long>()
        request.list_surveys.forEach{
            val newSurvey = Survey(
                title = it.title,
                number_available = it.number_available,
                description = it.description,
                image_url = it.image_url,
                available_until = it.available_until,
            )
            surveysDao.save(newSurvey)
            it.items.forEach{
                surveyItemsDao.save(
                    SurveyItem(
                        survey = newSurvey,
                        title = it.title,
                        description = it.description
                    )
                )
            }
            val items = surveyItemsDao.findAllBySurvey(newSurvey)
            newSurvey.items = items.toMutableList()
            surveys_ids.add(newSurvey.id)
            surveysDao.save(newSurvey)
        }
        return AdminPostSurveyResponse(
            message = "PostSurvey successful",
            surveys_ids = surveys_ids,
        )
    }
    fun deleteSurvey(request: AdminDeleteSurveyRequest): AdminDeleteSurveyResponse {
        if(request.list_ids.size==0){
            return AdminDeleteSurveyResponse(
                message = "Nothing to delete",
                surveys_ids = mutableListOf(),
            )
        }
        val surveys_ids = mutableListOf<Long>()
        request.list_ids.forEach{
            var shouldExecuteFinally = true
            var existed = false
            try{
                existed = surveysDao.existsById(it)
                surveysDao.deleteById(it)
            }catch (e: Exception){
                shouldExecuteFinally = false
            }finally {
                if(shouldExecuteFinally && existed){
                    surveys_ids.add(it)
                }
            }
        }
        return AdminDeleteSurveyResponse(
            message = "DeleteSurvey successful",
            surveys_ids = surveys_ids,
        )
    }
}