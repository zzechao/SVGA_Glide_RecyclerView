package com.zhouz.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ICONST_1


/**
 * @author:zhouz
 * @date: 2024/8/13 18:39
 * description：修改GlideSVGAParser的调用com.svga.glide.bitmap.ISVGABitmapDecoderDelegate
 */
class SVGAGlideParserClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {

    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        return if (name == "decodeFromInputStream" || name == "parseBinaryFile" || name == "parseSpecFile") {
            val initDescriptor = if (name == "parseSpecFile") "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V" else "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V"
            object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                    if (owner == "com/opensource/svgaplayer/SVGAVideoEntity" && name == "<init>") {
                        this.mv.visitInsn(ICONST_1)
                        super.visitMethodInsn(opcode, owner, name, initDescriptor, isInterface)
                    } else {
                        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                    }
                }

                override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                    if (name == "parseBinaryFile" || name == "parseSpecFile") {
                        super.visitMaxs(7, maxLocals)
                    } else {
                        super.visitMaxs(maxStack, maxLocals)
                    }
                }
            }
        } else {
            return super.visitMethod(access, name, descriptor, signature, exceptions)
        }
    }
}