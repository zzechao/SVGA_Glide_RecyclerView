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
 * description：TODO
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
        var entitySVGAFileContainsIndex = -1
        val jarOutput = JarOutputStream(BufferedOutputStream(FileOutputStream(output.get().asFile)))
        jarOutput.use {

            // allJars
            allJars.get().forEachIndexed { index, file ->
                Logger.i("handling " + file.asFile.absolutePath)
                val jarFile = JarFile(file.asFile)
                jarFile.use {
                    it.entries().iterator().forEach { jarEntry ->
                        val entryName = jarEntry.name
                        if (!jarEntry.isDirectory && entryName.isNotEmpty()) {
                            //println("Adding from jar ${jarEntry.name} ${entryName.startsWith(ScanSetting.ROUTER_CLASS_PACKAGE_NAME) && shouldProcessPreDexJar(entryName)}")
                            if (HookParams.ENTITY_SVGA_CLASS == entryName) {
                                //Logger.i("Directory CLASS_FILE_NAME entry name $entryName in arouter pool in jar")
                                entitySVGAFileContainsIndex = index
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
                        val relativePath = directory.asFile.toURI().relativize(file.toURI()).getPath()
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

            // fileContainsFileDo
            if (entitySVGAFileContainsIndex >= 0) {
                allJars.get().getOrNull(entitySVGAFileContainsIndex)?.let {
                    val jarFile = JarFile(it.asFile)
                    jarFile.use {
                        jarFile.getJarEntry(HookParams.ENTITY_SVGA_CLASS)?.let {
                            jarFile.getInputStream(it).use {
                                val bytes = it.referHackSVGAEntity()
                                if (bytes.isNotEmpty()) {
                                    jarOutput.writeEntity(HookParams.ENTITY_SVGA_CLASS, bytes)
                                } else {
                                    jarOutput.writeEntity(HookParams.ENTITY_SVGA_CLASS, it)
                                }
                            }
                            Logger.i("fileContains write success")
                        }
                    }
                }
            }
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
}