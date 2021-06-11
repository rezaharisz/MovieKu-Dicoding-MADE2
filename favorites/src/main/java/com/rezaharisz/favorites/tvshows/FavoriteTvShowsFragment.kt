package com.rezaharisz.favorites.tvshows

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezaharis.movieku.di.FavoritesDependencies
import com.rezaharis.movieku.tvshow.detail.DetailTvShowActivity
import com.rezaharisz.core.ui.adapter.TvShowsAdapter
import com.rezaharisz.favorites.databinding.FragmentFavoriteTvShowsBinding
import com.rezaharisz.favorites.di.DaggerFavoritesComponent
import com.rezaharisz.favorites.util.ViewModelFactory
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class FavoriteTvShowsFragment : Fragment() {

    private var _binding: FragmentFavoriteTvShowsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory
    private val favoritesTvShowsViewModel: FavoritesTvShowsViewModel by viewModels {factory}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoriteTvShowsBinding.inflate(inflater, container, false)
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
            val favoriteTvShowsAdapter = TvShowsAdapter()
            favoriteTvShowsAdapter.onItemClick = {
                val intent = Intent(activity, DetailTvShowActivity::class.java)
                intent.putExtra(DetailTvShowActivity.TV_SH0WS, it)
                startActivity(intent)
            }

            favoritesTvShowsViewModel.favoriteTvShows.observe(viewLifecycleOwner) { listTvShows ->
                favoriteTvShowsAdapter.setData(listTvShows)
                binding.lottieAnim.visibility = View.GONE
                binding.emptyFav.visibility = View.GONE

                if (listTvShows.isEmpty()) {
                    binding.lottieAnim.visibility = View.VISIBLE
                    binding.emptyFav.visibility = View.VISIBLE
                }
            }

            with(binding.rvFavoritesTvshows){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = favoriteTvShowsAdapter
            }
        }
    }
}