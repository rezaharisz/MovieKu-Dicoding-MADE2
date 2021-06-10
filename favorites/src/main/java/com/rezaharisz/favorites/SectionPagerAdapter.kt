package com.rezaharisz.favorites

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rezaharisz.favorites.movies.FavoriteMovieFragment
import com.rezaharisz.favorites.tvshows.FavoriteTvShowsFragment

class SectionPagerAdapter(activity: AppCompatActivity): FragmentStateAdapter(activity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> FavoriteMovieFragment()
            else -> FavoriteTvShowsFragment()
        }
    }
}