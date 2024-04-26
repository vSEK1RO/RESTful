package ro.sek1.RESTful.database.entity

import jakarta.persistence.*

@Entity
@Table(name="surveyitems")
data class SurveyItem (
    @ManyToOne
    val survey: Survey = Survey(),

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
    var users_wh_chosen: MutableList<User> = mutableListOf()

    override fun equals(other: Any?): Boolean {
        if(other !is SurveyItem)return false
        return other.id == id
    }
}