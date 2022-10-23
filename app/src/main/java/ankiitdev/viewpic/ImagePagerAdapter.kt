package ankiitdev.viewpic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ankiitdev.viewpic.core.Viewpic
import ankiitdev.viewpic.databinding.ItemImageBinding
import ankiitdev.viewpic.model.UnsplashImage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImagePagerAdapter :
    PagingDataAdapter<UnsplashImage, ImagePagerAdapter.ImageViewHolder>(
        object :
            DiffUtil.ItemCallback<UnsplashImage>() {
            override fun areItemsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UnsplashImage,
                newItem: UnsplashImage
            ): Boolean {
                return oldItem == newItem
            }
        }
    ) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageViewHolder {
        val binding = ItemImageBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setData(getItem(position))
    }

    class ImageViewHolder(
        private val binding: ItemImageBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(image: UnsplashImage?) {

            CoroutineScope(Dispatchers.Main).launch {
                Viewpic
                    .getInstance(context = binding.root.context, 10 * 1024 * 1024)
                    .displayImage(image?.urls?.regular.orEmpty(), binding.imageView, null)
            }

        }
    }
}
