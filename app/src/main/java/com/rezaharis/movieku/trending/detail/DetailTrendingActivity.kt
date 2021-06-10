package com.rezaharis.movieku.trending.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.rezaharis.movieku.databinding.ActivityDetailTrendingBinding
import com.rezaharisz.core.BuildConfig
import com.rezaharisz.core.domain.model.Trending
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTrendingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailTrendingBinding

    companion object{
        const val TRENDING = "trending"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailTrendingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            super.onBackPressed()
        }

        val detailTrending = intent.getParcelableExtra<Trending>(TRENDING)
        if (detailTrending != null){
            getShow(detailTrending)
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        } else{
            binding.progressbar.visibility = View.GONE
        }
    }

    private fun getShow(trending: Trending){
        Glide.with(this)
            .load(BuildConfig.BASE_IMAGE + trending.poster)
            .override(250, 320)
            .into(binding.ivPoster)
        binding.showName.text = trending.trendingName
        binding.showRelease.text = trending.releasedate
        binding.showRate.text = trending.rate.toString()
        binding.showVotecount.text = trending.votecount.toString()
        binding.showDes.text = trending.description
    }

}