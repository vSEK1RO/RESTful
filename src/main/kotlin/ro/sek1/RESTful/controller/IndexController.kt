package ro.sek1.RESTful.controller

import org.springframework.web.bind.annotation.*
import ro.sek1.RESTful.service.AdminService
import ro.sek1.RESTful.service.IndexService

@RestController
@RequestMapping("")
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
}