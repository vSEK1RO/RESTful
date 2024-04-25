package ro.sek1.RESTful.database

import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.enums.UserRole

@Component
class TestUser (
    var usersDao: UsersDao,
    var passwordEncoder: PasswordEncoder,
){
    @PostConstruct
    fun registerAdmin(){
        usersDao.save(
            User(
                name = "root",
                passhash = passwordEncoder.encode("root"),
                role = UserRole.ADMIN,
            )
        )
    }
}