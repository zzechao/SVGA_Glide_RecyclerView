package com.opensource.svgaplayer.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx
import com.svga.glide.SVGAGlideEx.bitmapPool


/**
 * Bitmap 解码器
 *
 * <T> 需要加载的数据类型
 *
 * Create by im_dsd 2020/7/7 17:39
 */
internal abstract class SVGABitmapDecoder<T> {

    fun decodeBitmapFrom(data: T, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
            // 如果期望的宽高是合法的, 则开启检测尺寸模式
            inJustDecodeBounds = (reqWidth > 0 && reqHeight > 0)
            inPreferredConfig = Bitmap.Config.RGB_565

            val bitmap = onDecode(data, this, reqWidth, reqHeight)
            if (!inJustDecodeBounds) {
                return bitmap
            }

            // Calculate inSampleSize
            inSampleSize = BitmapSampleSizeCalculator.calculate(this, reqWidth, reqHeight)
            // Decode bitmap with inSampleSize set
            inJustDecodeBounds = false
            onDecode(data, this, reqWidth, reqHeight)
        }
    }

    fun decodeBitmapFrom(data: T, sampleSize: Int, reqWidth: Int, reqHeight: Int): Bitmap? =
        BitmapFactory.Options().run {
            // 如果期望的宽高是合法的, 则开启检测尺寸模式
            inPreferredConfig = Bitmap.Config.RGB_565

            var mSampleSize = sampleSize
            if (mSampleSize == 1) {
                inJustDecodeBounds = (reqWidth > 0 && reqHeight > 0)
                if (inJustDecodeBounds) {
                    decodeBitmapSize(data, this)
                    mSampleSize = BitmapSampleSizeCalculator.calculate(this, reqWidth, reqHeight)
                    if (mSampleSize > sampleSize) {
                        LogUtils.debug(
                            "SVGABitmapDecoder",
                            "decodeBitmapFrom calculate sampleSize:$sampleSize mSampleSize:$mSampleSize"
                        )
                    }
                }
            }

            inJustDecodeBounds = false
            // Calculate inSampleSize
            inSampleSize = mSampleSize

            inMutable = true

            // 设置 ops的图片复用存储区域
            val isKitKatOrGreater: Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N

            if ((inSampleSize == 1 || isKitKatOrGreater) && shouldUsePool()) {
                // ...
                // 根据图片的期望尺寸到 BitmapPool 中获取一个 Bitmap 以复用
                if (reqWidth > 0 && reqHeight > 0) {
                    setInBitmap(this, reqWidth, reqHeight)
                }
            }
            return onDecode(data, this, reqWidth, reqHeight)
        }

    /**
     * 仿写Glide的setInBitmap
     */
    private fun setInBitmap(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int) {
        bitmapPool ?: return
        var expectedConfig: Bitmap.Config? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (options.inPreferredConfig === Bitmap.Config.HARDWARE) {
                return
            }
            expectedConfig = options.outConfig
        }
        if (expectedConfig == null) {
            expectedConfig = options.inPreferredConfig
        }
        // 调用了 inBitmap
        options.inBitmap = bitmapPool?.getDirty(reqWidth, reqHeight, expectedConfig)
    }

    private fun shouldUsePool(): Boolean {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return true
        }
        return false
    }

    abstract fun onDecode(
        data: T,
        ops: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap?

    abstract fun decodeBitmapSize(data: T, ops: BitmapFactory.Options)
}