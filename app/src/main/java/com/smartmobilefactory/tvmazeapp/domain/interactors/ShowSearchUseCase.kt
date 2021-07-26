package com.smartmobilefactory.tvmazeapp.domain.interactors

import com.smartmobilefactory.tvmazeapp.domain.interactors.base.BaseUseCase
import com.smartmobilefactory.tvmazeapp.domain.repo.TvMazeRepo
import javax.inject.Inject

class ShowSearchUseCase @Inject constructor(private val tvMazeRepo: TvMazeRepo) :
    BaseUseCase<ShowSearchUseCase.Params, ShowSearchUseCase.Result> {

    override suspend fun run(params: Params): Result {
        val response = tvMazeRepo.search(params.search)
        return if (response.isSuccessful) {
            Result(response.body(), response.isSuccessful, response.message())

        } else {
            Result(null, response.isSuccessful, response.message())

        }

    }

    class Params(val search: String)

    class Result(val data: Any?, val isSuccess: Boolean, val errMsg: String)


}