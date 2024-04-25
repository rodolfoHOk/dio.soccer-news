package br.com.dio.soccernews.ui.commons.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.soccernews.R
import br.com.dio.soccernews.databinding.NewsItemBinding
import br.com.dio.soccernews.domain.model.News
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val newsList: List<News>,
    private val onFavorite: (news: News) -> Unit
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = NewsItemBinding.inflate(layoutInflater, parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(newsList[position])
    }

    inner class NewsViewHolder(
        private val binding: NewsItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(news: News) = binding.run {
            this.tvCardTitle.text = news.title
            this.tvCardHeadline.text = news.headline
            Picasso.get().load(news.image)
                .centerCrop()
                .resize(396, 160)
                .into(this.ivCardImage)
            this.btnOpenLink.setOnClickListener { view ->
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setData(Uri.parse(news.link))
                view.context.startActivity(intent)
            }
            this.ivShare.setOnClickListener { view ->
                val intent = Intent(Intent.ACTION_SEND)
                intent.setType("text/plain")
                intent.putExtra(Intent.EXTRA_TEXT, news.link)
                val shareString = view.context.getString(R.string.share)
                view.context.startActivity(Intent.createChooser(intent, shareString))
            }
            this.ivFavorite.setOnClickListener {
                onFavorite(news)
            }
            val imageResource = if (news.favorite) {
                R.drawable.ic_favorite
            } else {
                R.drawable.ic_favorite_border
            }
            this.ivFavorite.setImageResource(imageResource)
        }

    }

}