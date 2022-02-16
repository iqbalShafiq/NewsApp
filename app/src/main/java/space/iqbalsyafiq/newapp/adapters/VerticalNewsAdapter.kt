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
import kotlinx.android.synthetic.main.item_horizontal.view.ivNews
import kotlinx.android.synthetic.main.item_horizontal.view.tvTitle
import kotlinx.android.synthetic.main.item_vertical.view.*
import space.iqbalsyafiq.newapp.databinding.ItemVerticalBinding
import space.iqbalsyafiq.newapp.model.Article

class VerticalNewsAdapter(
    val context: Context,
    val newsList: ArrayList<Article>
): RecyclerView.Adapter<VerticalNewsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    private lateinit var binding: ItemVerticalBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemVerticalBinding.inflate(
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
            tvAuthor.text = news.author

            val options = RequestOptions()
                .transform(CenterCrop(), RoundedCorners(32))
            Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(news.urlToImage)
                .into(ivNewsVertical)
        }
    }

    override fun getItemCount(): Int = newsList.size


}