package ro.sek1.RESTful.controller

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import ro.sek1.RESTful.model.request.admin.AdminDeleteSurveyRequest
import ro.sek1.RESTful.model.request.admin.AdminPostSurveyRequest
import ro.sek1.RESTful.service.AdminService

@RestController
@RequestMapping("/admin")
class AdminController(
    var adminService: AdminService,
){
    @GetMapping("/user")
    fun getUser() = adminService.getUser()
    @GetMapping("/user/{id}")
    fun getUserById(
        @PathVariable(value = "id") id: Long
    ) = adminService.getUserById(id)
    @GetMapping("/survey")
    fun getSurvey() = adminService.getSurvey()
    @GetMapping("/survey/{id}")
    fun getSurveyById(
        @PathVariable(value = "id") id: Long
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