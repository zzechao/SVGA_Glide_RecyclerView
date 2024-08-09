package com.svga.glide

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import com.bumptech.glide.load.ImageHeaderParser.ImageType
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.bitmapPool
import com.svga.glide.SVGAGlideEx.parsers
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer


/**
 * @author:zhouz
 * @date: 2024/8/9 17:52
 * description：
 */
private const val TAG = "SVGAGlideBitmapDecoder"

object SVGAGlideBitmapDecoder {

    fun onDecode(data: ByteArray, ops: BitmapFactory.Options, reqWidth: Int, reqHeight: Int): Bitmap? {
        if (::bitmapPool.isLateinit) {
            var result: Bitmap? = null
            val sourceWidth: Int = ops.outWidth
            val sourceHeight: Int = ops.outHeight
            var isHardwareConfigAllowed: Boolean

            if (sourceWidth == -1 || sourceHeight == -1) {
                isHardwareConfigAllowed = false
            }
            val orientation: Int = getImageOrientation(data)
            val degreesToRotate = TransformationUtils.getExifOrientationDegrees(orientation)
            val targetWidth = if (isRotationRequired(degreesToRotate)) sourceHeight else sourceWidth
            val targetHeight = if (isRotationRequired(degreesToRotate)) sourceWidth else sourceHeight
            val imageType: ImageType = getImageType(data)
            val isKitKatOrGreater = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT

            TransformationUtils.getBitmapDrawableLock().lock()
            try {
                // 数据加载
                LogUtils.debug(TAG, "onDecode 3")
                result = BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
            } catch (e: IllegalArgumentException) {
                if (ops.inBitmap != null) {
                    try {
                        // 输入流重置
                        bitmapPool.put(ops.inBitmap)
                        // 清理掉 inBitmap 并进行第二次加载
                        ops.inBitmap = null
                        // 再次调用进行加载
                        LogUtils.debug(TAG, "onDecode 4")
                        result = BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
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
            return BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
        }
    }

    /**
     * bytes获取imageType
     */
    private fun getImageType(bytes: ByteArray): ImageType {
        return ImageHeaderParserUtils.getType(parsers, ByteBuffer.wrap(bytes))
    }

    /**
     * file获取imageType
     */
    private fun getImageType(file: File): ImageType {
        var `is`: InputStream? = null
        try {
            `is` = RecyclableBufferedInputStream(FileInputStream(file), SVGAGlideEx.arrayPool)
            return ImageHeaderParserUtils.getType(parsers, `is`, SVGAGlideEx.arrayPool)
        } finally {
            if (`is` != null) {
                try {
                    `is`.close()
                } catch (_: IOException) {
                }
            }
        }
    }

    /**
     * bytes获取ImageOrientation
     */
    private fun getImageOrientation(bytes: ByteArray): Int {
        return ImageHeaderParserUtils.getOrientation(parsers, ByteBuffer.wrap(bytes), SVGAGlideEx.arrayPool)
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
}