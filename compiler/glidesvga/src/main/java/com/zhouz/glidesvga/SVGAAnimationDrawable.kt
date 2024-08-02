package com.zhouz.glidesvga

import android.animation.Animator
import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.ColorFilter
import android.graphics.PixelFormat
import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.os.Build
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.opensource.svgaplayer.SVGACallback2
import com.opensource.svgaplayer.SVGADynamicEntity
import com.opensource.svgaplayer.SVGASoundManager
import com.opensource.svgaplayer.SVGAVideoEntity
import com.opensource.svgaplayer.drawer.SVGACanvasDrawer
import com.opensource.svgaplayer.utils.log.LogUtils

/**
 * 当同一个SVGA图片被加载的时候 如果此时svga动画在运行中他们会共享同样的动画效果
 *
 * ***/
class SVGAAnimationDrawable(
    val videoItem: SVGAVideoEntity,
    val repeatCount: Int,
    val repeatMode: Int,
    val dynamicItem: SVGADynamicEntity,
    var showLastFrame: Boolean = false
) : Animatable, Drawable(),
    ValueAnimator.AnimatorUpdateListener, Animator.AnimatorListener {

    private val TAG = "SVGAAnimationDrawable"

    // 上层传下来的识别码
    var tag = ""

    var svgaCallback: SVGACallback2? = null

    private var mAnimator: ValueAnimator? = null
    private var currentFrame = -1

    //一共有多少帧
    private var totalFrame = 0

    private var isInitiativePause = false

    private var drawer = SVGACanvasDrawer(videoItem, dynamicItem).apply {
        scaleBySelf = false
    }

    var scaleType = ImageView.ScaleType.MATRIX
    var isStop: Boolean = false

    fun resetDynamicEntity(dynamicItem: SVGADynamicEntity) {
        drawer = SVGACanvasDrawer(videoItem, dynamicItem).apply {
            scaleBySelf = false
        }
    }

    override fun getIntrinsicWidth(): Int {
        return videoItem.videoSize.width.toInt()
    }

    override fun getIntrinsicHeight(): Int {
        return videoItem.videoSize.height.toInt()
    }

    override fun start() {
        if (mAnimator == null || mAnimator?.isRunning == false) {
            LogUtils.debug(TAG, "start start $tag")
            val startFrame = 0
            val endFrame = videoItem.frames - 1
            totalFrame = (endFrame - startFrame + 1)
            mAnimator?.cancel()
            mAnimator = null
            mAnimator = ValueAnimator.ofInt(startFrame, endFrame)
            mAnimator?.interpolator = LinearInterpolator()
            mAnimator?.duration = (totalFrame * (1000 / videoItem.FPS) / generateScale()).toLong()
            mAnimator?.repeatCount = repeatCount
            mAnimator?.repeatMode = repeatMode
            mAnimator?.addUpdateListener(this)
            mAnimator?.addListener(this)
            mAnimator?.start()
        } else {
            LogUtils.debug(TAG, "start resume $tag")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mAnimator?.resume()
            } else {
                mAnimator?.start()
            }
        }
    }

    private fun generateScale(): Double {
        var scale = 1.0
        try {
            val animatorClass = Class.forName("android.animation.ValueAnimator") ?: return scale
            val getMethod = animatorClass.getDeclaredMethod("getDurationScale") ?: return scale
            scale = (getMethod.invoke(animatorClass) as Float).toDouble()
            if (scale == 0.0) {
                val setMethod =
                    animatorClass.getDeclaredMethod("setDurationScale", Float::class.java)
                        ?: return scale
                setMethod.isAccessible = true
                setMethod.invoke(animatorClass, 1.0f)
                scale = 1.0
            }
        } catch (ignore: Exception) {
            ignore.printStackTrace()
        }
        return scale
    }


    override fun setVisible(visible: Boolean, restart: Boolean): Boolean {
        return super.setVisible(visible, restart)
    }

    override fun stop() {
        if (mAnimator != null) {
            LogUtils.debug(TAG, "stop $tag")
            isStop = true
            mAnimator?.cancel()
            mAnimator?.removeAllListeners()
            mAnimator?.removeAllUpdateListeners()
            mAnimator = null

            videoItem.audioList.forEach { audio ->
                audio.playID?.let {
                    if (SVGASoundManager.isInit()) {
                        SVGASoundManager.stop(it)
                    } else {
                        videoItem.soundPool?.stop(it)
                    }
                }
            }
        }
    }

    override fun isRunning(): Boolean {
        return mAnimator?.isRunning == true
    }

    override fun draw(canvas: Canvas) {
        if (currentFrame > -1) {
            drawer.drawFrame(canvas, currentFrame, scaleType)
        }
    }

    override fun setBounds(left: Int, top: Int, right: Int, bottom: Int) {
        super.setBounds(left, top, right, bottom)
    }

    override fun setAlpha(alpha: Int) {
    }

    override fun setColorFilter(colorFilter: ColorFilter?) {
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSPARENT
    }

    override fun onAnimationUpdate(animation: ValueAnimator?) {
        val frame = animation?.animatedValue as Int
        if (currentFrame != frame) {
            currentFrame = frame
            invalidateSelf()
            val percentage = (currentFrame + 1).toDouble() / videoItem.frames.toDouble()
            svgaCallback?.onStep(currentFrame, percentage)
        }
    }

    fun pause(isInitiative: Boolean = false) {
        if (mAnimator?.isStarted == true && mAnimator?.isPaused == false) {
            if (isInitiative) {
                isInitiativePause = true
            }
            LogUtils.debug(TAG, "pause $tag")
            mAnimator?.pause()
            svgaCallback?.onPause()
            videoItem.audioList.forEach { audio ->
                audio.playID?.let {
                    if (SVGASoundManager.isInit()) {
                        SVGASoundManager.pause(it)
                    } else {
                        videoItem.soundPool?.pause(it)
                    }
                }
                audio.playID = null
            }
        }
    }

    fun resume(isInitiative: Boolean = false) {
        if (this.isInitiativePause && !isInitiative) {
            return
        }
        isInitiativePause = false
        if (mAnimator?.isStarted == true && mAnimator?.isPaused == true) {
            mAnimator?.resume()
            svgaCallback?.onResume()
            LogUtils.debug(TAG, "resume $tag")
            videoItem.audioList.forEach { audio ->
                audio.playID?.let {
                    if (SVGASoundManager.isInit()) {
                        SVGASoundManager.resume(it)
                    } else {
                        videoItem.soundPool?.resume(it)
                    }
                }
                audio.playID = null
            }
        }
    }

    fun clear() {
        videoItem.audioList.forEach { audio ->
            audio.playID?.let {
                if (SVGASoundManager.isInit()) {
                    SVGASoundManager.stop(it)
                } else {
                    videoItem.soundPool?.stop(it)
                }
            }
            audio.playID = null
        }
        videoItem.clear()
    }

    override fun onAnimationStart(animation: Animator?) {
        svgaCallback?.onStart()
    }

    override fun onAnimationEnd(animation: Animator?) {
        svgaCallback?.onFinished()
        if (!showLastFrame) {
            currentFrame = -1
            invalidateSelf()
        }
    }

    override fun onAnimationCancel(animation: Animator?) {
        currentFrame = -1
        invalidateSelf()
    }

    override fun onAnimationRepeat(animation: Animator?) {
        svgaCallback?.onRepeat()
    }
}