package ankiitdev.viewpic.remote

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class ForceCacheInterceptor @Inject constructor(@ApplicationContext val context: Context) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var builder: Request.Builder = chain.request().newBuilder()
        if (!NetworkUtils.isInternetAvailable(context)) {
            builder.cacheControl(CacheControl.FORCE_CACHE);
            val maxStale = 60*60*24*30
            builder = builder.header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                .removeHeader("Pragma")
        }
        return chain.proceed(builder.build());
    }
}
