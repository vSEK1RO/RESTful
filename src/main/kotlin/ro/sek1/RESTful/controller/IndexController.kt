package ro.sek1.RESTful.controller

import io.micrometer.observation.annotation.Observed
import org.springframework.web.bind.annotation.*
import ro.sek1.RESTful.model.request.index.IndexDeleteVoteRequest
import ro.sek1.RESTful.model.request.index.IndexPostVoteRequest
import ro.sek1.RESTful.service.AdminService
import ro.sek1.RESTful.service.IndexService

@RestController
@RequestMapping("/index")
@Observed(name = "IndexController")
class IndexController (
    var indexService: IndexService,
    var adminService: AdminService,
){
    @GetMapping("/survey")
    fun getSurvey(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ) = adminService.getSurvey(page, size)
    @GetMapping("/survey/{id}")
    fun getSurveyById(
        @PathVariable("id") id: Long
    ) = adminService.getSurveyById(id)
    @GetMapping("/vote")
    fun getVote(
        @RequestHeader("Authorization") token: String,
    ) = indexService.getVote(token)
    @GetMapping("/vote/{id}")
    fun getVote(
        @PathVariable("id") id: Long,
        @RequestHeader("Authorization") token: String,
    ) = indexService.getVoteById(id, token)
    @PostMapping("/vote")
    fun postVote(
        @RequestBody request: IndexPostVoteRequest,
        @RequestHeader("Authorization") token: String,
    ) = indexService.postVote(request, token)
    @DeleteMapping("/vote")
    fun deleteVote(
        @RequestBody request: IndexDeleteVoteRequest,
        @RequestHeader("Authorization") token: String,
    ) = indexService.deleteVote(request, token)
}