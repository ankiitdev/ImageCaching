package ankiitdev.viewpic.di

import android.content.Context
import ankiitdev.viewpic.core.GradleDependencies
import ankiitdev.viewpic.remote.CacheInterceptor
import ankiitdev.viewpic.remote.ForceCacheInterceptor
import ankiitdev.viewpic.remote.UnsplashApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@ExperimentalSerializationApi
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(
        @ApplicationContext context: Context,
        cacheInterceptor: CacheInterceptor,
        forceCacheInterceptor: ForceCacheInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .addInterceptor(ChuckInterceptor(context))
            .addInterceptor(forceCacheInterceptor)
            .addNetworkInterceptor(cacheInterceptor)
            .cache(Cache(context.cacheDir, (10 * 10 * 1024).toLong()))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        apiBaseUrl: String
    ): Retrofit {
        val contentType = MediaType.get("application/json")
        val json = Json {
            ignoreUnknownKeys = true
        }

        return Retrofit.Builder()
            .baseUrl(apiBaseUrl)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(contentType))
            .build()
    }

    @Provides
    @Singleton
    fun provideUnsplashApi(retrofit: Retrofit): UnsplashApi {
        return retrofit.create(UnsplashApi::class.java)
    }

//    @Provides
//    @Singleton
//    fun provideGradleDependencies(): GradleDependencies {
//        return GradleDependenciesImpl()
//    }

//    @Provides
//    @Singleton
//    fun provideBaseUrl(gradleDependencies: GradleDependencies): String {
//        return gradleDependencies.apiBaseUrl
//    }

    @Provides
    @Singleton
    fun provideBaseUrl(
        gradleDependencies: GradleDependencies
    ): String {
        return gradleDependencies.apiBaseUrl
    }

//    @Provides
//    @Singleton
//    fun provideApiKey(
//        gradleDependencies: GradleDependencies
//    ): String {
//        return gradleDependencies.apiKey
//    }

}