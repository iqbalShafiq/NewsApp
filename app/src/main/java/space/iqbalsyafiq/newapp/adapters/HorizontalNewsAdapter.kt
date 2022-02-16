package space.iqbalsyafiq.newapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_horizontal.view.*
import space.iqbalsyafiq.newapp.databinding.ItemHorizontalBinding
import space.iqbalsyafiq.newapp.model.Article

class HorizontalNewsAdapter(
    val context: Context,
    val newsList: ArrayList<Article>
): RecyclerView.Adapter<HorizontalNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private lateinit var binding: ItemHorizontalBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemHorizontalBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val news = newsList[position]
        with(holder.itemView) {
            tvTitle.text = news.title
            val options = RequestOptions()
                .transform(CenterCrop(), RoundedCorners(32))
            Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(news.urlToImage)
                .into(ivNews)
        }
    }

    override fun getItemCount(): Int = newsList.size

}