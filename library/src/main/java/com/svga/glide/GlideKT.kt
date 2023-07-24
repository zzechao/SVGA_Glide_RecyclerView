package com.svga.glide

import android.view.MotionEvent
import com.opensource.svgaplayer.utils.log.LogUtils

/**
 * Time:2023/3/17 12:02
 * Author: zhouzechao
 * Description:
 */
fun SVGAImageViewDrawableTarget.setSvgaClickMapListener(clickKey: List<String>, listener: (String) -> Unit) {
    view.isClickable = true
    dynamicItem.setClickArea(clickKey)
    view.setOnTouchListener { v, event ->
        if (event?.action != MotionEvent.ACTION_DOWN) {
            return@setOnTouchListener false
        }
        LogUtils.debug("GlideKT", "setOnTouchListener event?.action:${event.action} x:${event.x} y:${event.y}")
        for ((key, value) in dynamicItem.mClickMap) {
            LogUtils.debug("GlideKT", "for key:${key} value:${value[0]}-${value[1]}-${value[2]}-${value[3]}")
            if (event.x >= value[0] && event.x <= value[2] && event.y >= value[1] && event.y <= value[3]) {
                listener.invoke(key)
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }
}

