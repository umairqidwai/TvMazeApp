package com.smartmobilefactory.tvmazeapp.domain.repo

import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponse
import retrofit2.Response

interface TvMazeRepo {
    suspend fun search(search: String): Response<TvShowResponse>
}