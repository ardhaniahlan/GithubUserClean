package org.apps.githubuser

import android.app.Application
import org.apps.core.di.databaseModule
import org.apps.core.di.networkModule
import org.apps.core.di.repositoryModule
import org.apps.githubuser.ui.di.useCaseModule
import org.apps.githubuser.ui.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule,
                )
            )
        }
    }
}