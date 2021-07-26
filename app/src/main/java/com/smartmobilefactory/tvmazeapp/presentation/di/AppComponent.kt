package com.smartmobilefactory.tvmazeapp.presentation.di

import com.smartmobilefactory.tvmazeapp.presentation.fragments.TvShowSearchFragment
import com.smartmobilefactory.tvmazeapp.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(DataModule::class)])
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: TvShowSearchFragment)

}