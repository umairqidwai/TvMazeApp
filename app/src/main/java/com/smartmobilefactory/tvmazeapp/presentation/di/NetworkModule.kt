package com.smartmobilefactory.tvmazeapp.presentation.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.smartmobilefactory.tvmazeapp.BuildConfig
import com.smartmobilefactory.tvmazeapp.data.remote.TvMazeService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit) : TvMazeService {
        return retrofit.create(TvMazeService::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        val okHttpBuilder =  OkHttpClient.Builder()
        okHttpBuilder.addNetworkInterceptor(interceptor)
        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)

        return okHttpBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit{
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

    }

    @Singleton
    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }


}