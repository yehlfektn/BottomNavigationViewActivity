package com.csi.bottomnavigationactivity

import android.app.Application
import com.csi.bottomnavigationactivity.di.appModule
import com.csi.bottomnavigationactivity.di.databaseModule
import com.csi.bottomnavigationactivity.utils.debug
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import timber.log.Timber


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        startKoin {
            androidContext(this@MyApp)
            debug {
                androidLogger(Level.ERROR)
            }
            modules(listOf(appModule, databaseModule))
        }
    }
}