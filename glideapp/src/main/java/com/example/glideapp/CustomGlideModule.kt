package com.example.glideapp

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule
import com.zhouz.glidesvga.SVGAGlideEx
import java.io.File

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
        SVGAGlideEx.register(glide, registry, cachePath)
    }
}