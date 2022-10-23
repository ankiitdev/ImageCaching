package ankiitdev.viewpic

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import ankiitdev.viewpic.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class MainViewModel @Inject constructor(
    repository: ImageRepository
) : ViewModel() {
    val getAllImages = repository.getAllImages()
}