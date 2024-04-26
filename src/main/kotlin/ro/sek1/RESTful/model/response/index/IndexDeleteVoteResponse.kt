package ro.sek1.RESTful.model.response.index

import ro.sek1.RESTful.model.Response

data class IndexDeleteVoteResponse(
    val message: String,
    val ids: MutableList<Long>,
): Response("indexService")
