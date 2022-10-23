package ankiitdev.viewpic.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ankiitdev.viewpic.model.UnsplashImage
import ankiitdev.viewpic.paging.ImagePagingSource
import ankiitdev.viewpic.remote.UnsplashApi
import ankiitdev.viewpic.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class ImageRepository @Inject constructor(
    private val unsplashApi: UnsplashApi
) {

    fun getAllImages(): Flow<PagingData<UnsplashImage>> {
//        val pagingSourceFactory = { unsplashDatabase.unsplashImageDao().getAllImages() }
//        return Pager(
//            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
//            remoteMediator = UnsplashRemoteMediator(
//                unsplashApi = unsplashApi,
//                unsplashDatabase = unsplashDatabase
//            ),
//            pagingSourceFactory = pagingSourceFactory
//        ).flow
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                ImagePagingSource(unsplashApi = unsplashApi)
            }
        ).flow
    }

}