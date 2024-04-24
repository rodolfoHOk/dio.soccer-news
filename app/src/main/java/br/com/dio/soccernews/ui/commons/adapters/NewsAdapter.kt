package br.com.dio.soccernews.ui.commons.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.dio.soccernews.databinding.NewsItemBinding
import br.com.dio.soccernews.domain.model.News
import com.squareup.picasso.Picasso

class NewsAdapter(
    private val newsList: List<News>
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
        }

    }

}
