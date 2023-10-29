package com.svga.glide

import com.bumptech.glide.load.model.GlideUrl
import com.opensource.svgaplayer.SVGAVideoEntity
import com.opensource.svgaplayer.proto.MovieEntity
import com.opensource.svgaplayer.utils.log.LogUtils
import com.svga.glide.SVGAGlideEx.arrayPool
import org.json.JSONObject
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
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
        val file = File(
            cacheDir + File.separatorChar
                    + cacheKey(GlideUrl(tag))
        )
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
                        svgaVideoEntity = SVGAVideoEntity(
                            MovieEntity.ADAPTER.decode(it),
                            File(path),
                            mFrameWidth,
                            mFrameHeight
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
        try {
            FileInputStream(binaryFile).use {
                return SVGAVideoEntity(
                    MovieEntity.ADAPTER.decode(it),
                    File(source),
                    frameWidth,
                    frameHeight
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
        val buffer = arrayPool.get(1024, ByteArray::class.java)
        try {
            FileInputStream(jsonFile).use { fileInputStream ->
                ByteArrayOutputStream().use { byteArrayOutputStream ->
                    while (true) {
                        val size = fileInputStream.read(buffer)
                        if (size == -1) {
                            break
                        }
                        byteArrayOutputStream.write(buffer, 0, size)
                    }
                    val jsonObj = JSONObject(byteArrayOutputStream.toString())
                    return SVGAVideoEntity(jsonObj, File(source), frameWidth, frameHeight)
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
        BufferedInputStream(inputStream).use {
            ZipInputStream(it).use { zipInputStream ->
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
                    if (!fileZip.exists() || fileZip.length() == 0L) {
                        LogUtils.debug(TAG, "fileZip:$fileZip")
                        FileOutputStream(fileZip).use { fileOutputStream ->
                            val buffer = arrayPool.get(1024, ByteArray::class.java)
                            while (true) {
                                val readBytes = zipInputStream.read(buffer)
                                if (readBytes <= 0) break
                                fileOutputStream.write(buffer, 0, readBytes)
                            }
                            arrayPool.put(buffer)
                        }
                    } else {
                        LogUtils.debug(TAG, "fileZip:$fileZip exists")
                    }
                    zipInputStream.closeEntry()
                }
            }
        }
    }

    private fun inflate(byteArray: ByteArray): ByteArray? {
        val inflater = Inflater()
        inflater.setInput(byteArray, 0, byteArray.size)
        ByteArrayOutputStream().use { inflatedOutputStream ->
            val buffer = arrayPool.get(1024, ByteArray::class.java)
            while (true) {
                val count = inflater.inflate(buffer)
                if (count <= 0) break
                inflatedOutputStream.write(buffer, 0, count)
            }
            inflater.end()
            arrayPool.put(buffer)
            return inflatedOutputStream.toByteArray()
        }
    }

    private fun readAsBytes(inputStream: InputStream): ByteArray? {
        ByteArrayOutputStream().use { byteArrayOutputStream ->
            val buffer = arrayPool.get(1024, ByteArray::class.java)
            try {
                while (true) {
                    val cnt = inputStream.read(buffer)
                    if (cnt <= 0) break
                    byteArrayOutputStream.write(buffer, 0, cnt)
                }
            } catch (_: Throwable) {

            } finally {
                arrayPool.put(buffer)
            }


            return byteArrayOutputStream.toByteArray()
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
}