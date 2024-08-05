package com.zhouz.glidesvga

import com.opensource.svgaplayer.SVGACallback


/**
 * @author:zhouz
 * @date: 2024/8/2 18:47
 * description：针对glide 加载
 */
interface SVGACallback2 : SVGACallback {

    fun onStart() {}

    // 只有Glide方法加载才有回调
    fun onResume() {}

    // 只有Glide方法加载才有回调
    fun onFailure() {}

    override fun onPause() {}

    override fun onFinished() {}

    override fun onRepeat() {}

    override fun onStep(frame: Int, percentage: Double) {}
}