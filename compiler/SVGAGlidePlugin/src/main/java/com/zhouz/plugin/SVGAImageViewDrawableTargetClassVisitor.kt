package com.zhouz.plugin

import com.zhouz.plugin.HookParams.ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ACC_FINAL
import org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.Opcodes.ACONST_NULL
import org.objectweb.asm.Opcodes.ALOAD
import org.objectweb.asm.Opcodes.CHECKCAST
import org.objectweb.asm.Opcodes.DUP
import org.objectweb.asm.Opcodes.GETFIELD
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.IFNONNULL
import org.objectweb.asm.Opcodes.INVOKEINTERFACE
import org.objectweb.asm.Opcodes.INVOKESPECIAL
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL
import org.objectweb.asm.Opcodes.NEW
import org.objectweb.asm.Opcodes.POP
import org.objectweb.asm.Opcodes.RETURN


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

    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        return if (name == HookParams.ENTITY_SVGA_TARGET_METHOD && descriptor == "(Lcom/svga/glide/SVGAResource;Lcom/svga/glide/SVGAAnimationDrawable;)V") {
            Logger.i("SVGAImageViewDrawableTargetClassVisitor visitMethod name:${name}")
            null
        } else {
            super.visitMethod(access, name, descriptor, signature, exceptions)
        }
    }

    override fun visitEnd() {
        prepareCreate()
        super.visitEnd()
    }

    private fun prepareCreate() {
        val methodVisitor = cv.visitMethod(ACC_PRIVATE or ACC_FINAL, "prepare", "(Lcom/svga/glide/SVGAResource;Lcom/svga/glide/SVGAAnimationDrawable;)V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;")
        methodVisitor.visitLdcInsn("prepare for plugin")
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true)
        methodVisitor.visitVarInsn(ALOAD, 1)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false)
        methodVisitor.visitInsn(DUP)
        val label0 = Label()
        methodVisitor.visitJumpInsn(IFNONNULL, label0)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitLabel(label0)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitVarInsn(ALOAD, 1)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false)
        methodVisitor.visitTypeInsn(NEW, "com/svga/glide/SVGAImageViewDrawableTarget\$prepare$1")
        methodVisitor.visitInsn(DUP)
        methodVisitor.visitVarInsn(ALOAD, 2)
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget\$prepare$1", "<init>", "(Lcom/svga/glide/SVGAAnimationDrawable;)V", false)
        methodVisitor.visitTypeInsn(CHECKCAST, "kotlin/jvm/functions/Function0")
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "prepare\$com_opensource_svgaplayer", "(Lkotlin/jvm/functions/Function0;Lcom/opensource/svgaplayer/SVGAParser\$PlayCallback;)V", false)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(4, 3)
        methodVisitor.visitEnd()
    }
}