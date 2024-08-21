package com.example.glideapp

import android.app.Application
import com.opensource.svgaplayer.utils.log.SVGALogger

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        SVGALogger.setLogEnabled(true)
    }
}