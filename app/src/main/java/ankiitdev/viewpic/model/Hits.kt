package ankiitdev.viewpic.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Hits(
    @SerialName("id")
    val id: Int?= null,
    @SerialName("webformatURL")
    val userImageURL: String? = null
)
