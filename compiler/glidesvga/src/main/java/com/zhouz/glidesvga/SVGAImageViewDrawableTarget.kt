package com.zhouz.glidesvga

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.request.target.CustomViewTarget
import com.bumptech.glide.request.transition.Transition
import com.opensource.svgaplayer.SVGACallback2
import com.opensource.svgaplayer.SVGADynamicEntity
import com.opensource.svgaplayer.utils.log.LogUtils

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

    override fun onResourceLoading(placeholder: Drawable?) {
        super.onResourceLoading(placeholder)
        LogUtils.debug(
            TAG, "onResourceLoading ${Thread.currentThread().name} "
        )
    }

    override fun onResourceReady(
        resource: SVGAResource,
        transition: Transition<in SVGAResource>?
    ) {
        LogUtils.debug(
            TAG, "onResourceReady ${Thread.currentThread().name} " +
                    "resource.videoItem:${resource.videoItem == null} " +
                    "showLastFrame:$showLastFrame"
        )
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
        resource.videoItem.prepare({
            drawable.start()
        }, null)
    }

    override fun onResourceCleared(placeholder: Drawable?) {
        clearDrawable("onResourceCleared")
    }

    private fun clearDrawable(reason: String) {
        (view.drawable as? SVGAAnimationDrawable)?.apply {
            LogUtils.debug(TAG, "clearDrawable $reason ${this.tag}")
        }?.apply {
            stop()
        }
        view.setImageDrawable(null)
    }

    override fun onStart() {
        (view.drawable as? SVGAAnimationDrawable)?.apply {
            LogUtils.debug(TAG, "onStart ${this.tag}")
        }?.resume()
    }

    override fun onStop() {
        (view.drawable as? SVGAAnimationDrawable)?.apply {
            LogUtils.debug(TAG, "onStop ${this.tag}")
        }?.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        clearDrawable("onDestroy")
    }
}