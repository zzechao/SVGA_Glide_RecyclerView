package com.svga.glide

import com.svga.glide.log.DefaultLog
import com.svga.glide.log.ILog
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import java.io.InputStream

object SVGAGlideEx {
    var log: ILog = DefaultLog()

    lateinit var bitmapPool: BitmapPool
    lateinit var arrayPool: ArrayPool
    lateinit var parsers: List<ImageHeaderParser>

    fun register(glide: Glide, registry: Registry, cachePath: String) {
        arrayPool = glide.arrayPool
        bitmapPool = glide.bitmapPool
        parsers = registry.imageHeaderParsers
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