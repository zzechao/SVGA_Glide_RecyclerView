package com.svga.glide

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.util.Util
import com.svga.glide.SVGAGlideEx.bitmapPool

/**
 * Time:2022/11/26 22:38
 * Author: zhouzechao
 * Description:
 */
class SVGAGlideResourceDelegate(private val resource: SVGAResource) : Resource<SVGAResource> {

    override fun getResourceClass(): Class<SVGAResource> {
        return SVGAResource::class.java
    }

    override fun get(): SVGAResource {
        return resource
    }

    override fun getSize(): Int {
        var cnt = 0
        resource.videoItem?.imageMap?.values?.forEach {
            cnt += Util.getBitmapByteSize(it)
        }
        return cnt
    }

    override fun recycle() {
        resource.videoItem?.movieItem = null
        bitmapPool.let { pool ->
            resource.videoItem?.imageMap?.forEach {
                pool.put(it.value)
            }
        }
        resource.videoItem?.imageMap?.clear()
        resource.videoItem?.clear()
    }
}