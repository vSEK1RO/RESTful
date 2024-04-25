package ro.sek1.RESTful.database.entity

import jakarta.persistence.*

@Entity
@Table(name="surveyitems")
data class SurveyItem (

    @Column(length = 50, nullable = false)
    var title: String = "",

    //nullable

    @Column(columnDefinition = "TEXT")
    var description: String? = null,
){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @ManyToMany(mappedBy = "surveyitems_wh_chosen")
    val users_wh_chosen: MutableList<User> = mutableListOf()

    @ManyToOne
    val survey: Survey = Survey()
}