package com.rezaharisz.core.utils

import com.rezaharisz.core.data.sources.local.entity.MovieEntities
import com.rezaharisz.core.data.sources.local.entity.TrendingEntities
import com.rezaharisz.core.data.sources.local.entity.TvShowsEntities
import com.rezaharisz.core.data.sources.remote.responses.ResponseDataMovie
import com.rezaharisz.core.data.sources.remote.responses.ResponseDataTrending
import com.rezaharisz.core.data.sources.remote.responses.ResponseDataTvShows
import com.rezaharisz.core.domain.model.Movies
import com.rezaharisz.core.domain.model.Trending
import com.rezaharisz.core.domain.model.TvShows

object DataMapper {

    //MOVIES
    fun mapMovieResponsesToEntities(input: List<ResponseDataMovie>): List<MovieEntities>{
        val movieList = ArrayList<MovieEntities>()
        input.map {
            val movies = MovieEntities(
                id = it.id,
                poster = it.poster,
                movieName = it.movieName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount
            )
            movieList.add(movies)
        }
        return movieList
    }

    fun mapMovieEntitiesToDomain(input: List<MovieEntities>): List<Movies> =
        input.map {
            Movies(
                id = it.id,
                poster = it.poster,
                movieName = it.movieName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount,
                setFavorite = it.setFavorite
            )
        }

    fun mapMovieDomainToEntities(input: Movies) =
        MovieEntities(
            id = input.id,
            poster = input.poster,
            movieName = input.movieName,
            description = input.description,
            releasedate = input.releasedate,
            rate = input.rate,
            votecount = input.votecount,
            setFavorite = input.setFavorite
        )

    //TV SHOWS
    fun mapTvShowsResponsesToEntites(input: List<ResponseDataTvShows>): List<TvShowsEntities>{
        val tvShowsList = ArrayList<TvShowsEntities>()
        input.map {
            val tvShows = TvShowsEntities(
                id = it.id,
                poster = it.poster,
                tvShowsName = it.tvShowsName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount
            )
            tvShowsList.add(tvShows)
        }
        return tvShowsList
    }

    fun mapTvShowsEntitiesToDomain(input: List<TvShowsEntities>): List<TvShows> =
        input.map {
            TvShows(
                id = it.id,
                poster = it.poster,
                tvShowsName = it.tvShowsName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount,
                setFavorite = it.setFavorite
            )
        }

    fun mapTvShowsDomainToEntities(input: TvShows) =
        TvShowsEntities(
            id = input.id,
            poster = input.poster,
            tvShowsName = input.tvShowsName,
            description = input.description,
            releasedate = input.releasedate,
            rate = input.rate,
            votecount = input.votecount,
            setFavorite = input.setFavorite
        )

    //TRENDING
    fun mapTrendingResponsesToEntities(input: List<ResponseDataTrending>): List<TrendingEntities>{
        val trendingList = ArrayList<TrendingEntities>()
        input.map {
            val trending = TrendingEntities(
                id = it.id,
                poster = it.poster,
                trendingName = it.trendingName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount
            )
            trendingList.add(trending)
        }
        return trendingList
    }

    fun mapTrendingEntitiesToDomain(input: List<TrendingEntities>): List<Trending> =
        input.map {
            Trending(
                id = it.id,
                poster = it.poster,
                trendingName = it.trendingName,
                description = it.description,
                releasedate = it.releasedate,
                rate = it.rate,
                votecount = it.votecount,
                setFavorite = it.setFavorite
            )
        }

    fun mapTrendingDomainToEntities(input: Trending) =
        TrendingEntities(
            id = input.id,
            poster = input.poster,
            trendingName = input.trendingName,
            description = input.description,
            releasedate = input.releasedate,
            rate = input.rate,
            votecount = input.votecount,
            setFavorite = input.setFavorite
        )

}