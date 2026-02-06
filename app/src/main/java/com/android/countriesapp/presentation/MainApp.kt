package com.android.countriesapp.presentation

import android.app.Application
import com.android.countriesapp.dependencyinjection.dataModule
import com.android.countriesapp.dependencyinjection.domainModule
import com.android.countriesapp.dependencyinjection.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApp)
            modules(
                presentationModule,
                dataModule,
                domainModule
            )
        }
    }
}
