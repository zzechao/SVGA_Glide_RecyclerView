package com.zhouz.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author:zhouz
 * @date: 2024/8/13 16:58
 * description：原始方案
 */
class SVGAEntityClassVisitor2(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return when (name) {
            HookParams.ENTITY_SVGA_CLASS_METHOD -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                super.visitMethod(
                    Opcodes.ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD_NEW, descriptor, signature, exceptions
                )
            }

            HookParams.ENTITY_SVGA_CLASS_METHOD2 -> {
                super.visitMethod(
                    Opcodes.ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD2, descriptor, signature, exceptions
                )
            }

            else -> {
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }
        }
    }
}