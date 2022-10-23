package ankiitdev.viewpic.model

import kotlinx.serialization.Serializable

@Serializable
data class UnsplashImage(
    val id: String,
    val urls: Urls,
    val likes: Int,
    val user: User
)
