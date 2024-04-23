package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import ro.sek1.RESTful.model.enums.UserRole
import java.time.LocalDateTime

@Entity
@Table(name="users")
data class User (
    @Column(length = 20, nullable = false)
    var name: String = "",

    @Column(nullable = false)
    var passhash: String = "",

    @Column(nullable = false)
    var email: String = "",

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER,
) : UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    @ManyToMany
    val surveys_wh_voted: MutableList<Survey> = mutableListOf()

    @ManyToMany
    val surveyitems_wh_chosen: MutableList<SurveyItem> = mutableListOf()

    //UserDetails impl

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(role.name))
    }
    override fun getPassword(): String {
        return passhash
    }
    override fun getUsername(): String {
        return name
    }
    override fun isAccountNonExpired(): Boolean {
        return true
    }
    override fun isAccountNonLocked(): Boolean {
        return true
    }
    override fun isCredentialsNonExpired(): Boolean {
        return true
    }
    override fun isEnabled(): Boolean {
        return true
    }
}