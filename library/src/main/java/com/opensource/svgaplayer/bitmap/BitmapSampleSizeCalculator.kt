package com.opensource.svgaplayer.bitmap

import android.graphics.BitmapFactory

/**
 *
 * Create by im_dsd 2020/7/7 17:59
 */
internal object BitmapSampleSizeCalculator {

    fun calculate(options: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Int {
        // Raw height and width of image
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
        return calculate(height, width, reqWidth, reqHeight)
    }

    /**
     * https://developer.android.com/topic/performance/graphics/load-bitmap
     * 采用率计算方法
     */
    fun calculate(height: Int, width: Int, reqWidth: Int, reqHeight: Int): Int {
        var inSampleSize = 1

        if (reqHeight <= 0 || reqWidth <= 0) {
            return inSampleSize
        }
        if (height > reqHeight || width > reqWidth) {

            val halfHeight: Int = height / 2
            val halfWidth: Int = width / 2

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while (halfHeight / inSampleSize >= reqHeight && halfWidth / inSampleSize >= reqWidth) {
                inSampleSize *= 2
            }
        }

        return inSampleSize
    }
}