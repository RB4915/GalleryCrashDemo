package com.shadowapps.gallerycrashdemo

import android.app.Application
import com.shadowapps.gallerycrashdemo.activities.CrashActivity
import com.shadowapps.gallerycrashdemo.helpers.GlobalExceptionHandler

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        GlobalExceptionHandler.initialize(this, CrashActivity::class.java)
    }
}