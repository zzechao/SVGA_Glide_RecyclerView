package com.svga.glide

import com.bumptech.glide.load.Option
import com.bumptech.glide.load.Options
import com.bumptech.glide.load.ResourceDecoder
import com.bumptech.glide.load.engine.Resource
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.opensource.svgaplayer.SVGAVideoEntity
import com.svga.glide.SVGAGlideEx.log
import java.io.InputStream

/**
 * Time:2022/11/26 16:07
 * Author: zhouzechao
 * Description:
 */
class SVGAResourceStreamDecoder(
    val cachePath: String
) : ResourceDecoder<InputStream, SVGAResource> {

    private val TAG = "SVGAResourceStreamDecoder"

    private val parser by lazy {
        GlideSVGAParser()
    }

    override fun handles(source: InputStream, options: Options): Boolean {
        return true
    }

    override fun decode(
        source: InputStream,
        width: Int,
        height: Int,
        options: Options
    ): Resource<SVGAResource>? {
        val tag = if (!options.get(SVGATAG).isNullOrBlank()) {
            options.get(SVGATAG) ?: ""
        } else {
            "${System.currentTimeMillis()}"
        }
        log.d(
            TAG, "decode source:$source " +
                    "width:$width height:$height options:$tag"
        )
        val requestedWidth = if (width == SIZE_ORIGINAL) {
            0
        } else {
            width
        }
        val requestedHeight = if (height == SIZE_ORIGINAL) {
            0
        } else {
            height
        }
        val svga: SVGAVideoEntity? =
            parser.decodeFromInputStream(
                inputStream = source,
                cachePath,
                requestedWidth,
                requestedHeight, tag
            )
        return SVGAGlideResourceDelegate(
            SVGAResource(svga, options.get(SVGATAG) ?: "", width, height)
        )
    }
}

val SVGATAG = Option.memory("com.bumptech.glide.load.model.stream.HttpGlideUrlLoader.SVGATag", "")