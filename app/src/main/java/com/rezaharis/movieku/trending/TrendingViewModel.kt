package com.rezaharis.movieku.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rezaharisz.core.domain.usecase.MovieKuUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrendingViewModel @Inject constructor(movieKuUseCase: MovieKuUseCase): ViewModel() {
    val trending = movieKuUseCase.getTrending().asLiveData()
}