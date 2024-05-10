package com.svga.glide

import android.animation.ValueAnimator
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.R
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
    var detachedToStop: Boolean = true
) : CustomViewTarget<ImageView, SVGAResource>(imageView) {

    private var attachStateChangeListener: View.OnAttachStateChangeListener? = null

    init {
        if (dynamicItem.dynamicHidden.isNotEmpty() ||
            dynamicItem.dynamicImage.isNotEmpty() ||
            dynamicItem.dynamicText.isNotEmpty() ||
            dynamicItem.dynamicTextPaint.isNotEmpty() ||
            dynamicItem.dynamicStaticLayoutText.isNotEmpty() ||
            dynamicItem.dynamicBoringLayoutText.isNotEmpty() ||
            dynamicItem.dynamicDrawer.isNotEmpty() ||
            dynamicItem.dynamicIClickArea.isNotEmpty() ||
            dynamicItem.mClickMap.isNotEmpty() ||
            dynamicItem.dynamicDrawerSized.isNotEmpty()
        ) {
            view.setTag(R.id.glide_custom_view_target_tag, null)
        }
    }

    private val TAG = "SVGAImageViewDrawableTarget"

    override fun onLoadFailed(errorDrawable: Drawable?) {
        clearDrawable()
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
                    "resource.videoItem:${resource.videoItem == null} "
        )
        resource.videoItem ?: kotlin.run {
            svgaCallback?.onFailure()
            return
        }

        val unit = {
            LogUtils.debug(TAG, "onResourceReady unit")
            val drawable =
                SVGAAnimationDrawable(resource.videoItem, times - 1, repeatMode, dynamicItem)
            drawable.tag = resource.model
            drawable.svgaCallback = svgaCallback
            drawable.scaleType = view.scaleType
            view.setImageDrawable(drawable)
            if (detachedToStop) {
                if (attachStateChangeListener == null) {
                    attachStateChangeListener = object : View.OnAttachStateChangeListener {
                        override fun onViewAttachedToWindow(v: View?) {
                            if (times - 1 == ValueAnimator.INFINITE) {
                                (view.drawable as? SVGAAnimationDrawable)?.start()
                            }
                        }

                        override fun onViewDetachedFromWindow(v: View?) {
                            (view.drawable as? SVGAAnimationDrawable)?.stop()
                        }
                    }
                }
                view.addOnAttachStateChangeListener(attachStateChangeListener)
            }
            drawable.start()
        }

        val drawableCur = view.drawable as? SVGAAnimationDrawable
        drawableCur?.let {
            if (it.repeatCount == times - 1 &&
                it.repeatMode == repeatMode &&
                resource.videoItem == it.videoItem
            ) {
                LogUtils.debug(TAG, "onResourceReady same drawable ${drawableCur.tag}")
                it.scaleType = view.scaleType
                it.resetDynamicEntity(dynamicItem)
                it.stop()
                it.start()
            } else {
                LogUtils.debug(TAG, "onResourceReady not same drawable ${resource.model}")
                drawableCur.stop()
                view.setImageDrawable(null)
                unit()
            }
        } ?: kotlin.run {
            LogUtils.debug(TAG, "onResourceReady new drawable ${resource.model}")
            if (resource.videoItem.imageMap.isEmpty()) {
                resource.videoItem.resetCreateImages()
                unit()
            } else {
                unit()
            }
        }
    }

    override fun onResourceCleared(placeholder: Drawable?) {
        clearDrawable()
    }

    private fun clearDrawable() {
        (view.drawable as? SVGAAnimationDrawable)?.apply {
            LogUtils.debug(TAG, "clearDrawable ${this.tag}")
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
        attachStateChangeListener?.let {
            view.removeOnAttachStateChangeListener(attachStateChangeListener)
        }
        (view.drawable as? SVGAAnimationDrawable)?.apply {
            LogUtils.debug(TAG, "onDestroy ${this.tag}")
        }?.apply {
            stop()
        }
        view.setImageDrawable(null)
    }
}