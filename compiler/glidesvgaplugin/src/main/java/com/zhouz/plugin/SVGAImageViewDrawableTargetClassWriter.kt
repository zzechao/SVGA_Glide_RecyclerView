package com.zhouz.plugin

import com.zhouz.plugin.HookParams.ENTITY_SVGA_ANIMATION_DRAWABLE_NAME
import com.zhouz.plugin.HookParams.ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME
import com.zhouz.plugin.HookParams.ENTITY_SVGA_TARGET_NAME
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassWriter
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

/**
 * @author:zhouz
 * @date: 2024/8/8 18:33
 * description：因为prepare(callback: () -> Unit...)，所以javac并定会生成匿名内部类
 */
class SVGAImageViewDrawableTargetClassWriter : ClassWriter(0) {
    private var fieldVisitor: FieldVisitor? = null
    private var methodVisitor: MethodVisitor? = null
    private var annotationVisitor0: AnnotationVisitor? = null

    fun writer(): ByteArray {
        visit(
            Opcodes.V1_8,
            Opcodes.ACC_FINAL or Opcodes.ACC_SUPER,
            ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME,
            "Lkotlin/jvm/internal/Lambda;Lkotlin/jvm/functions/Function0<Lkotlin/Unit;>;",
            "kotlin/jvm/internal/Lambda",
            arrayOf("kotlin/jvm/functions/Function0")
        )

        visitOuterClass(
            ENTITY_SVGA_TARGET_NAME,
            "onResourceReady",
            "(Lcom/zhouz/glidesvga/SVGAResource;Lcom/bumptech/glide/request/transition/Transition;)V"
        )

        annotationVisitor0 = visitAnnotation("Lkotlin/Metadata;", true)
        annotationVisitor0?.visit("mv", intArrayOf(1, 7, 1))
        annotationVisitor0?.visit("k", 3)
        annotationVisitor0?.visit("xi", 48)

        val annotationVisitor1 = annotationVisitor0?.visitArray("d1")
        annotationVisitor1?.visit(
            null,
            "\u0000\u0008\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n\u00a2\u0006\u0002\u0008\u0002"
        )
        annotationVisitor1?.visitEnd()

        val annotationVisitor2 = annotationVisitor0?.visitArray("d2")
        annotationVisitor2?.visit(null, "<anonymous>")
        annotationVisitor2?.visit(null, "")
        annotationVisitor2?.visit(null, "invoke")
        annotationVisitor2?.visitEnd()

        annotationVisitor0?.visitEnd()

        visitInnerClass(
            ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME,
            null,
            null,
            Opcodes.ACC_FINAL or Opcodes.ACC_STATIC
        )

        fieldVisitor = visitField(
            Opcodes.ACC_FINAL or Opcodes.ACC_SYNTHETIC,
            "\$drawable",
            "L$ENTITY_SVGA_ANIMATION_DRAWABLE_NAME;",
            null,
            null
        )
        fieldVisitor?.visitEnd()

        methodVisitor =
            visitMethod(0, "<init>", "(L$ENTITY_SVGA_ANIMATION_DRAWABLE_NAME;)V", null, null)
        methodVisitor?.visitCode()
        methodVisitor?.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor?.visitVarInsn(Opcodes.ALOAD, 1)
        methodVisitor?.visitFieldInsn(
            Opcodes.PUTFIELD,
            ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME,
            "\$drawable",
            "L$ENTITY_SVGA_ANIMATION_DRAWABLE_NAME;"
        )
        methodVisitor?.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor?.visitInsn(Opcodes.ICONST_0)
        methodVisitor?.visitMethodInsn(
            Opcodes.INVOKESPECIAL, "kotlin/jvm/internal/Lambda", "<init>", "(I)V", false
        )
        methodVisitor?.visitInsn(Opcodes.RETURN)
        methodVisitor?.visitMaxs(2, 2)
        methodVisitor?.visitEnd()

        methodVisitor =
            visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_FINAL, "invoke", "()V", null, null)
        methodVisitor?.visitCode()
        methodVisitor?.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor?.visitFieldInsn(
            Opcodes.GETFIELD,
            ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME,
            "\$drawable",
            "L$ENTITY_SVGA_ANIMATION_DRAWABLE_NAME;"
        )
        methodVisitor?.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            ENTITY_SVGA_ANIMATION_DRAWABLE_NAME,
            "start",
            "()V",
            false
        )
        methodVisitor?.visitInsn(Opcodes.RETURN)
        methodVisitor?.visitMaxs(1, 1)
        methodVisitor?.visitEnd()

        methodVisitor = visitMethod(
            Opcodes.ACC_PUBLIC or Opcodes.ACC_BRIDGE or Opcodes.ACC_SYNTHETIC,
            "invoke",
            "()Ljava/lang/Object;",
            null,
            null
        )
        methodVisitor?.visitCode()
        methodVisitor?.visitVarInsn(Opcodes.ALOAD, 0)
        methodVisitor?.visitMethodInsn(
            Opcodes.INVOKEVIRTUAL,
            ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME,
            "invoke",
            "()V",
            false
        )
        methodVisitor?.visitFieldInsn(Opcodes.GETSTATIC, "kotlin/Unit", "INSTANCE", "Lkotlin/Unit;")
        methodVisitor?.visitInsn(Opcodes.ARETURN)
        methodVisitor?.visitMaxs(1, 1)
        methodVisitor?.visitEnd()

        visitEnd()

        return toByteArray()
    }
}