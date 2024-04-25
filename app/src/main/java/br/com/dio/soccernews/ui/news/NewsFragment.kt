package br.com.dio.soccernews.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dio.soccernews.databinding.FragmentNewsBinding
import br.com.dio.soccernews.ui.commons.adapters.NewsAdapter
import com.google.android.material.snackbar.Snackbar

class NewsFragment : Fragment() {

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    private lateinit var newsViewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        newsViewModel = ViewModelProvider(
            this, NewsViewModelFactory(requireActivity().application)
        ).get(NewsViewModel::class.java)

        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        binding.rvNews.layoutManager = LinearLayoutManager(this.context)
        newsViewModel.newsList.observe(viewLifecycleOwner) { newsList ->
            binding.rvNews.adapter = NewsAdapter(newsList) { news ->
                newsViewModel.favorite(news)
            }
        }
        newsViewModel.error.observe(viewLifecycleOwner) { error ->
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
