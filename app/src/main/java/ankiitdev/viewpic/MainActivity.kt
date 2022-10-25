package ankiitdev.viewpic

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import ankiitdev.viewpic.core.Viewpic
import ankiitdev.viewpic.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

//    private lateinit var imageLoader: Viewpic

    private val viewModel: MainViewModel by viewModels()


    private val pagerAdapter: ImagePagerAdapter by lazy {
        ImagePagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerImages.apply {
            this.layoutManager = LinearLayoutManager(this.context)
            adapter = pagerAdapter
        }

        binding.recyclerImages.adapter = pagerAdapter
//        imageLoader = Viewpic.getInstance(this, 10*1024*1024)
//        lifecycleScope.launch {
//            imageLoader.displayImage("https://images.unsplash.com/photo-1666122093052-82dda8cff1be?crop%5Cu003dentropy%5Cu0026cs%5Cu003dtinysrgb%5Cu0026fit%5Cu003dmax%5Cu0026fm%5Cu003djpg%5Cu0026ixid%5Cu003dMnwzNjQxOTd8MHwxfGFsbHwxN3x8fHx8fDJ8fDE2NjYyOTUxMTA%5Cu0026ixlib%5Cu003drb-4.0.3%5Cu0026q%5Cu003d80%5Cu0026w%5Cu003d1080", binding.imageView, R.drawable.placeholder)
//        }

        lifecycleScope.launchWhenCreated {
            viewModel.getAllImages.collectLatest { pagingData ->
                pagerAdapter.submitData(pagingData)
            }
        }

    }


    override fun inflateLayout(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onDestroy() {
        super.onDestroy()
//        Viewpic.getInstance(this).cancelAll()
    }

    companion object {
        const val IMAGE_URL =
            "https://i.pinimg.com/originals/93/09/77/930977991c52b48e664c059990dea125.jpg"
    }


}