package ankiitdev.viewpic.core

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import ankiitdev.viewpic.DownloadImageTask
import ankiitdev.viewpic.async.DownloadTask
import ankiitdev.viewpic.cache.CacheRepository
import ankiitdev.viewpic.cache.Config
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.Future

class Viewpic private constructor(context: Context, cacheSize: Int) {
    private val cache = CacheRepository(context, cacheSize)
//    private val executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
    private val mRunningDownloadList:HashMap<String, Future<Bitmap?>> = hashMapOf()

    suspend fun displayImage(url: String, imageview: ImageView, placeholder: Int?) {
        val bitmap = cache.getImage(url)
        bitmap?.let {
            imageview.setImageBitmap(it)
            return
        }
            ?: run {
                imageview.tag = url
                if (placeholder != null)
                    imageview.setImageResource(placeholder)
//                addDownloadImageTask( url, DownloadImageTask(url , imageview , cache))
                downloadImages(url , imageview , cache)
            }

    }

    private suspend fun downloadImages(url: String, imageView: ImageView, cache: CacheRepository) {
        var bitmap: Bitmap? = null
        try {
            withContext(Dispatchers.IO) {
                val imageUrl = URL(url)
                val conn: HttpURLConnection =
                    imageUrl.openConnection() as HttpURLConnection
                bitmap = BitmapFactory.decodeStream(conn.inputStream)
                conn.disconnect()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        bitmap?.let {
            if (imageView.tag == url) {
                imageView.setImageBitmap(bitmap)
            }
            cache.addImage(url, it)
        }
    }


    private fun addDownloadImageTask(url: String, downloadTask: DownloadTask<Bitmap?>) {
//        mRunningDownloadList[url] = executorService.submit(downloadTask)
    }

    fun clearcache() {
        cache.clearImages()
    }

    fun cancelTask(url: String){
        synchronized(this){
            mRunningDownloadList.forEach {
                if (it.key == url && !it.value.isDone)
                    it.value.cancel(true)
            }
        }
    }

    fun cancelAll() {
        synchronized (this) {
            mRunningDownloadList.forEach{
                if ( !it.value.isDone)
                    it.value.cancel(true)
            }
            mRunningDownloadList.clear()
        }
    }

    companion object {
        private val INSTANCE: Viewpic? = null
        @Synchronized
        fun getInstance(context: Context, cacheSize: Int = Config.defaultCacheSize): Viewpic {
            return INSTANCE?.let { return INSTANCE }
                ?: run {
                    return Viewpic(context, cacheSize)
                }
        }
    }
}