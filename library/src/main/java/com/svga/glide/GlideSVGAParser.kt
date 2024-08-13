package com.svga.glide

import com.bumptech.glide.load.model.GlideUrl
import com.opensource.svgaplayer.SVGAVideoEntity
import com.opensource.svgaplayer.proto.MovieEntity
import com.opensource.svgaplayer.utils.SVGARect
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.arrayPool
import okio.Buffer
import okio.buffer
import okio.inflate
import okio.sink
import okio.source
import org.json.JSONObject
import java.io.ByteArrayInputStream
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStream
import java.security.MessageDigest
import java.util.zip.Inflater
import java.util.zip.ZipInputStream

/**
 * Time:2022/11/30 00:25
 * Author:
 * Description:
 */
const val movieBinary = "movie.binary"
const val movieSpec = "movie.spec"

class GlideSVGAParser {

    private val TAG = "GlideSVGAParser"

    fun decodeFromInputStream(
        inputStream: InputStream,
        cacheDir: String,
        mFrameWidth: Int,
        mFrameHeight: Int,
        tag: String
    ): SVGAVideoEntity? {
        LogUtils.debug(TAG, "decodeFromInputStream mFrameWidth:$mFrameWidth mFrameHeight:$mFrameHeight $tag")
        val file = File(cacheDir + File.separatorChar + cacheKey(GlideUrl(tag)))
        file.takeIf { !it.exists() }?.mkdirs()
        val path = file.absolutePath
        var svgaVideoEntity: SVGAVideoEntity? = null
        try {
            readAsBytes(inputStream)?.let { bytes ->
                if (isZipFile(bytes)) {
                    LogUtils.debug(TAG, "decodeFromInputStream isZipFile true $tag")
                    ByteArrayInputStream(bytes).use {
                        unzip(it, path)
                    }
                    val binaryFile = File(path, movieBinary)
                    val jsonFile = File(path, movieSpec)
                    svgaVideoEntity = if (binaryFile.isFile) {
                        parseBinaryFile(
                            path, binaryFile, mFrameWidth,
                            mFrameHeight
                        )
                    } else if (jsonFile.isFile) {
                        parseSpecFile(
                            path, jsonFile, mFrameWidth,
                            mFrameHeight
                        )
                    } else {
                        null
                    }
                    LogUtils.debug(TAG, "decodeFromInputStream end")
                } else {
                    LogUtils.debug(TAG, "decodeFromInputStream isZipFile false $tag")
                    inflate(bytes)?.let {
                        val videoEntity = MovieEntity.ADAPTER.decode(it)
                        val width = minOf(mFrameWidth, videoEntity.params.viewBoxWidth.toInt())
                        val height = minOf(mFrameHeight, videoEntity.params.viewBoxHeight.toInt())
                        svgaVideoEntity = SVGAVideoEntity(
                            videoEntity,
                            File(path),
                            width,
                            height,
                            true
                        )
                    }
                    LogUtils.debug(TAG, "decodeFromInputStream end")
                }
            }
        } catch (e: Throwable) {
            LogUtils.error(TAG, "decodeFromInputStream error", e)
        }
        return svgaVideoEntity
    }

    private fun parseBinaryFile(
        source: String,
        binaryFile: File,
        frameWidth: Int,
        frameHeight: Int
    ): SVGAVideoEntity? {
        LogUtils.info(TAG, "parseSpecFile frameWidth:$frameWidth frameHeight:$frameHeight")
        try {
            FileInputStream(binaryFile).use {
                val videoEntity = MovieEntity.ADAPTER.decode(it)
                val width = minOf(frameWidth, videoEntity.params.viewBoxWidth.toInt())
                val height = minOf(frameHeight, videoEntity.params.viewBoxHeight.toInt())
                return SVGAVideoEntity(
                    videoEntity,
                    File(source),
                    width,
                    height,
                    true
                )
            }
        } catch (e: Exception) {
            binaryFile.delete()
            return null
        } finally {
        }
    }

    private fun parseSpecFile(
        source: String,
        jsonFile: File,
        frameWidth: Int,
        frameHeight: Int
    ): SVGAVideoEntity? {
        LogUtils.info(TAG, "parseSpecFile frameWidth:$frameWidth frameHeight:$frameHeight")
        val buffer = arrayPool.get(1024, ByteArray::class.java)
        try {
            jsonFile.source().buffer().use { fileInputStream ->
                Buffer().use { byteArrayOutputStream ->
                    while (true) {
                        val size = fileInputStream.read(buffer)
                        if (size == -1) {
                            break
                        }
                        byteArrayOutputStream.write(buffer, 0, size)
                    }
                    val jsonObj = JSONObject(byteArrayOutputStream.readString(Charsets.UTF_8))
                    var width = frameWidth
                    var height = frameHeight
                    jsonObj.setupByJson()?.let {
                        width = minOf(it.width.toInt(), frameWidth)
                        height = minOf(it.height.toInt(), frameHeight)
                    }
                    return SVGAVideoEntity(jsonObj, File(source), width, height, true)
                }
            }
        } catch (e: Exception) {
            jsonFile.delete()
            return null
        } finally {
            arrayPool.put(buffer)
        }
    }

    private fun unzip(inputStream: ByteArrayInputStream, cacheDir: String) {
        ZipInputStream(inputStream).use { zipInputStream ->
            while (true) {
                val zipItem = zipInputStream.nextEntry ?: break
                if (zipItem.name.contains("../")) {
                    // 解压路径存在路径穿越问题，直接过滤
                    continue
                }
                if (zipItem.name.contains("/")) {
                    continue
                }
                val fileZip = File(cacheDir, zipItem.name)
                ensureUnzipSafety(fileZip, cacheDir)
                val reader = zipInputStream.source().buffer()
                val buffer = arrayPool.get(1024, ByteArray::class.java)
                try {
                    if (!fileZip.exists() || fileZip.length() == 0L) {
                        LogUtils.debug(TAG, "fileZip:$fileZip")
                        fileZip.sink().buffer().use { fileOutputStream ->
                            while (true) {
                                val readBytes = reader.read(buffer)
                                if (readBytes <= 0) break
                                fileOutputStream.write(buffer, 0, readBytes)
                            }
                            arrayPool.put(buffer)
                        }
                    } else {
                        LogUtils.debug(TAG, "fileZip:$fileZip exists")
                    }
                } catch (_: Throwable) {
                } finally {
                    arrayPool.put(buffer)
                }
            }
            zipInputStream.closeEntry()
        }
    }

    private fun inflate(byteArray: ByteArray): ByteArray? {
        val inflater = Inflater()
        inflater.setInput(byteArray, 0, byteArray.size)
        Buffer().use { inflatedOutputStream ->
            Buffer().inflate(inflater).buffer().use {
                val buffer = arrayPool.get(1024, ByteArray::class.java)
                try {
                    while (true) {
                        val count = it.read(buffer)
                        if (count <= 0) break
                        inflatedOutputStream.write(buffer, 0, count)
                    }
                } catch (_: Throwable) {
                } finally {
                    inflater.end()
                    arrayPool.put(buffer)
                }
            }
            return inflatedOutputStream.readByteArray()
        }
    }

    private fun readAsBytes(inputStream: InputStream): ByteArray? {
        Buffer().use { byteArrayOutputStream ->
            val buffer = arrayPool.get(1024, ByteArray::class.java)
            try {
                inputStream.source().buffer().use {
                    while (true) {
                        val cnt = it.read(buffer)
                        if (cnt <= 0) break
                        byteArrayOutputStream.write(buffer, 0, cnt)
                    }
                }
            } catch (_: Throwable) {

            } finally {
                arrayPool.put(buffer)
            }
            return byteArrayOutputStream.readByteArray()
        }
    }

    // 是否是 zip 文件
    private fun isZipFile(bytes: ByteArray): Boolean {
        return bytes.size > 4 && bytes[0].toInt() == 80 && bytes[1].toInt() == 75 && bytes[2].toInt() == 3 && bytes[3].toInt() == 4
    }

    // 检查 zip 路径穿透
    private fun ensureUnzipSafety(outputFile: File, dstDirPath: String) {
        val dstDirCanonicalPath = File(dstDirPath).canonicalPath
        val outputFileCanonicalPath = outputFile.canonicalPath
        if (!outputFileCanonicalPath.startsWith(dstDirCanonicalPath)) {
            throw IOException("Found Zip Path Traversal Vulnerability with $dstDirCanonicalPath")
        }
    }

    private fun cacheKey(glideUrl: GlideUrl): String {
        val messageDigest = MessageDigest.getInstance("MD5")
        messageDigest.update(glideUrl.toStringUrl().toByteArray(charset("UTF-8")))
        val digest = messageDigest.digest()
        var sb = ""
        for (b in digest) {
            sb += String.format("%02x", b)
        }
        return sb
    }

    // 获取
    private fun JSONObject.setupByJson(): SVGARect? {
        return optJSONObject("viewBox")?.let { viewBoxObject ->
            val width = viewBoxObject.optDouble("width", 0.0)
            val height = viewBoxObject.optDouble("height", 0.0)
            SVGARect(0.0, 0.0, width, height)
        }
    }
}