package com.zhouz.glidesvga

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.os.Build
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.opensource.svgaplayer.SVGADynamicEntity

/**
 * Time:2022/11/26 16:07
 * Author: zhouzechao
 * Description:
 */
open class SVGAImageViewDrawableTarget(
    imageView: ImageView, var times: Int = 0,
    var repeatMode: Int = ValueAnimator.RESTART,
    val dynamicItem: SVGADynamicEntity = SVGADynamicEntity(),
    var svgaCallback: SVGACallback2? = null,
    var showLastFrame: Boolean = false
) : CustomViewTarget<ImageView, SVGAResource>(imageView) {

    private val TAG = "SVGAImageViewDrawableTarget"

    init {
        request = null
    }

    override fun onLoadFailed(errorDrawable: Drawable?) {
        clearDrawable("onLoadFailed")
        svgaCallback?.onFailure()
    }

    override fun onResourceReady(
        resource: SVGAResource,
        transition: Transition<in SVGAResource>?
    ) {
        resource.videoItem ?: kotlin.run {
            svgaCallback?.onFailure()
            return
        }
        val drawable = SVGAAnimationDrawable(
            resource.videoItem, times - 1, repeatMode, dynamicItem, showLastFrame
        )
        drawable.tag = resource.model
        drawable.svgaCallback = svgaCallback
        drawable.scaleType = view.scaleType
        view.setImageDrawable(drawable)
        drawable.start()
    }

    override fun onResourceCleared(placeholder: Drawable?) {
        clearDrawable("onResourceCleared")
    }

    private fun clearDrawable(reason: String) {
        (view.drawable as? SVGAAnimationDrawable)?.stop()
        view.setImageDrawable(null)
    }

    override fun onStart() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            (view.drawable as? SVGAAnimationDrawable)?.resume()
        }
    }

    override fun onStop() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            (view.drawable as? SVGAAnimationDrawable)?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDrawable("onDestroy")
    }
}