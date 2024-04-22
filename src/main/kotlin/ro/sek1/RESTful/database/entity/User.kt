package ro.sek1.RESTful.database.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="users")
data class User (
    @Column(length = 20, nullable = false)
    val name: String = "",

    @Column(nullable = false)
    val pass_hash: String = "",

    @Column(nullable = false)
    val email: String = "",
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @Column(columnDefinition = "timestamp", nullable = false)
    val created_at: LocalDateTime = LocalDateTime.now()

    @ManyToMany
    val surveys_wh_voted: MutableList<Survey> = mutableListOf()

    @ManyToMany
    val surveyitems_wh_chosen: MutableList<SurveyItem> = mutableListOf()
}