package com.svga.glide

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.util.Util
import com.opensource.svgaplayer.utils.log.LogUtils

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
        var cnt = 0L
        resource.videoItem?.imageMap?.values?.forEach {
            cnt += Util.getBitmapByteSize(it)
        }
        return cnt.toInt().apply {
            LogUtils.debug(TAG, "getSize ${resource.model} size:$this")
        }
    }

    override fun recycle() {
        LogUtils.debug(TAG, "recycle ${resource.model}")
        resource.videoItem?.clear()
    }
}