package ankiitdev.viewpic.remote

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class CacheInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = chain.proceed(chain.request())
//        val cacheControl = CacheControl.Builder()
//            .maxAge(10, TimeUnit.DAYS)
//            .build()
        val maxAge = 60
        return response.newBuilder()
//            .header("Cache-Control", cacheControl.toString())
            .header("Cache-Control", "public, max-Age=$maxAge")
            .build()
    }
}