package com.xegami.mac

import android.app.Application

class MyApp : Application() {

    companion object {
        lateinit var instance: MyApp
    }

    override fun onCreate() {
        super.onCreate()

        instance = this
    }
}