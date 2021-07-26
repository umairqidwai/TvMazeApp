package com.smartmobilefactory.tvmazeapp.data.remote

import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvMazeService {
    @GET("/search/shows")
    suspend fun search(@Query("q") search: String): Response<TvShowResponse>
}