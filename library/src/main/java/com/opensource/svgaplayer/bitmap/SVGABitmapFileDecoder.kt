package com.opensource.svgaplayer.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.bitmapPool
import java.io.IOException

/**
 * 通过文件解码 Bitmap
 *
 * Create by im_dsd 2020/7/7 17:50
 */
internal object SVGABitmapFileDecoder : SVGABitmapDecoder<String>() {

    override fun onDecode(
        data: String,
        ops: BitmapFactory.Options,
        reqWidth: Int,
        reqHeight: Int
    ): Bitmap? {
        bitmapPool?.let {
            var result: Bitmap? = null
            TransformationUtils.getBitmapDrawableLock().lock()
            try {
                // 数据加载
                LogUtils.debug("SVGABitmapFileDecoder", "onDecode 3")
                result = BitmapFactory.decodeFile(data, ops)
            } catch (e: IllegalArgumentException) {
                if (ops.inBitmap != null) {
                    try {
                        // 输入流重置
                        it.put(ops.inBitmap)
                        // 清理掉 inBitmap 并进行第二次加载
                        ops.inBitmap = null
                        // 再次调用进行加载
                        LogUtils.debug("SVGABitmapFileDecoder", "onDecode 4")
                        result = BitmapFactory.decodeFile(data, ops)
                    } catch (resetException: IOException) {
                        LogUtils.debug("SVGABitmapFileDecoder", "onDecode 5")
                    }
                }
            } finally {
                TransformationUtils.getBitmapDrawableLock().unlock()
            }
            return result
        } ?: kotlin.run {
            LogUtils.debug("SVGABitmapFileDecoder", "onDecode 7")
            return BitmapFactory.decodeFile(data, ops)
        }
    }

    override fun decodeBitmapSize(data: String, ops: BitmapFactory.Options) {
        BitmapFactory.decodeFile(data, ops)
    }
}