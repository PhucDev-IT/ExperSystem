package vn.mobile.expersystem

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate

class MyApplication : Application() {

    val context: Context get() = instance.applicationContext

    init { instance = this }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }


    companion object {
        const val CHANNEL_ID = "CHANNEL_1"
        @SuppressLint("StaticFieldLeak")
        @get:Synchronized
        var instance:MyApplication = MyApplication()
    }

}