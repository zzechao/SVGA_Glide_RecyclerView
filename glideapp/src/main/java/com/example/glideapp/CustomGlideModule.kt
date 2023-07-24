package com.example.glideapp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.svga.glide.SVGAResource
import com.svga.glide.SVGAResourceStreamDecoder
import java.io.File
import java.io.InputStream

/**
 * @author YvesCheung
 * 2019/3/29
 */
@GlideModule
class CustomGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)


        val cachePath = context.cacheDir.absolutePath + File.separatorChar + "glide-svga"
        //registry.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())

        registry.prepend(
            Registry.BUCKET_ANIMATION,
            InputStream::class.java, SVGAResource::class.java,
            SVGAResourceStreamDecoder(cachePath, glide)
        )
    }
}