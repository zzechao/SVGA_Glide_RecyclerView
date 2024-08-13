package com.svga.glide

import com.opensource.svgaplayer.SVGAVideoEntity


/**
 * Time:2022/11/26 13:59
 * Author: zhouzechao
 * Description:
 */
data class SVGAResource(
    val videoItem: SVGAVideoEntity?,
    val model: String,
    val width: Int,
    val height: Int
) {
    val imageMapField: com.svga.glide.util.ReflectUtils? by lazy {
        try {
            com.svga.glide.util.ReflectUtils.reflect(videoItem).field("imageMap")
        } catch (e: Throwable) {
            null
        }
    }
}