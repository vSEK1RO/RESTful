package ro.sek1.RESTful.service

import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import ro.sek1.RESTful.config.JwtService
import ro.sek1.RESTful.database.dao.SurveyItemsDao
import ro.sek1.RESTful.database.dao.SurveysDao
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.maps.MappedSurvey
import ro.sek1.RESTful.model.maps.MappedSurveyItem
import ro.sek1.RESTful.model.request.index.*
import ro.sek1.RESTful.model.response.index.*

@Service
class IndexService (
    var usersDao: UsersDao,
    var surveysDao: SurveysDao,
    var surveyItemsDao: SurveyItemsDao,
    var jwtService: JwtService,
){
    fun getVote(
        token: String,
    ): IndexGetVoteResponse {
        val user = usersDao.findByName(
            jwtService.extractUsername(token.substring(7))
        ).orElse(null)
            ?: return IndexGetVoteResponse(mutableListOf(),mutableListOf())
        val surveys = user.surveys_wh_voted
        val items = user.surveyitems_wh_chosen
        return IndexGetVoteResponse(
            surveys_wh_voted = surveys.map{
                MappedSurvey().fromEntity(it)
            }.toMutableList(),
            surveyitems_wh_chosen = items.map{
                MappedSurveyItem().fromEntity(it)
            }.toMutableList(),
        )
    }
    fun getVoteById(id: Long, token: String): IndexGetVoteByIdResponse {
        val user = usersDao.findByName(
            jwtService.extractUsername(token.substring(7))
        ).orElse(null)
            ?: return IndexGetVoteByIdResponse(null)
        val item = surveyItemsDao.findById(id).orElse(null)
            ?: return IndexGetVoteByIdResponse(null)
        if(!user.surveyitems_wh_chosen.contains(item)){
            return IndexGetVoteByIdResponse(null)
        }
        return IndexGetVoteByIdResponse(MappedSurveyItem().fromEntity(item))
    }
    fun postVote(request: IndexPostVoteRequest, token: String): IndexPostVoteResponse{
        val user = usersDao.findByName(
            jwtService.extractUsername(token.substring(7))
        ).orElse(null)
            ?: return IndexPostVoteResponse(
                message = "Username doesn't exist",
                ids = mutableListOf()
            )
        val items = surveyItemsDao.findAllById(request.list_ids)
        val ids = mutableListOf<Long>()
        items.forEach{
            if(!user.surveyitems_wh_chosen.contains(it)){
                user.surveyitems_wh_chosen.add(it)
                it.users_wh_chosen.add(user)
                ids.add(it.id)
            }
            if(!user.surveys_wh_voted.contains(it.survey)){
                user.surveys_wh_voted.add(it.survey)
                it.survey.users_wh_voted.add(user)
            }
        }
        usersDao.save(user)
        surveyItemsDao.saveAll(items)
        surveysDao.saveAll(items.map{
            it.survey
        })
        return IndexPostVoteResponse(
            message = "IndexPostVote successful",
            ids = ids,
        )
    }
    fun deleteVote(request: IndexDeleteVoteRequest, token: String): IndexDeleteVoteResponse{
        val user = usersDao.findByName(
            jwtService.extractUsername(token.substring(7))
        ).orElse(null)
            ?: return IndexDeleteVoteResponse(
                message = "Username doesn't exist",
                ids = mutableListOf()
            )
        val items = surveyItemsDao.findAllById(request.list_ids)
        val ids = mutableListOf<Long>()
        items.forEach{
            if(user.surveyitems_wh_chosen.contains(it)){
                user.surveyitems_wh_chosen.remove(it)
                it.users_wh_chosen.remove(user)
                ids.add(it.id)
            }
            if(user.surveys_wh_voted.contains(it.survey)){
                user.surveys_wh_voted.remove(it.survey)
                it.survey.users_wh_voted.remove(user)
            }
        }
        usersDao.save(user)
        surveyItemsDao.saveAll(items)
        surveysDao.saveAll(items.map{
            it.survey
        })
        return IndexDeleteVoteResponse(
            message = "IndexDeleteVote successful",
            ids = ids,
        )
    }
}