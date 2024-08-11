package com.svga.glide.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import java.util.Collections
import java.util.EnumSet

private val TYPES_THAT_USE_POOL_PRE_KITKAT: Set<ImageType> = Collections.unmodifiableSet(
    EnumSet.of(
        ImageType.JPEG, ImageType.PNG_A, ImageType.PNG
    )
)

interface ISVGABitmapDecoderDelegate<T> {

    fun decodeBitmapFrom(data: T, reqWidth: Int, reqHeight: Int): Bitmap? {
        return BitmapFactory.Options().run {
            // 如果期望的宽高是合法的, 则开启检测尺寸模式
            inJustDecodeBounds = (reqWidth > 0 && reqHeight > 0)
            inPreferredConfig = Bitmap.Config.RGB_565

            val bitmap = onDecode(data, this)
            if (!inJustDecodeBounds) {
                return bitmap
            }

            // Calculate inSampleSize
            inSampleSize = attachSampleSize(this, reqWidth, reqHeight)
            // Decode bitmap with inSampleSize set
            inJustDecodeBounds = false



            onDecode(data, this)
        }
    }

    fun onDecode(data: T, ops: BitmapFactory.Options): Bitmap?

    /**
     * com.bumptech.glide.load.resource.bitmap.ImageReader.getImageType
     */
    fun getImageType(data: T): ImageType

    /**
     * 采样率计算
     */
    fun attachSampleSize(
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
    fun shouldUsePool(imageType: ImageType): Boolean {
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
    fun isScaling(options: BitmapFactory.Options): Boolean {
        return options.inTargetDensity > 0 && options.inDensity > 0 && options.inTargetDensity != options.inDensity
    }
}