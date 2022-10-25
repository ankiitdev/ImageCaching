package ankiitdev.viewpic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ankiitdev.viewpic.model.Hits
import ankiitdev.viewpic.model.UnsplashImage
import ankiitdev.viewpic.remote.UnsplashApi
import ankiitdev.viewpic.util.Constants.ITEMS_PER_PAGE

class ImagePagingSource(
    private val unsplashApi: UnsplashApi
) : PagingSource<Int, Hits>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hits> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi
                .getPixabayImages(key = "30849235-0ed94c600034772ec06449f10", imageType = "photo", page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.hits?.isEmpty() ?: true
//            unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val data = response.hits
            LoadResult.Page(
                data = data.orEmpty(),
                prevKey = null,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Hits>): Int? {
        return state.anchorPosition
    }

}