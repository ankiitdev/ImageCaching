package ankiitdev.viewpic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImage(
//    @SerialName("id")
//    val id: String?= null,
//    @SerialName("urls")
//    val urls: Urls?= null,
//    @SerialName("likes")
//    val likes: String?= null,
    @SerialName("total")
    val total: Int?= null,
    @SerialName("totalHits")
    val totalHits: Int?= null,
    @SerialName("hits")
    val hits: List<Hits>?,
//    @SerialName("user")
//    val user: User?= null
)
