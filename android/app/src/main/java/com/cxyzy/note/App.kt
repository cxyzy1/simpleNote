package com.cxyzy.note

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.cxyzy.note.utils.logger.initLogger
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        context = this
        initLogger(BuildConfig.DEBUG)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(appModule)
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

}