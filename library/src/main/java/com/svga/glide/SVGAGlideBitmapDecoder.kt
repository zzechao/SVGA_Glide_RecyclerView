package com.svga.glide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.bitmapPool
import com.svga.glide.SVGAGlideEx.fixBitmapToRequestedDimensions
import com.svga.glide.SVGAGlideEx.parsers
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer
import java.util.Collections
import java.util.EnumSet
import kotlin.math.ceil


/**
 * @author:zhouz
 * @date: 2024/8/9 17:52
 * description：
 */
private const val TAG = "SVGAGlideBitmapDecoder"

object SVGAGlideBitmapDecoder {

    private val TYPES_THAT_USE_POOL_PRE_KITKAT: Set<ImageType> = Collections.unmodifiableSet(
        EnumSet.of(
            ImageType.JPEG, ImageType.PNG_A, ImageType.PNG
        )
    )

    fun onDecode(
        data: ByteArray, options: BitmapFactory.Options, requestedWidth: Int, requestedHeight: Int
    ): Bitmap? {
        if (::bitmapPool.isLateinit) {
            var result: Bitmap? = null
            val sourceWidth: Int = options.outWidth
            val sourceHeight: Int = options.outHeight
            var isHardwareConfigAllowed: Boolean

            if (sourceWidth == -1 || sourceHeight == -1) {
                isHardwareConfigAllowed = false
            }

            val targetWidth = if (requestedWidth == SIZE_ORIGINAL || requestedWidth == 0) {
                sourceWidth
            } else requestedWidth

            val targetHeight = if (requestedHeight == SIZE_ORIGINAL || requestedHeight == 0) {
                sourceHeight
            } else requestedHeight

            val imageType: ImageType = getImageType(data)
            val isKitKatOrGreater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
            if ((options.inSampleSize == 1 || isKitKatOrGreater) && shouldUsePool(imageType)) {
                var expectedWidth: Int
                var expectedHeight: Int
                if (sourceWidth >= 0 && sourceHeight >= 0 && fixBitmapToRequestedDimensions && isKitKatOrGreater) {
                    expectedWidth = targetWidth;
                    expectedHeight = targetHeight;
                } else {
                    val densityMultiplier =
                        if (isScaling(options)) options.inTargetDensity * 1f / options.inDensity else 1f
                    val sampleSize = options.inSampleSize
                    val downsampledWidth = ceil(sourceWidth / sampleSize.toFloat())
                    val downsampledHeight = ceil(sourceHeight / sampleSize.toFloat());
                    expectedWidth = Math.round(downsampledWidth * densityMultiplier)
                    expectedHeight = Math.round(downsampledHeight.times(densityMultiplier))
                }
            }


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
            return BitmapFactory.decodeByteArray(data, 0, data.count(), options)
        }
    }

    /**
     * bytes获取imageType
     */
    private fun getImageType(bytes: ByteArray): ImageType {
        return ImageHeaderParserUtils.getType(parsers, ByteBuffer.wrap(bytes))
    }

    /**
     * bytes获取ImageOrientation
     */
    private fun getImageOrientation(bytes: ByteArray): Int {
        return ImageHeaderParserUtils.getOrientation(
            parsers, ByteBuffer.wrap(bytes), SVGAGlideEx.arrayPool
        )
    }

    /**
     * file获取ImageOrientation
     */
    private fun getImageOrientation(file: File): Int {
        var `is`: InputStream? = null
        try {
            `is` = RecyclableBufferedInputStream(FileInputStream(file), SVGAGlideEx.arrayPool)
            return ImageHeaderParserUtils.getOrientation(parsers, `is`, SVGAGlideEx.arrayPool)
        } finally {
            if (`is` != null) {
                try {
                    `is`.close()
                } catch (_: IOException) {
                }
            }
        }
    }

    private fun isRotationRequired(degreesToRotate: Int): Boolean {
        return degreesToRotate == 90 || degreesToRotate == 270
    }

    private fun shouldUsePool(imageType: ImageType): Boolean {
        // On KitKat+, any bitmap (of a given config) can be used to decode any other bitmap
        // (with the same config).
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return true
        }

        // We cannot reuse bitmaps when decoding images that are not PNG or JPG prior to KitKat.
        // See: https://groups.google.com/forum/#!msg/android-developers/Mp0MFVFi1Fo/e8ZQ9FGdWdEJ
        return TYPES_THAT_USE_POOL_PRE_KITKAT.contains(imageType)
    }

    private fun isScaling(options: BitmapFactory.Options): Boolean {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity
    }
}