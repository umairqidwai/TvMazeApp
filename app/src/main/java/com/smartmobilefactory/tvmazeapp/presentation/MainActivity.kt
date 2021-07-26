package com.smartmobilefactory.tvmazeapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smartmobilefactory.tvmazeapp.R
import com.smartmobilefactory.tvmazeapp.presentation.di.AppComponent
import com.smartmobilefactory.tvmazeapp.presentation.di.DaggerAppComponent

class MainActivity : AppCompatActivity() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}