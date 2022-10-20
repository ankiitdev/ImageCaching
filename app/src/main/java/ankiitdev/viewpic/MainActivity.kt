package ankiitdev.viewpic

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.lifecycleScope
import ankiitdev.viewpic.core.Viewpic
import ankiitdev.viewpic.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : BaseActivity<ActivityMainBinding>() {

    private lateinit var imageLoader: Viewpic

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        imageLoader = Viewpic.getInstance(this, 10*1024*1024)
        lifecycleScope.launch {
            imageLoader.displayImage(IMAGE_URL, binding.imageView, R.drawable.placeholder)
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