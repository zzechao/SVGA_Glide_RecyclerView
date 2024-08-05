package com.zhouz.plugin

import org.objectweb.asm.ClassReader
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.ClassWriter.COMPUTE_FRAMES
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import java.io.InputStream


/**
 * @author:zhouz
 * @date: 2024/8/5 17:24
 * description：修改SVGAVideoEntity的 prepare方法
 */
class SVGAEntityClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        return if (name == HookParams.ENTITY_SVGA_CLASS_METHOD) {
            super.visitMethod(Opcodes.ACC_PUBLIC, name, descriptor, signature, exceptions)
        } else {
            super.visitMethod(access, name, descriptor, signature, exceptions)
        }
    }
}

fun InputStream.referHackSVGAEntity(): ByteArray {
    try {
        val cr = ClassReader(this)
        val cw = ClassWriter(cr, COMPUTE_FRAMES)
        val cv = SVGAEntityClassVisitor(Opcodes.ASM9, cw)
        cr.accept(cv, ClassReader.EXPAND_FRAMES)
        return cw.toByteArray() ?: ByteArray(0)
    } catch (ex: Throwable) {
        Logger.e("ex:${ex.message}")
    }
    return ByteArray(0)
}