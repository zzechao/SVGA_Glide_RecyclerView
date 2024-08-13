package com.svga.glide

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.util.Util
import com.svga.glide.SVGAGlideEx.bitmapPool
import com.svga.glide.SVGAGlideEx.log

/**
 * Time:2022/11/26 22:38
 * Author: zhouzechao
 * Description:
 */
class SVGAGlideResourceDelegate(private val resource: SVGAResource) : Resource<SVGAResource> {

    private val TAG = "SVGAGlideResourceDelegate"

    override fun getResourceClass(): Class<SVGAResource> {
        return SVGAResource::class.java
    }

    override fun get(): SVGAResource {
        return resource
    }

    override fun getSize(): Int {
        var cnt = 0
        val map = resource.imageMapField?.get<HashMap<String, Bitmap>>()
        map?.values?.forEach {
            cnt += Util.getBitmapByteSize(it)
        }
        return cnt
    }

    override fun recycle() {
        log.d(TAG, "recycle ${resource.model}")
        resource.videoItem?.movieItem = null
        bitmapPool.let { pool ->
            val map = resource.imageMapField?.get<HashMap<String, Bitmap>>()
            log.d(TAG, "recycle ${map?.size}")
            map?.forEach {
                pool.put(it.value)
            }
        }
        resource.videoItem?.clear()
    }
}