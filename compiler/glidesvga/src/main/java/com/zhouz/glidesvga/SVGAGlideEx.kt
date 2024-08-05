package com.zhouz.glidesvga

import android.os.Build
import com.zhouz.glidesvga.log.DefaultLog
import com.zhouz.glidesvga.log.ILog
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.io.InputStream

object SVGAGlideEx {
    var log: ILog = DefaultLog()


    var bitmapPool: BitmapPool? = null
        get() {
            return if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {
                field
            } else {
                null
            }
        }
    lateinit var arrayPool: ArrayPool

    fun register(glide: Glide, registry: Registry, cachePath: String) {
        arrayPool = glide.arrayPool
        bitmapPool = glide.bitmapPool
        registry.prepend(
            Registry.BUCKET_ANIMATION,
            InputStream::class.java, SVGAResource::class.java,
            SVGAResourceStreamDecoder(cachePath)
        )
    }

    fun initLog(iLog: ILog) {
        log = iLog
    }
}