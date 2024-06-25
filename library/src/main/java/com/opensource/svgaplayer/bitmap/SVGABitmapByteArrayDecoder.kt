package com.opensource.svgaplayer.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.bitmapPool
import java.io.IOException


/**
 * 通过字节码解码 Bitmap
 *
 * Create by im_dsd 2020/7/7 17:50
 */
internal object SVGABitmapByteArrayDecoder : SVGABitmapDecoder<ByteArray>() {

    override fun onDecode(
        data: ByteArray,
        ops: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        bitmapPool?.let {
            var result: Bitmap? = null
            TransformationUtils.getBitmapDrawableLock().lock()
            try {
                // 数据加载
                LogUtils.debug("SVGABitmapByteArrayDecoder", "onDecode 3")
                result = BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
            } catch (e: IllegalArgumentException) {
                if (ops.inBitmap != null) {
                    try {
                        // 输入流重置
                        it.put(ops.inBitmap)
                        // 清理掉 inBitmap 并进行第二次加载
                        ops.inBitmap = null
                        // 再次调用进行加载
                        LogUtils.debug("SVGABitmapByteArrayDecoder", "onDecode 4")
                        result = BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
                    } catch (resetException: IOException) {
                        LogUtils.error("SVGABitmapByteArrayDecoder", "onDecode 5")
                    }
                }
            } finally {
                TransformationUtils.getBitmapDrawableLock().unlock()
            }
            return result
        } ?: kotlin.run {
            LogUtils.debug("SVGABitmapByteArrayDecoder", "onDecode 7")
            return BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
        }
    }

    override fun decodeBitmapSize(data: ByteArray, ops: BitmapFactory.Options) {
        BitmapFactory.decodeByteArray(data, 0, data.count(), ops)
    }
}