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
import java.io.IOException
import java.io.InputStream

/**
 * 通过文件解码 Bitmap
 *
 * Create by im_dsd 2020/7/7 17:50
 */
object SVGAGlideBitmapFileDecoderDelegate : ISVGABitmapDecoderDelegate<String> {

    override fun onDecode(data: String, ops: BitmapFactory.Options): Bitmap? {
        return BitmapFactory.decodeFile(data, ops)
    }

    override fun getImageType(data: String): ImageHeaderParser.ImageType {
        return RecyclableBufferedInputStream(
            FileInputStream(File(data)),
            SVGAGlideEx.arrayPool
        ).use {
            ImageHeaderParserUtils.getType(parsers, it, SVGAGlideEx.arrayPool)
        }
    }
}