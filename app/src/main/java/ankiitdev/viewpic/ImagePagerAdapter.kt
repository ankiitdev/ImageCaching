package ankiitdev.viewpic

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ankiitdev.viewpic.databinding.ItemImageBinding
import ankiitdev.viewpic.model.Hits

class ImagePagerAdapter :
    PagingDataAdapter<Hits, ImagePagerAdapter.ImageViewHolder>(
        object :
            DiffUtil.ItemCallback<Hits>() {
            override fun areItemsTheSame(
                oldItem: Hits,
                newItem: Hits
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: Hits,
                newItem: Hits
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
        fun setData(image: Hits?) {
            binding.imageView.loadImage(image?.userImageURL.orEmpty(), null)
            binding.textId.text = image?.id.toString()
        }
    }
}
