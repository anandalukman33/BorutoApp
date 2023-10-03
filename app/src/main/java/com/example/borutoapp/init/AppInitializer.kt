package com.example.borutoapp.init

import android.content.Context
import androidx.startup.Initializer
import com.example.borutoapp.BuildConfig
import timber.log.Timber
import timber.log.Timber.DebugTree

@Suppress("unused")
class AppInitializer: Initializer<Unit> {
    override fun create(context: Context) {
        if (!BuildConfig.DEBUG) {
            return
        }
        Timber.plant(DebugTree())
    }

    override fun dependencies(): MutableList<Class<out Initializer<*>>> = mutableListOf()
}