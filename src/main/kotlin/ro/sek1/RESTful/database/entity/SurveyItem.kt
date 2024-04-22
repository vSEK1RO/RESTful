package ro.sek1.RESTful.database.entity

import jakarta.persistence.*

@Entity
@Table(name="surveyitems")
data class SurveyItem (
    @ManyToOne
    val survey: Survey = Survey(),

    @Column(length = 50, nullable = false)
    val title: String = "",

    //nullable

    @Column(columnDefinition = "TEXT")
    val description: String? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToMany(mappedBy = "surveyitems_wh_chosen")
    val users_wh_chosen: MutableList<User> = mutableListOf()
}