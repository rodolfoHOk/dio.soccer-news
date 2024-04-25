package br.com.dio.soccernews.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dio.soccernews.databinding.FragmentFavoritesBinding
import br.com.dio.soccernews.ui.commons.adapter.NewsAdapter
import com.google.android.material.snackbar.Snackbar

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!

    private lateinit var favoritesViewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        favoritesViewModel = ViewModelProvider(
            this, FavoritesViewModelFactory(requireActivity().application)
        ).get(FavoritesViewModel::class.java)

        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)

        binding.rvNews.layoutManager = LinearLayoutManager(this.context)
        favoritesViewModel.favoritesNewsList.observe(viewLifecycleOwner) { favoritesList ->
            binding.rvNews.adapter = NewsAdapter(favoritesList) { news ->
                favoritesViewModel.removeFavorite(news)
            }
        }
        favoritesViewModel.error.observe(viewLifecycleOwner) { error ->
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
