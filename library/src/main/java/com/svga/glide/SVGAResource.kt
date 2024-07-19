package com.svga.glide

import android.view.View
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
    val height: Int,
    var attachStateChangeListener: View.OnAttachStateChangeListener? = null
)