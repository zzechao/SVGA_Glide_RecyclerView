package com.zhouz.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.file.Directory
import org.gradle.api.file.RegularFile
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.ListProperty
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction
import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.ClassWriter.COMPUTE_FRAMES
import org.objectweb.asm.Opcodes
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.jar.JarEntry
import java.util.jar.JarFile
import java.util.jar.JarOutputStream


/**
 * @author:zhouz
 * @date: 2024/8/2 18:33
 * description：transfer
 */
abstract class SVGAGlideTransferTask : DefaultTask() {
    @get:InputFiles
    abstract val allJars: ListProperty<RegularFile>

    @get:InputFiles
    abstract val allDirectories: ListProperty<Directory>

    @get:OutputFile
    abstract val output: RegularFileProperty

    @Internal
    val jarPaths = mutableSetOf<String>()

    @TaskAction
    fun taskRun() {
        val leftSlash: Boolean = File.separator == "/"

        // SVGAVideoEntity.class的位置
        var entitySVGAFileContainsIndex = -1

        // SVGAImageViewDrawableTarget.class 的位置
        var imageViewDrawableTargetIndex = -1

        // SVGAGlideResourceDelegate.class 的位置
        var mSVGAGlideResourceDelegateIndex = -1

        // GlideSVGAParser.class 的位置
        var mSVGAGlideParserIndex = -1

        // SVGAAnimationDrawable.class 的位置
        var mSVGAAnimationDrawableIndex = -1

        val jarOutput = JarOutputStream(BufferedOutputStream(FileOutputStream(output.get().asFile)))
        jarOutput.use {

            // allJars
            allJars.get().forEachIndexed { index, file ->
                val jarFile = JarFile(file.asFile)
                jarFile.use {
                    it.entries().iterator().forEach { jarEntry ->
                        val entryName = jarEntry.name
                        if (!jarEntry.isDirectory && entryName.isNotEmpty()) {
                            //println("Adding from jar ${jarEntry.name} ${entryName.startsWith(ScanSetting.ROUTER_CLASS_PACKAGE_NAME) && shouldProcessPreDexJar(entryName)}")
                            if (HookParams.ENTITY_SVGA_CLASS == entryName) {
                                Logger.i("allJars match handling entryName:$entryName")
                                //Logger.i("Directory CLASS_FILE_NAME entry name $entryName in arouter pool in jar")
                                entitySVGAFileContainsIndex = index
                            } else if (HookParams.ENTITY_SVGA_TARGET_CLASS == entryName) {
                                Logger.i("allJars match handling entryName:$entryName")
                                imageViewDrawableTargetIndex = index
                            } else if (HookParams.SVGA_GLIDE_PARSER_CLASS == entryName) {
                                Logger.i("allJars match handling entryName:$entryName")
                                mSVGAGlideParserIndex = index
                            } else if (HookParams.SVGA_GLIDE_RESOURCE_DELEGATE_CLASS == entryName) {
                                Logger.i("allJars match handling entryName:$entryName")
                                mSVGAGlideResourceDelegateIndex = index
                            } else if (HookParams.SVGA_GLIDE_DRAWABLE_CLASS == entryName) {
                                Logger.i("allJars match handling entryName:$entryName")
                                mSVGAAnimationDrawableIndex = index
                            } else {
                                jarFile.getInputStream(jarEntry).use {
                                    jarOutput.writeEntity(jarEntry.name, it)
                                }
                            }
                        }
                    }
                }
            }

            // allDirectories
            allDirectories.get().forEach { directory ->
                directory.asFile.walk().forEach { file ->
                    if (file.isFile) {
                        val relativePath =
                            directory.asFile.toURI().relativize(file.toURI()).getPath()
                        val entryName = if (leftSlash) {
                            relativePath
                        } else {
                            relativePath.replace(File.separatorChar, '/')
                        }
                        file.inputStream().use {
                            jarOutput.writeEntity(entryName, it)
                        }
                    }
                }
            }


            // SVGAVideoEntity的修改
            if (entitySVGAFileContainsIndex >= 0) {
                allJars.get().getOrNull(entitySVGAFileContainsIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.referHack(HookParams.ENTITY_SVGA_CLASS, jarOutput)
                }
            }

            // ImageViewDrawableTarget的修改
            if (imageViewDrawableTargetIndex > 0) {
                allJars.get().getOrNull(imageViewDrawableTargetIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.referHack(HookParams.ENTITY_SVGA_TARGET_CLASS, jarOutput)
                }
            }

            // SVGAGlideParser的修改
            if (mSVGAGlideParserIndex > 0) {
                allJars.get().getOrNull(mSVGAGlideParserIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.referHack(HookParams.SVGA_GLIDE_PARSER_CLASS, jarOutput)
                }
            }

            // SVGAGlideResourceDelegate的修改
            if (mSVGAGlideResourceDelegateIndex > 0) {
                allJars.get().getOrNull(mSVGAGlideResourceDelegateIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.referHack(HookParams.SVGA_GLIDE_RESOURCE_DELEGATE_CLASS, jarOutput)
                }
            }

            // SVGAAnimationDrawable的修改
            if (mSVGAAnimationDrawableIndex > 0) {
                allJars.get().getOrNull(mSVGAAnimationDrawableIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.referHack(HookParams.SVGA_GLIDE_DRAWABLE_CLASS, jarOutput)
                }
            }

            // prepare$com_opensource_svgaplayer匿名内部类的构建
            jarOutput.writeEntity(
                HookParams.ENTITY_SVGA_TARGET_CLASS_PREPARE_2_CLASS,
                SVGAImageViewDrawableTargetClassWriter().writer()
            )
        }
    }

    private fun JarOutputStream.writeEntity(name: String, inputStream: InputStream) {
        // check for duplication name first
        if (jarPaths.contains(name)) {
            printDuplicatedMessage(name)
        } else {
            putNextEntry(JarEntry(name))
            inputStream.copyTo(this)
            closeEntry()
            jarPaths.add(name)
        }
    }

    private fun JarOutputStream.writeEntity(relativePath: String, byteArray: ByteArray) {
        // check for duplication name first
        if (jarPaths.contains(relativePath)) {
            printDuplicatedMessage(relativePath)
        } else {
            putNextEntry(JarEntry(relativePath))
            write(byteArray)
            closeEntry()
            jarPaths.add(relativePath)
        }
    }

    private fun printDuplicatedMessage(name: String) =
        Logger.e("Cannot add ${name}, because output Jar already has file with the same name.")

    /**
     * hook的 class
     */
    private fun JarFile.referHack(clazzName: String, jarOutput: JarOutputStream) {
        use {
            getJarEntry(clazzName)?.let {
                this.getInputStream(it).use {
                    val cr = ClassReader(it)
                    val cw = ClassWriter(cr, COMPUTE_FRAMES)
                    when (clazzName) {
                        HookParams.ENTITY_SVGA_TARGET_CLASS -> {
                            val cv = SVGAImageViewDrawableTargetClassVisitor(Opcodes.ASM9, cw)
                            cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        }

                        HookParams.ENTITY_SVGA_CLASS -> {
                            val cv = SVGAEntityClassVisitor2(Opcodes.ASM9, cw)
                            cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        }

                        HookParams.SVGA_GLIDE_PARSER_CLASS -> {
                            val cv = SVGAGlideParserClassVisitor(Opcodes.ASM9, cw)
                            cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        }

                        HookParams.SVGA_GLIDE_RESOURCE_DELEGATE_CLASS -> {
                            val cv = SVGAGlideResourceDelegateVisitor(Opcodes.ASM9, cw)
                            cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        }

                        HookParams.SVGA_GLIDE_DRAWABLE_CLASS -> {
                            val cv = SVGAGlideDrawableClassVisitor(Opcodes.ASM9, cw)
                            cr.accept(cv, ClassReader.EXPAND_FRAMES)
                        }
                    }
                    val bytes = cw.toByteArray() ?: ByteArray(0)
                    if (bytes.isNotEmpty()) {
                        jarOutput.writeEntity(clazzName, bytes)
                    } else {
                        jarOutput.writeEntity(clazzName, it)
                    }
                }
                Logger.i("$clazzName write success")
            }
        }
    }
}