package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="surveys")
data class Survey (
    @Column(length = 50, nullable = false)
    var title: String = "",

    @Column(nullable = false)
    var number_available: Long = 0,

    //nullable

    @Column(columnDefinition = "TEXT")
    var description: String? = "",

    @Column(columnDefinition = "TEXT")
    var image_url: String? = null,

    @Column(columnDefinition = "timestamp")
    var available_until: LocalDateTime? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    @OneToMany
    @JoinColumn(name = "survey_id")
    var items: MutableList<SurveyItem> = mutableListOf()

    @ManyToMany(mappedBy = "surveys_wh_voted")
    var users_wh_voted: MutableList<User> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if(other !is Survey)return false
        return other.id == id
    }
}