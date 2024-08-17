package com.svga.glide.bitmap

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.bumptech.glide.load.ImageHeaderParser
import com.bumptech.glide.load.ImageHeaderParserUtils
import com.bumptech.glide.load.resource.bitmap.RecyclableBufferedInputStream
import com.svga.glide.SVGAGlideEx
import com.svga.glide.SVGAGlideEx.parsers
import java.io.File
import java.io.FileInputStream

/**
 * 通过文件解码 Bitmap
 * com.opensource.svgaplayer.bitmap.SVGABitmapFileDecoder
 */
object SVGAGlideBitmapFileDecoderDelegate : ISVGABitmapDecoderDelegate<String> {

    override fun onDecode(data: String, options: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeFile(data, options)
    }

    override fun getImageType(data: String): ImageHeaderParser.ImageType {
        return RecyclableBufferedInputStream(
            FileInputStream(File(data)),
            SVGAGlideEx.arrayPool
        ).use {
            ImageHeaderParserUtils.getType(parsers, it, SVGAGlideEx.arrayPool)
        }
    }

    override var timeMillis: Long = 0L
}