package com.svga.glide

import com.bumptech.glide.RequestBuilder
import com.bumptech.glide.annotation.GlideExtension
import com.bumptech.glide.annotation.GlideOption
import com.bumptech.glide.annotation.GlideType
import com.bumptech.glide.request.BaseRequestOptions
import com.bumptech.glide.request.RequestOptions

/**
 * Time:2022/11/26 16:35
 * Author: zhouzechao
 * Description:
 */
@GlideExtension
object SVGAExtensions {
    private val DECODE_TYPE = RequestOptions
        .decodeTypeOf(SVGAResource::class.java)
        .lock()

    @JvmStatic
    @GlideType(SVGAResource::class)
    fun asSVGAResource(requestBuilder: RequestBuilder<SVGAResource?>): RequestBuilder<SVGAResource?> {
        return requestBuilder.apply(DECODE_TYPE)
    }

    @JvmStatic
    @GlideOption
    fun setSVGATag(options: BaseRequestOptions<*>, tag: String): BaseRequestOptions<*> {
        return options.set(SVGATAG, tag)
    }
}