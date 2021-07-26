package com.smartmobilefactory.tvmazeapp.domain.interactors.base

interface BaseUseCase<P, R> {

    suspend fun run(params: P): R
}