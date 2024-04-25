package ro.sek1.RESTful.model.response.admin

import ro.sek1.RESTful.model.Response
import ro.sek1.RESTful.model.maps.MappedUser

data class AdminGetUserResponse(
    var list: MutableList<MappedUser>
): Response("adminService")
