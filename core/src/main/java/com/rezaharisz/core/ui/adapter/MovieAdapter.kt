@file:Suppress("DEPRECATION")

package com.rezaharisz.core.ui.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.rezaharisz.core.BuildConfig.BASE_IMAGE
import com.rezaharisz.core.R
import com.rezaharisz.core.databinding.ItemFavoritesBinding
import com.rezaharisz.core.domain.model.Movies

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    private var listFavoriteMovies = ArrayList<Movies>()
    var onItemClick: ((Movies) -> Unit)? = null

    fun setData(favoriteMovieList: List<Movies>?){
        if (favoriteMovieList == null) return
        listFavoriteMovies.clear()
        listFavoriteMovies.addAll(favoriteMovieList)
        notifyDataSetChanged()
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoritesBinding.bind(itemView)

        fun bind(movies: Movies){
            with(binding){
                Glide.with(itemView)
                    .load(BASE_IMAGE + movies.poster)
                    .override(150,220)
                    .listener(object : RequestListener<Drawable>{
                        override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                            binding.progressbar.visibility = View.VISIBLE
                            return true
                        }

                        override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                            binding.progressbar.visibility = View.GONE
                            return false
                        }

                    })
                    .into(ivPoster)

                tvFavorites.text = movies.movieName
                tvRate.text = movies.rate.toString()
                tvDes.text = movies.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listFavoriteMovies[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MovieHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false))

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movieFavorites = listFavoriteMovies[position]
        holder.bind(movieFavorites)
    }

    override fun getItemCount() = listFavoriteMovies.size

}