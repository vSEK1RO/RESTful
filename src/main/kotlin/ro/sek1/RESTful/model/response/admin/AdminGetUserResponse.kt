package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.Response

data class AdminGetUserResponse(
    var list: MutableList<User>
): Response("adminService")
