package com.smartmobilefactory.tvmazeapp.data.repo

import com.smartmobilefactory.tvmazeapp.data.remote.TvMazeService
import com.smartmobilefactory.tvmazeapp.domain.model.TvShowResponse
import com.smartmobilefactory.tvmazeapp.domain.repo.TvMazeRepo
import retrofit2.Response
import javax.inject.Inject

class TvMazeRepoImpl @Inject constructor(val tvMazeService: TvMazeService) :
    TvMazeRepo {
    override suspend fun search(search: String): Response<TvShowResponse> {
        return tvMazeService.search(search)
    }

}