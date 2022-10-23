package ankiitdev.viewpic.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ankiitdev.viewpic.model.UnsplashImage
import ankiitdev.viewpic.remote.UnsplashApi
import ankiitdev.viewpic.util.Constants.ITEMS_PER_PAGE

class ImagePagingSource(
    private val unsplashApi: UnsplashApi
) : PagingSource<Int, UnsplashImage>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.isEmpty()
            unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
            LoadResult.Page(
                data = response,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (endOfPaginationReached) null else currentPage + 1
            )
//            if (response.isNotEmpty()) {
//                unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
//                LoadResult.Page(
//                    data = response,
//                    prevKey = if (currentPage == 1) null else currentPage - 1,
//                    nextKey = if (endOfPaginationReached == true) null else currentPage + 1
//                )
//            } else {
//                unsplashApi.getAllImages(page = currentPage, perPage = ITEMS_PER_PAGE)
//                LoadResult.Page(
//                    data = emptyList(),
//                    prevKey = null,
//                    nextKey = null
//                )
//            }

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition
    }

}