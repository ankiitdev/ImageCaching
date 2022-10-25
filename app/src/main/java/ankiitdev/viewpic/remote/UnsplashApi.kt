package ankiitdev.viewpic.remote

import ankiitdev.viewpic.BuildConfig
import ankiitdev.viewpic.model.UnsplashImage
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashImage>

    @GET("/api")
    suspend fun getPixabayImages(
        @Query("key") key: String,
        @Query("image_type") imageType: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): UnsplashImage

}