package ankiitdev.viewpic.cache

import android.content.Context
import android.graphics.Bitmap

class CacheRepository constructor(context: Context, cacheSize: Int): ImageCache {

    private val diskCache = DiskCache.getInstance(context)
    private val memoryCache = MemoryCache(cacheSize)

    override fun addImage(url: String, bitmap: Bitmap) {
        diskCache.addImage(url, bitmap)
        memoryCache.addImage(url, bitmap)
    }

    override fun getImage(url: String): Bitmap? {
        return memoryCache.getImage(url) ?: diskCache.getImage(url)
    }

    override fun clearImages() {
        diskCache.clearImages()
        memoryCache.clearImages()
    }
}