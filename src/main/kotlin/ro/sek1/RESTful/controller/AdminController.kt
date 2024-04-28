package ro.sek1.RESTful.controller

import io.micrometer.observation.annotation.Observed
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ro.sek1.RESTful.model.request.admin.AdminDeleteSurveyRequest
import ro.sek1.RESTful.model.request.admin.AdminPostSurveyRequest
import ro.sek1.RESTful.service.AdminService

@RestController
@RequestMapping("/admin")
@Observed(name = "AdminController")
class AdminController(
    var adminService: AdminService,
){
    @GetMapping("/user")
    fun getUser(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ) = adminService.getUser(page, size)
    @GetMapping("/user/{id}")
    fun getUserById(
        @PathVariable("id") id: Long
    ) = adminService.getUserById(id)
    @GetMapping("/survey")
    fun getSurvey(
        @RequestParam("page") page: Int = 0,
        @RequestParam("size") size: Int = 10,
    ) = adminService.getSurvey(page, size)
    @GetMapping("/survey/{id}")
    fun getSurveyById(
        @PathVariable("id") id: Long
    ) = adminService.getSurveyById(id)
    @PostMapping("/survey")
    fun getUser(
        @RequestBody request: AdminPostSurveyRequest
    ) = adminService.postSurvey(request)
    @DeleteMapping("/survey")
    fun getUser(
        @RequestBody request: AdminDeleteSurveyRequest
    ) = adminService.deleteSurvey(request)
}