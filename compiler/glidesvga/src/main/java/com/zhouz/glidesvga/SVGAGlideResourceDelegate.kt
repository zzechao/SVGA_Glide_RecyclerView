package com.zhouz.glidesvga

import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.util.Util
import com.zhouz.glidesvga.SVGAGlideEx.bitmapPool
import com.zhouz.glidesvga.SVGAGlideEx.log

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
//        resource.videoItem?.imageMap?.values?.forEach {
//            cnt += Util.getBitmapByteSize(it)
//        }
        return cnt.toInt().apply {
            log.d(TAG, "getSize ${resource.model} size:$this")
        }
    }

    override fun recycle() {
        log.d(TAG, "recycle ${resource.model}")
        resource.videoItem?.movieItem = null
//        bitmapPool?.let { pool ->
//            resource.videoItem?.imageMap?.forEach {
//                pool.put(it.value)
//            }
//        }
//        resource.videoItem?.imageMap?.clear()
        resource.videoItem?.clear()
    }
}