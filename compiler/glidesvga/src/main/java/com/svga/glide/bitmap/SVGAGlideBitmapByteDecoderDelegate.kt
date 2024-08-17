package com.svga.glide.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.svga.glide.SVGAGlideEx.parsers
import java.nio.ByteBuffer

/**
 * 通过字节码解码 Bitmap
 * com.opensource.svgaplayer.bitmap.SVGABitmapByteArrayDecoder
 */
object SVGAGlideBitmapByteDecoderDelegate : ISVGABitmapDecoderDelegate<ByteArray> {

    override fun onDecode(data: ByteArray, options: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeByteArray(data, 0, data.count(), options)
    }

    override fun getImageType(data: ByteArray): ImageHeaderParser.ImageType {
        return ImageHeaderParserUtils.getType(parsers, ByteBuffer.wrap(data))
    }

    override var timeMillis: Long = 0L
}