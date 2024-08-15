package com.example.glideapp.ui

import android.os.Handler
import android.os.HandlerThread
import android.os.Process

object Unit {
    var thunderHandler: Handler? = null
    private var handlerThread: HandlerThread? = null

    init {
        handlerThread =
            HandlerThread("bitmaploader", Process.THREAD_PRIORITY_BACKGROUND)
        handlerThread?.start()
        thunderHandler = Handler(handlerThread!!.looper)
    }
}