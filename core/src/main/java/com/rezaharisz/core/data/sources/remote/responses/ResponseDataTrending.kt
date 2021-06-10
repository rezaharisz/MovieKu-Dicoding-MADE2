package com.rezaharisz.core.data.sources.remote.responses

import com.google.gson.annotations.SerializedName

data class ResponseDataTrending(

    @field:SerializedName("id")
    val id: Int,

    @field:SerializedName("poster_path")
    val poster: String,

    @field:SerializedName("name", alternate = ["title"])
    val trendingName: String,

    @field:SerializedName("overview")
    val description: String,

    @field:SerializedName("release_date", alternate = ["first_air_date"])
    val releasedate: String,

    @field:SerializedName("vote_average")
    val rate: Double,

    @field:SerializedName("vote_count")
    val votecount: Int

)