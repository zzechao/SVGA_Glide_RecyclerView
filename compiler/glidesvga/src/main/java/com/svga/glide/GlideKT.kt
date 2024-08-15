package com.svga.glide

import android.annotation.SuppressLint
import android.view.MotionEvent
import com.svga.glide.SVGAGlideEx.log
import com.svga.glide.util.ReflectUtils

/**
 * Time:2023/3/17 12:02
 * Author: zhouzechao
 * Description:
 */
@SuppressLint("ClickableViewAccessibility")
fun SVGAImageViewDrawableTarget.setSvgaClickMapListener(clickKey: List<String>, listener: (String) -> Unit) {
    view.isClickable = true
    dynamicItem.setClickArea(clickKey)
    val map = ReflectUtils.reflect(dynamicItem)?.field("mClickMap")?.get<HashMap<String, IntArray>>() ?: return
    view.setOnTouchListener { v, event ->
        if (event?.action != MotionEvent.ACTION_DOWN) {
            return@setOnTouchListener false
        }
        log.d("GlideKT", "setOnTouchListener event?.action:${event.action} x:${event.x} y:${event.y}")
        for ((key, value) in map) {
            log.d("GlideKT", "for key:${key} value:${value[0]}-${value[1]}-${value[2]}-${value[3]}")
            if (event.x >= value[0] && event.x <= value[2] && event.y >= value[1] && event.y <= value[3]) {
                listener.invoke(key)
                return@setOnTouchListener true
            }
        }
        return@setOnTouchListener false
    }
}

