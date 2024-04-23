package ro.sek1.RESTful.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ro.sek1.RESTful.database.dao.UsersDao

@Service
class UserDetailsService : UserDetailsService {
    @Autowired
    lateinit var usersDao: UsersDao
    override fun loadUserByUsername(username: String?): UserDetails {
        return usersDao.findByName(username?:"").orElseThrow{
            UsernameNotFoundException("User ${username} not found")
        }
    }

}