package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.model.Response
import ro.sek1.RESTful.model.maps.MappedUser

data class AdminGetUserByIdResponse(
    var user: MappedUser?
): Response("adminService")
