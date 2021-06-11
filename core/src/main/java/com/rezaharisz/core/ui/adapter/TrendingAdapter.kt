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
import com.rezaharisz.core.domain.model.Trending

class TrendingAdapter: RecyclerView.Adapter<TrendingAdapter.TrendingViewHolder>() {

    private var listTrending = ArrayList<Trending>()
    var onItemClick: ((Trending) -> Unit)? = null

    fun setData(trending: List<Trending>?){
        if (trending == null) return
        listTrending.clear()
        listTrending.addAll(trending)
        notifyDataSetChanged()
    }

    inner class TrendingViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemFavoritesBinding.bind(itemView)

        fun bind(trending: Trending){
            with(binding){
                Glide.with(itemView)
                    .load(BASE_IMAGE + trending.poster)
                    .override(150,220)
                    .listener(object : RequestListener<Drawable> {
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
                tvFavorites.text = trending.trendingName
                tvRate.text = trending.rate.toString()
                tvDes.text = trending.description
            }
        }

        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listTrending[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder =
        TrendingViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_favorites, parent, false))

    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val trending = listTrending[position]
        holder.bind(trending)
    }

    override fun getItemCount(): Int = listTrending.size

}