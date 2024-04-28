package ro.sek1.RESTful.database

import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import ro.sek1.RESTful.config.sha256
import ro.sek1.RESTful.database.dao.SurveysDao
import ro.sek1.RESTful.database.dao.UsersDao
import ro.sek1.RESTful.database.entity.Survey
import ro.sek1.RESTful.database.entity.User
import ro.sek1.RESTful.model.enums.UserRole
import java.time.LocalDateTime

@Component
class TestUser (
    var usersDao: UsersDao,
    var surveysDao: SurveysDao,
    var passwordEncoder: PasswordEncoder,
){
    @PostConstruct
    fun registerAdmin(){
        if(!usersDao.existsByName("root")){
            usersDao.save(
                User(
                    name = "root",
                    passhash = passwordEncoder.encode("root"),
                    role = UserRole.ADMIN,
                )
            )
        }
    }

    @PostConstruct
    fun registerUsers(){
        val users = mutableListOf<User>()
        val surveys = mutableListOf<Survey>()
        for(i in 1..20){
            users.add(
                User(
                    name = LocalDateTime.now().toString().sha256().slice(0..10),
                    passhash = passwordEncoder.encode("test"),
                    role = UserRole.USER,
                )
            )
            surveys.add(
                Survey(
                    title = LocalDateTime.now().toString().sha256().slice(0..10),
                )
            )
        }
        usersDao.saveAll(users)
        surveysDao.saveAll(surveys)
    }
}