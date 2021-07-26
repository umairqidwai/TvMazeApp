package com.smartmobilefactory.tvmazeapp.presentation.di

import com.smartmobilefactory.tvmazeapp.data.repo.TvMazeRepoImpl
import com.smartmobilefactory.tvmazeapp.domain.repo.TvMazeRepo
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun provideRepository(tvMazeRepoImpl: TvMazeRepoImpl) : TvMazeRepo

}