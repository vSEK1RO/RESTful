package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="surveys")
data class Survey (
    @Column(length = 50, nullable = false)
    var title: String = "",

    @Column(nullable = false)
    var number_avaible: Long = 0,

    @OneToMany
    @JoinColumn(name = "survey_id")
    val items: MutableList<SurveyItem> = mutableListOf(),

    //nullable

    @Column(columnDefinition = "TEXT")
    var description: String? = "",

    @Column(columnDefinition = "TEXT")
    var image_url: String? = null,

    @Column(columnDefinition = "timestamp")
    var avaible_until: LocalDateTime? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    @ManyToMany(mappedBy = "surveys_wh_voted")
    val users_wh_voted: MutableList<User> = mutableListOf()
}