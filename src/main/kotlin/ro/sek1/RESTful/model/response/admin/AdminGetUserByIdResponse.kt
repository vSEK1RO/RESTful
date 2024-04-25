package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.Response

data class AdminGetUserByIdResponse(
    var user: User?
): Response("adminService")
