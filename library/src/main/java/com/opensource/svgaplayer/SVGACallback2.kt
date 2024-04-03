package com.opensource.svgaplayer

/**
 * Created by cuiminghui on 2017/3/30.
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