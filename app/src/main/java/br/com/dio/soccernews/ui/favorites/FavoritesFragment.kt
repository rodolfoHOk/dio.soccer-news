package br.com.dio.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dio.soccernews.databinding.FragmentFavoritesBinding
import br.com.dio.soccernews.ui.commons.adapter.NewsAdapter
import br.com.dio.soccernews.ui.commons.state.State
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private val favoritesViewModel by viewModels<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvNews.layoutManager = LinearLayoutManager(this.context)

        binding.srlFavorites.setOnRefreshListener {
            favoritesViewModel.findAllFavorites()
        }

        favoritesViewModel.favoritesNewsList.observe(viewLifecycleOwner) { favoritesList ->
            binding.rvNews.adapter = NewsAdapter(favoritesList) { news ->
                favoritesViewModel.removeFavorite(news)
            }
        }
        favoritesViewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                State.DOING -> binding.srlFavorites.isRefreshing = true
                else -> binding.srlFavorites.isRefreshing = false
            }
        }
        favoritesViewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error.isNotBlank()) {
                Snackbar.make(requireView(), error, Snackbar.LENGTH_LONG).show()
            }
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        favoritesViewModel.findAllFavorites()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
