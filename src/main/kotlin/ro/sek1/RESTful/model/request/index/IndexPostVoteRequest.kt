package ro.sek1.RESTful.model.request.index

import com.fasterxml.jackson.annotation.JsonProperty

data class IndexPostVoteRequest(
    @JsonProperty("list_ids")
    var list_ids: MutableList<Long>
)
