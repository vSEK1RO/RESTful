package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tokens")
class Token (
    @Column(nullable = false)
    var hash: String = "",

    @Column(nullable = false)
    var loggedOut: Boolean = false,

    //nullable

    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    override fun equals(other: Any?): Boolean {
        if(other !is Token)return false
        return other.id == id
    }
}