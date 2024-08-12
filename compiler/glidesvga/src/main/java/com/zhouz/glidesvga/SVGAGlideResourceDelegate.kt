package com.zhouz.glidesvga

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.util.Util
import com.zhouz.glidesvga.SVGAGlideEx.bitmapPool
import com.zhouz.glidesvga.SVGAGlideEx.log
import com.zhouz.glidesvga.util.ReflectUtils

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
        return cnt
    }

    override fun recycle() {
        log.d(TAG, "recycle ${resource.model}")
        resource.videoItem?.movieItem = null
        resource.videoItem?.clear()
    }
}