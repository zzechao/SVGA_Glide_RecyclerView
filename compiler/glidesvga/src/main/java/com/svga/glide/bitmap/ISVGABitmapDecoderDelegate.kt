package com.svga.glide.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.svga.glide.SVGAGlideEx
import com.svga.glide.SVGAGlideEx.bitmapPool
import com.svga.glide.SVGAGlideEx.log
import java.io.IOException
import java.util.Collections
import java.util.EnumSet
import kotlin.math.ceil
import kotlin.math.max


private const val TAG = "ISVGABitmapDecoderDelegate"

private val TYPES_THAT_USE_POOL_PRE_KITKAT: Set<ImageType> = Collections.unmodifiableSet(
    EnumSet.of(
        ImageType.JPEG, ImageType.PNG_A, ImageType.PNG
    )
)

/**
 * com.opensource.svgaplayer.bitmap.SVGABitmapDecoder
 */
interface ISVGABitmapDecoderDelegate<T> {

    var timeMillis: Long

    fun decodeBitmapFrom(data: T, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
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

            onDecode(data, this)

//            val imageType: ImageType = getImageType(data)
//
//            var userInBitmap = false
//
//            /**
//             * com.bumptech.glide.load.resource.bitmap.Downsampler.decodeFromWrappedStreams
//             */
//            val isKitKatOrGreater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
//            var expectedWidth = 0
//            var expectedHeight = 0
//            if ((inSampleSize == 1 || isKitKatOrGreater) && shouldUsePool(imageType)) {
//
//                val targetWidth = if (reqWidth == SIZE_ORIGINAL || reqWidth == 0) {
//                    sourceWidth
//                } else reqWidth
//                val targetHeight = if (reqHeight == SIZE_ORIGINAL || reqHeight == 0) {
//                    sourceHeight
//                } else reqHeight
//
//                val adjustedScaleFactor: Float = getScaleFactor(sourceWidth, sourceHeight, targetWidth, targetHeight)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    this.inTargetDensity = adjustTargetDensityForError(adjustedScaleFactor)
//                    this.inDensity = getDensityMultiplier(adjustedScaleFactor)
//                }
//                if (isScaling(this)) {
//                    this.inScaled = true
//                } else {
//                    this.inTargetDensity = 0
//                    this.inDensity = this.inTargetDensity
//                }
//                val densityMultiplier =
//                    if (isScaling(this)) inTargetDensity * 1f / inDensity else 1f
//                val sampleSize = inSampleSize
//                val downSampledWidth = ceil(sourceWidth / sampleSize.toFloat())
//                val downSampledHeight = ceil(sourceHeight / sampleSize.toFloat());
//                expectedWidth = Math.round(downSampledWidth * densityMultiplier)
//                expectedHeight = Math.round(downSampledHeight.times(densityMultiplier))
//
//                // If this isn't an image, or BitmapFactory was unable to parse the size, width and height
//                // will be -1 here.
//                if (expectedWidth > 0 && expectedHeight > 0) {
//                    userInBitmap = setInBitmap(this, expectedWidth, expectedHeight)
//                }
//            }
//            if (userInBitmap) {
//                var result: Bitmap? = null
//                TransformationUtils.getBitmapDrawableLock().lock()
//                try {
//                    // 数据加载
//                    logger("onDecode use inBitmap expectedWidth:$expectedWidth expectedHeight:$expectedHeight")
//                    result = onDecode(data, this)
//                } catch (e: IllegalArgumentException) {
//                    if (this.inBitmap != null) {
//                        try {
//                            bitmapPool.put(this.inBitmap)
//                            // 清理掉 inBitmap 并进行第二次加载
//                            this.inBitmap = null
//                            // 再次调用进行加载
//                            result = onDecode(data, this)
//                        } catch (resetException: IOException) {
//                            log.e(TAG, "onDecode error", resetException)
//                        }
//                    }
//                } finally {
//                    TransformationUtils.getBitmapDrawableLock().unlock()
//                }
//                result
//            } else {
//                onDecode(data, this)
//            }
        }
    }

    private fun logger(msg: String) {
        if (System.currentTimeMillis() - timeMillis > 5000L) {
            timeMillis = System.currentTimeMillis()
            log.d(TAG, msg)
        }
    }

    fun onDecode(data: T, options: BitmapFactory.Options): Bitmap?

    /**
     * com.bumptech.glide.load.resource.bitmap.ImageReader.getImageType
     */
    fun getImageType(data: T): ImageType

    /**
     * 采样率计算
     */
    private fun attachSampleSize(
        options: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Int {
        // Raw height and width of image
        val (height: Int, width: Int) = options.run { outHeight to outWidth }
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


    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.shouldUsePool
     */
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

    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.isScaling
     */
    private fun isScaling(options: BitmapFactory.Options): Boolean {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity
    }

    /**
     * com.bumptech.glide.load.resource.bitmap.DownsampleStrategy.getScaleFactor
     * 这里只处理缩小，不处理放大
     */
    private fun getScaleFactor(
        sourceWidth: Int, sourceHeight: Int, requestedWidth: Int, requestedHeight: Int
    ): Float {
        val widthPercentage = requestedWidth / sourceWidth.toFloat()
        val heightPercentage = requestedHeight / sourceHeight.toFloat()
        return if (widthPercentage >= 1f || heightPercentage >= 1f) {
            1f
        } else {
            max(widthPercentage.toDouble(), heightPercentage.toDouble()).toFloat()
        }
    }

    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.round
     */
    private fun round(value: Float): Int {
        return (value + 0.5).toInt()
    }

    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.adjustTargetDensityForError
     */
    private fun adjustTargetDensityForError(adjustedScaleFactor: Float): Int {
        val densityMultiplier = getDensityMultiplier(adjustedScaleFactor)
        val targetDensity = round(densityMultiplier * adjustedScaleFactor)
        val scaleFactorWithError = targetDensity / densityMultiplier.toFloat()
        val difference = adjustedScaleFactor / scaleFactorWithError
        return round(difference * targetDensity)
    }

    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.getDensityMultiplier
     */
    private fun getDensityMultiplier(adjustedScaleFactor: Float): Int {
        return Math.round(
            Int.MAX_VALUE * (if (adjustedScaleFactor <= 1.0) adjustedScaleFactor else 1 / adjustedScaleFactor)
        )
    }

    val isUseBitmap: Boolean get() = false

    /**
     * com.bumptech.glide.load.resource.bitmap.Downsampler.setInBitmap
     */
    private fun setInBitmap(
        options: BitmapFactory.Options, width: Int, height: Int
    ): Boolean {
        if (!isUseBitmap) return false
        try {
            var expectedConfig: Bitmap.Config? = null
            // Avoid short circuiting, it appears to break on some devices.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                if (options.inPreferredConfig == Bitmap.Config.HARDWARE) {
                    return false
                }
                // On API 26 outConfig may be null for some images even if the image is valid, can be decoded
                // and outWidth/outHeight/outColorSpace are populated (see b/71513049).
                expectedConfig = options.outConfig
            }

            if (expectedConfig == null) {
                // We're going to guess that BitmapFactory will return us the config we're requesting. This
                // isn't always the case, even though our guesses tend to be conservative and prefer configs
                // of larger sizes so that the Bitmap will fit our image anyway. If we're wrong here and the
                // config we choose is too small, our initial decode will fail, but we will retry with no
                // inBitmap which will succeed so if we're wrong here, we're less efficient but still correct.
                expectedConfig = options.inPreferredConfig
            }
            // BitmapFactory will clear out the Bitmap before writing to it, so getDirty is safe.
            options.inBitmap = bitmapPool.getDirty(width, height, expectedConfig)
            return true
        } catch (_: Throwable) {
        }
        return false
    }
}