package com.anton.dobrogorsky.usersposts.app

import android.app.Application
import com.anton.dobrogorsky.usersposts.di.Modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {

    companion object {
        val BASE_URL = "https://jsonplaceholder.typicode.com"
    }

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            koin.loadModules(listOf(
                Modules.viewModelModule,
                Modules.networkModule,
                Modules.repositoryModule
            ))
            koin.createRootScope()
        }
    }

}