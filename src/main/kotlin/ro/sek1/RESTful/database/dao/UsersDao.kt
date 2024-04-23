package ro.sek1.RESTful.database.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ro.sek1.RESTful.database.entity.User
import java.util.Optional

@Repository
interface UsersDao : JpaRepository<User, Long> {
    fun findByName(username: String): Optional<User>
}