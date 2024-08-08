package com.zhouz.plugin

import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author:zhouz
 * @date: 2024/8/5 17:24
 * description：修改SVGAVideoEntity的 prepare方法为 public
 */
class SVGAEntityClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return if (name == HookParams.ENTITY_SVGA_CLASS_METHOD) {
            Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
            super.visitMethod(
                Opcodes.ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD_NEW, descriptor, signature, exceptions
            )
        } else {
            super.visitMethod(access, name, descriptor, signature, exceptions)
        }
    }
}