package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="surveys")
data class Survey (
    @Column(length = 50, nullable = false)
    val title: String = "",

    @Column(nullable = false)
    val number_avaible: Long = 0,

    @OneToMany
    @JoinColumn(name = "survey_id")
    val items: MutableList<SurveyItem> = mutableListOf(),

    //nullable

    @Column(columnDefinition = "TEXT")
    val description: String? = "",

    @Column(columnDefinition = "TEXT")
    val image_url: String? = null,

    @Column(columnDefinition = "timestamp")
    val avaible_until: LocalDateTime? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    @ManyToMany(mappedBy = "surveys_wh_voted")
    val users_wh_voted: MutableList<User> = mutableListOf()
}