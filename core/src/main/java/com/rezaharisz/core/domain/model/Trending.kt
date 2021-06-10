package com.rezaharisz.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Trending(

    var id: Int? = null,

    var poster: String? = null,

    var trendingName: String? = null,

    var description: String? = null,

    var releasedate: String? = null,

    var rate: Double? = null,

    var votecount: Int? = null,

    var setFavorite: Boolean = false

): Parcelable