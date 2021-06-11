package com.rezaharisz.favorites.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezaharis.movieku.di.FavoritesDependencies
import com.rezaharis.movieku.movie.detail.DetailMovieActivity
import com.rezaharisz.core.ui.adapter.MovieAdapter
import com.rezaharisz.favorites.databinding.FragmentFavoriteMovieBinding
import com.rezaharisz.favorites.di.DaggerFavoritesComponent
import com.rezaharisz.favorites.util.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteMovieFragment : Fragment() {

    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoritesMovieViewModel: FavoritesMovieViewModel by viewModels {factory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        DaggerFavoritesComponent.builder()
            .context(requireContext())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavoritesDependencies::class.java
                )
            )
            .build()
            .inject(this)

        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            val favoriteMovieAdapter = MovieAdapter()
            favoriteMovieAdapter.onItemClick = {
                val intent = Intent(activity, DetailMovieActivity::class.java)
                intent.putExtra(DetailMovieActivity.MOVIE, it)
                startActivity(intent)
            }

            favoritesMovieViewModel.favoriteMovies.observe(viewLifecycleOwner) { listMovie ->
                favoriteMovieAdapter.setData(listMovie)
                binding.lottieAnim.visibility = View.GONE
                binding.emptyFav.visibility = View.GONE

                if (listMovie.isEmpty()) {
                    binding.lottieAnim.visibility = View.VISIBLE
                    binding.emptyFav.visibility = View.VISIBLE
                }
            }

            with(binding.rvFavoritesMovies){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteMovieAdapter
            }
        }
    }

}