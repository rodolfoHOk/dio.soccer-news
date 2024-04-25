package br.com.dio.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dio.soccernews.databinding.FragmentNewsBinding
import br.com.dio.soccernews.ui.commons.adapter.NewsAdapter
import br.com.dio.soccernews.ui.commons.state.State
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private val newsViewModel by viewModels<NewsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        binding.rvNews.layoutManager = LinearLayoutManager(this.context)

        binding.srlNews.setOnRefreshListener {
            newsViewModel.getAllNews()
        }

        newsViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            binding.rvNews.adapter = NewsAdapter(newsList) { news ->
                newsViewModel.favorite(news)
            }
        }
        newsViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.DOING -> binding.srlNews.isRefreshing = true
                else -> binding.srlNews.isRefreshing = false
            }
        }
        newsViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotBlank()) {
                Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        newsViewModel.getAllNews()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
