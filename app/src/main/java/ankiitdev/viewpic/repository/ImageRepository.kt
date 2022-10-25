package ankiitdev.viewpic.repository

import androidx.paging.*
import ankiitdev.viewpic.model.Hits
import ankiitdev.viewpic.model.UnsplashImage
import ankiitdev.viewpic.paging.ImagePagingSource
import ankiitdev.viewpic.remote.UnsplashApi
import ankiitdev.viewpic.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@ExperimentalPagingApi
class ImageRepository @Inject constructor(
    private val unsplashApi: UnsplashApi
) {

    fun getAllImages(): Flow<PagingData<Hits>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                ImagePagingSource(unsplashApi = unsplashApi)
            }
        ).flow
    }

}