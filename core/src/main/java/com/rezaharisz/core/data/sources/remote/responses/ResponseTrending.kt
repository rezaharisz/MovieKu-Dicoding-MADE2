package com.rezaharisz.core.data.sources.remote.responses

import com.google.gson.annotations.SerializedName

data class ResponseTrending(
    @field:SerializedName("results")
    val results: List<ResponseDataTrending>
)
