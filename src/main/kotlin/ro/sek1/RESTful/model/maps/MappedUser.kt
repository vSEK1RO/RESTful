package ro.sek1.RESTful.model.maps

import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.enums.UserRole

class MappedUser {
    var name: String = ""
    var email: String = ""
    var role: UserRole = UserRole.USER
    var id: Long = 0
    fun fromEntity(user: User): MappedUser{
        name = user.name
        email = user.email
        role = user.role
        id = user.id
        return this
    }
    fun toEntity(): User{
        return User(
            name = name,
            email = email,
            role = role,
        )
    }
}