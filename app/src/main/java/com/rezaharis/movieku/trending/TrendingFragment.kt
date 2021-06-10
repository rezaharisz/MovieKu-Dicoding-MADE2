package com.rezaharis.movieku.trending

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rezaharis.movieku.databinding.FragmentTrendingBinding
import com.rezaharis.movieku.trending.detail.DetailTrendingActivity
import com.rezaharisz.core.data.Resource
import com.rezaharisz.core.ui.adapter.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrendingFragment : Fragment() {

    private var _binding: FragmentTrendingBinding? = null
    private val binding get() = _binding!!

    private val trendingViewModel: TrendingViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTrendingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            val trendingAdapter = TrendingAdapter()
            trendingAdapter.onItemClick = {
                val intent = Intent(activity, DetailTrendingActivity::class.java)
                intent.putExtra(DetailTrendingActivity.TRENDING, it)
                startActivity(intent)
            }

            trendingViewModel.trending.observe(viewLifecycleOwner){ listTrending ->
                if (listTrending != null) {
                    when (listTrending) {
                        is Resource.Loading -> showLoading(true)
                        is Resource.Success -> {
                            showLoading(false)
                            trendingAdapter.setData(listTrending.data)
                        }
                        is Resource.Error -> {
                            showLoading(false)
                            Toast.makeText(
                                context,
                                "Kesalahan Ketika Memuat Data",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }

            with(binding.crTrending){
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = trendingAdapter
            }
        }
    }

    private fun showLoading(state: Boolean){
        if (state){
            binding.progressbar.visibility = View.VISIBLE
        } else{
            binding.progressbar.visibility = View.GONE
        }
    }

}