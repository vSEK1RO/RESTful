package ro.sek1.RESTful.database.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ro.sek1.RESTful.database.entity.Token
import ro.sek1.RESTful.database.entity.User
import java.util.Optional

@Repository
interface TokenDao: JpaRepository<Token, Long> {
    fun findByToken(token: String): Optional<Token>
    fun findAllByUser(user: User): MutableList<Token>
}