package com.rezaharisz.core.data.sources.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trending_entities")
data class TrendingEntities(

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "trending_id")
    var id: Int? = null,

    @ColumnInfo(name = "trending_poster")
    var poster: String? = null,

    @ColumnInfo(name = "trending_name")
    var trendingName: String? = null,

    @ColumnInfo(name = "trending_description")
    var description: String? = null,

    @ColumnInfo(name = "trending_releasedate")
    var releasedate: String? = null,

    @ColumnInfo(name = "trending_rate")
    var rate: Double? = null,

    @ColumnInfo(name = "trending_votecount")
    var votecount: Int? = null,

    @ColumnInfo(name = "trending_setfavorite")
    var setFavorite: Boolean = false

)
