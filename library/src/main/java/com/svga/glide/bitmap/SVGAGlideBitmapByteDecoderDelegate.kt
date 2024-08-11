package com.svga.glide.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx
import com.svga.glide.SVGAGlideEx.bitmapPool
import com.svga.glide.SVGAGlideEx.fixBitmapToRequestedDimensions
import com.svga.glide.SVGAGlideEx.parsers
import java.io.IOException
import java.nio.ByteBuffer
import kotlin.math.ceil

private const val TAG = "SVGAGlideBitmapByteDecoderDelegate"

object SVGAGlideBitmapByteDecoderDelegate : ISVGABitmapDecoderDelegate<ByteArray> {

    override fun decodeBitmapFrom(data: ByteArray, reqWidth: Int, reqHeight: Int): Bitmap? {
        val options = BitmapFactory.Options().apply {
            // 如果期望的宽高是合法的, 则开启检测尺寸模式
            inJustDecodeBounds = (reqWidth > 0 && reqHeight > 0)
            inPreferredConfig = Bitmap.Config.RGB_565

            val bitmap = onDecode(data, this)
            if (!inJustDecodeBounds) {
                return bitmap
            }

            val sourceWidth: Int = outWidth
            val sourceHeight: Int = outHeight

            inSampleSize = attachSampleSize(this, reqWidth, reqHeight)
            // Decode bitmap with inSampleSize set
            inJustDecodeBounds = false


            var isHardwareConfigAllowed: Boolean

            if (sourceWidth == -1 || sourceHeight == -1) {
                isHardwareConfigAllowed = false
            }

            val targetWidth = if (reqWidth == SIZE_ORIGINAL || reqWidth == 0) {
                sourceWidth
            } else reqWidth

            val targetHeight = if (reqHeight == SIZE_ORIGINAL || reqHeight == 0) {
                sourceHeight
            } else reqHeight

            val imageType: ImageType = getImageType(data)


            val isKitKatOrGreater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
            if ((inSampleSize == 1 || isKitKatOrGreater) && shouldUsePool(imageType)) {
                var expectedWidth: Int
                var expectedHeight: Int
                if (sourceWidth >= 0 && sourceHeight >= 0 && fixBitmapToRequestedDimensions && isKitKatOrGreater) {
                    expectedWidth = targetWidth
                    expectedHeight = targetHeight
                } else {
                    val densityMultiplier =
                        if (isScaling(this)) inTargetDensity * 1f / inDensity else 1f
                    val sampleSize = inSampleSize
                    val downSampledWidth = ceil(sourceWidth / sampleSize.toFloat())
                    val downSampledHeight = ceil(sourceHeight / sampleSize.toFloat());
                    expectedWidth = Math.round(downSampledWidth * densityMultiplier)
                    expectedHeight = Math.round(downSampledHeight.times(densityMultiplier))
                }
            }
        }

        if (SVGAGlideEx::bitmapPool.isLateinit) {
            var result: Bitmap? = null
            TransformationUtils.getBitmapDrawableLock().lock()
            try {
                // 数据加载
                LogUtils.debug(TAG, "onDecode 3")
                result = BitmapFactory.decodeByteArray(data, 0, data.count(), options)
            } catch (e: IllegalArgumentException) {
                if (options.inBitmap != null) {
                    try {
                        // 输入流重置
                        bitmapPool.put(options.inBitmap)
                        // 清理掉 inBitmap 并进行第二次加载
                        options.inBitmap = null
                        // 再次调用进行加载
                        LogUtils.debug(TAG, "onDecode 4")
                        result = BitmapFactory.decodeByteArray(data, 0, data.count(), options)
                    } catch (resetException: IOException) {
                        LogUtils.error(TAG, "onDecode 5")
                    }
                }
            } finally {
                TransformationUtils.getBitmapDrawableLock().unlock()
            }
            return result
        } else {
            LogUtils.debug(TAG, "onDecode 7")
            return BitmapFactory.decodeByteArray(data, 0, data.count())
        }
    }

    override fun onDecode(data: ByteArray, ops: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
    }

    override fun getImageType(data: ByteArray): ImageHeaderParser.ImageType {
        return ImageHeaderParserUtils.getType(parsers, ByteBuffer.wrap(data))
    }
}