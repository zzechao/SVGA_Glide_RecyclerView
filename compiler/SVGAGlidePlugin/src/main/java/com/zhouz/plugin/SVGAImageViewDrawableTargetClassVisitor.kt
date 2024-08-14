package com.zhouz.plugin

import com.zhouz.plugin.HookParams.ENTITY_SVGA_ANIMATION_DRAWABLE_NAME
import com.zhouz.plugin.HookParams.ENTITY_SVGA_CLASS_METHOD_NEW
import com.zhouz.plugin.HookParams.ENTITY_SVGA_NAME
import com.zhouz.plugin.HookParams.ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author:zhouz
 * @date: 2024/8/8 18:33
 * description：修改SVGAImageViewDrawableTarget的SVGAVideoEntity的 prepare方法调用
 */
class SVGAImageViewDrawableTargetClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor {
        // 匹配 classWriter.visitField(ACC_PRIVATE, "times", "I", null, null);
        if (name == "times" && access == Opcodes.ACC_PRIVATE && descriptor == "I") {
            Logger.i("SVGAImageViewDrawableTargetClassVisitor visitField name:${name}")
            cv.visitInnerClass(ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME, null, null, Opcodes.ACC_FINAL or Opcodes.ACC_STATIC)
        }
        return super.visitField(access, name, descriptor, signature, value)
    }

    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor {
        var mv = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (name == HookParams.ENTITY_SVGA_TARGET_METHOD) {
            Logger.i("SVGAImageViewDrawableTargetClassVisitor visitMethod name:${name}")
            mv = OnResourceReadyMethodVisitor(Opcodes.ASM9, mv)
        }
        return mv
    }


    inner class OnResourceReadyMethodVisitor(api: Int, mv: MethodVisitor) : MethodVisitor(api, mv) {
        override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
            // 匹配visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageDrawable", "(Landroid/graphics/drawable/Drawable;)V", false);
            if (opcode == Opcodes.INVOKEVIRTUAL && name == "setImageDrawable") {
                Logger.i("MethodWriter visitMethodInsn name:${name}")
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, HookParams.ENTITY_SVGA_RESOURCE_NAME, "getVideoItem", "()L${HookParams.ENTITY_SVGA_NAME};", false)
                mv.visitTypeInsn(Opcodes.NEW, ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME)
                mv.visitInsn(Opcodes.DUP)
            } else if (opcode == Opcodes.INVOKEVIRTUAL && name == "start") {
                Logger.i("MethodWriter visitMethodInsn name:${name}")
                // 匹配visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "start", "()V", false);
                mv.visitMethodInsn(Opcodes.INVOKESPECIAL, ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME, "<init>", "(L${ENTITY_SVGA_ANIMATION_DRAWABLE_NAME};)V", false)
                mv.visitTypeInsn(Opcodes.CHECKCAST, "kotlin/jvm/functions/Function0")
                mv.visitInsn(Opcodes.ACONST_NULL)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_NAME, ENTITY_SVGA_CLASS_METHOD_NEW, "(Lkotlin/jvm/functions/Function0;Lcom/opensource/svgaplayer/SVGAParser\$PlayCallback;)V", false)
            } else {
                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
            }
        }
    }
}