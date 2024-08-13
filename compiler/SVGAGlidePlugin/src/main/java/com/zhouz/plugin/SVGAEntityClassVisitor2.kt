package com.zhouz.plugin

import com.zhouz.plugin.HookParams.ENTITY_SVGA_NAME
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ACC_PUBLIC
import org.objectweb.asm.Opcodes.ALOAD
import org.objectweb.asm.Opcodes.ASTORE
import org.objectweb.asm.Opcodes.BIPUSH
import org.objectweb.asm.Opcodes.DCONST_0
import org.objectweb.asm.Opcodes.DUP
import org.objectweb.asm.Opcodes.GETFIELD
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.GOTO
import org.objectweb.asm.Opcodes.ICONST_0
import org.objectweb.asm.Opcodes.ICONST_1
import org.objectweb.asm.Opcodes.IFEQ
import org.objectweb.asm.Opcodes.IFNONNULL
import org.objectweb.asm.Opcodes.IFNULL
import org.objectweb.asm.Opcodes.ILOAD
import org.objectweb.asm.Opcodes.INVOKESPECIAL
import org.objectweb.asm.Opcodes.INVOKESTATIC
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL
import org.objectweb.asm.Opcodes.ISTORE
import org.objectweb.asm.Opcodes.NEW
import org.objectweb.asm.Opcodes.NOP
import org.objectweb.asm.Opcodes.POP
import org.objectweb.asm.Opcodes.PUTFIELD
import org.objectweb.asm.Opcodes.RETURN


/**
 * @author:zhouz
 * @date: 2024/8/13 16:58
 * description：SVGAVideoEntity.class 的 transfer
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
                    ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD_NEW, descriptor, signature, exceptions
                )
            }

            HookParams.ENTITY_SVGA_CLASS_METHOD2 -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                super.visitMethod(
                    ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD2, descriptor, signature, exceptions
                )
            }

            "getAntiAlias" -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                val fieldVisitor = cv.visitField(Opcodes.ACC_PRIVATE, "isGlide", "Z", null, null)
                fieldVisitor.visitEnd()
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }

            "clear" -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                val init1 = {
                    val methodVisitor = cv.visitMethod(ACC_PUBLIC, "<init>", "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V", null, null)
                    methodVisitor.visitAnnotableParameterCount(5, false)
                    val annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/NotNull;", false)
                    annotationVisitor0.visitEnd()
                    val annotationVisitor1 = methodVisitor.visitParameterAnnotation(1, "Lorg/jetbrains/annotations/NotNull;", false)
                    annotationVisitor1.visitEnd()
                    methodVisitor.visitCode()
                    val label0 = Label()
                    val label1 = Label()
                    val label2 = Label()
                    methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Exception")
                    val label3 = Label()
                    methodVisitor.visitTryCatchBlock(label0, label1, label3, "java/lang/OutOfMemoryError")
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitLdcInsn("json")
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false)
                    methodVisitor.visitVarInsn(ALOAD, 2)
                    methodVisitor.visitLdcInsn("cacheDir")
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitLdcInsn("SVGAVideoEntity")
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "TAG", "Ljava/lang/String;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitInsn(ICONST_1)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "antiAlias", "Z")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitTypeInsn(NEW, "com/opensource/svgaplayer/utils/SVGARect")
                    methodVisitor.visitInsn(DUP)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/opensource/svgaplayer/utils/SVGARect", "<init>", "(DDDD)V", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "videoSize", "Lcom/opensource/svgaplayer/utils/SVGARect;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitIntInsn(BIPUSH, 15)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "FPS", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/collections/CollectionsKt", "emptyList", "()Ljava/util/List;", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "spriteList", "Ljava/util/List;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/collections/CollectionsKt", "emptyList", "()Ljava/util/List;", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "audioList", "Ljava/util/List;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitTypeInsn(NEW, "java/util/HashMap")
                    methodVisitor.visitInsn(DUP)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "imageMap", "Ljava/util/HashMap;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 5)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "isGlide", "Z")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 3)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mFrameWidth", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 4)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mFrameHeight", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 2)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mCacheDir", "Ljava/io/File;")
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitLdcInsn("movie")
                    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "org/json/JSONObject", "optJSONObject", "(Ljava/lang/String;)Lorg/json/JSONObject;", false)
                    methodVisitor.visitInsn(DUP)
                    val label4 = Label()
                    methodVisitor.visitJumpInsn(IFNONNULL, label4)
                    methodVisitor.visitInsn(POP)
                    methodVisitor.visitInsn(RETURN)
                    methodVisitor.visitLabel(label4)
                    methodVisitor.visitVarInsn(ASTORE, 6)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 6)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "setupByJson", "(Lorg/json/JSONObject;)V", false)
                    methodVisitor.visitLabel(label0)
                    methodVisitor.visitInsn(NOP)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "parserImages", "(Lorg/json/JSONObject;)V", false)
                    methodVisitor.visitLabel(label1)
                    val label5 = Label()
                    methodVisitor.visitJumpInsn(GOTO, label5)
                    methodVisitor.visitLabel(label2)
                    methodVisitor.visitVarInsn(ASTORE, 7)
                    methodVisitor.visitVarInsn(ALOAD, 7)
                    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false)
                    methodVisitor.visitJumpInsn(GOTO, label5)
                    methodVisitor.visitLabel(label3)
                    methodVisitor.visitVarInsn(ASTORE, 7)
                    methodVisitor.visitVarInsn(ALOAD, 7)
                    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/OutOfMemoryError", "printStackTrace", "()V", false)
                    methodVisitor.visitLabel(label5)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "resetSprites", "(Lorg/json/JSONObject;)V", false)
                    methodVisitor.visitInsn(RETURN)
                    methodVisitor.visitMaxs(11, 8)
                    methodVisitor.visitEnd()
                }

                val init2 = {
                    val methodVisitor = cv.visitMethod(ACC_PUBLIC, "<init>", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V", null, null)
                    methodVisitor.visitAnnotableParameterCount(5, false)
                    val annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/NotNull;", false)
                    annotationVisitor0.visitEnd()
                    val annotationVisitor1 = methodVisitor.visitParameterAnnotation(1, "Lorg/jetbrains/annotations/NotNull;", false)
                    annotationVisitor1.visitEnd()
                    methodVisitor.visitCode()
                    val label0 = Label()
                    val label1 = Label()
                    val label2 = Label()
                    methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Exception")
                    val label3 = Label()
                    methodVisitor.visitTryCatchBlock(label0, label1, label3, "java/lang/OutOfMemoryError")
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitLdcInsn("entity")
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false)
                    methodVisitor.visitVarInsn(ALOAD, 2)
                    methodVisitor.visitLdcInsn("cacheDir")
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitLdcInsn("SVGAVideoEntity")
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "TAG", "Ljava/lang/String;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitInsn(ICONST_1)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "antiAlias", "Z")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitTypeInsn(NEW, "com/opensource/svgaplayer/utils/SVGARect")
                    methodVisitor.visitInsn(DUP)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitInsn(DCONST_0)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/opensource/svgaplayer/utils/SVGARect", "<init>", "(DDDD)V", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "videoSize", "Lcom/opensource/svgaplayer/utils/SVGARect;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitIntInsn(BIPUSH, 15)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "FPS", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/collections/CollectionsKt", "emptyList", "()Ljava/util/List;", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "spriteList", "Ljava/util/List;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/collections/CollectionsKt", "emptyList", "()Ljava/util/List;", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "audioList", "Ljava/util/List;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitTypeInsn(NEW, "java/util/HashMap")
                    methodVisitor.visitInsn(DUP)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/util/HashMap", "<init>", "()V", false)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "imageMap", "Ljava/util/HashMap;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 5)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "isGlide", "Z")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 3)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mFrameWidth", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ILOAD, 4)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mFrameHeight", "I")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 2)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "mCacheDir", "Ljava/io/File;")
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitFieldInsn(PUTFIELD, ENTITY_SVGA_NAME, "movieItem", "Lcom/opensource/svgaplayer/proto/MovieEntity;")
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitFieldInsn(GETFIELD, "com/opensource/svgaplayer/proto/MovieEntity", "params", "Lcom/opensource/svgaplayer/proto/MovieParams;")
                    methodVisitor.visitInsn(DUP)
                    val label4 = Label()
                    methodVisitor.visitJumpInsn(IFNULL, label4)
                    methodVisitor.visitVarInsn(ASTORE, 7)
                    methodVisitor.visitInsn(ICONST_0)
                    methodVisitor.visitVarInsn(ISTORE, 8)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 7)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "setupByMovie", "(Lcom/opensource/svgaplayer/proto/MovieParams;)V", false)
                    methodVisitor.visitJumpInsn(GOTO, label0)
                    methodVisitor.visitLabel(label4)
                    methodVisitor.visitInsn(POP)
                    methodVisitor.visitInsn(NOP)
                    methodVisitor.visitLabel(label0)
                    methodVisitor.visitInsn(NOP)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "parserImages", "(Lcom/opensource/svgaplayer/proto/MovieEntity;)V", false)
                    methodVisitor.visitLabel(label1)
                    val label5 = Label()
                    methodVisitor.visitJumpInsn(GOTO, label5)
                    methodVisitor.visitLabel(label2)
                    methodVisitor.visitVarInsn(ASTORE, 6)
                    methodVisitor.visitVarInsn(ALOAD, 6)
                    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Exception", "printStackTrace", "()V", false)
                    methodVisitor.visitJumpInsn(GOTO, label5)
                    methodVisitor.visitLabel(label3)
                    methodVisitor.visitVarInsn(ASTORE, 6)
                    methodVisitor.visitVarInsn(ALOAD, 6)
                    methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/OutOfMemoryError", "printStackTrace", "()V", false)
                    methodVisitor.visitLabel(label5)
                    methodVisitor.visitVarInsn(ALOAD, 0)
                    methodVisitor.visitVarInsn(ALOAD, 1)
                    methodVisitor.visitMethodInsn(INVOKESPECIAL, ENTITY_SVGA_NAME, "resetSprites", "(Lcom/opensource/svgaplayer/proto/MovieEntity;)V", false)
                    methodVisitor.visitInsn(RETURN)
                    methodVisitor.visitMaxs(11, 9)
                    methodVisitor.visitEnd()
                }

                super.visitMethod(access, name, descriptor, signature, exceptions).apply {
                    init1()
                    init2()
                }
            }

            "createBitmap" -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                when (descriptor) {
                    "(Ljava/lang/String;)Landroid/graphics/Bitmap;" -> {
                        object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                            private var label1: Label? = null
                            override fun visitCode() {
                                super.visitCode()
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "isGlide", "Z")
                                val label0 = Label()
                                mv.visitJumpInsn(Opcodes.IFEQ, label0)
                                mv.visitFieldInsn(Opcodes.GETSTATIC, "com/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate", "INSTANCE", "Lcom/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate;")
                                mv.visitVarInsn(ALOAD, 1)
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "mFrameWidth", "I")
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "mFrameHeight", "I")
                                mv.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate", "decodeBitmapFrom", "(Ljava/lang/Object;II)Landroid/graphics/Bitmap;", false)
                                label1 = Label()
                                mv.visitJumpInsn(GOTO, label1)
                                mv.visitLabel(label0)
                            }

                            override fun visitInsn(opcode: Int) {
                                if (Opcodes.ARETURN == opcode) {
                                    mv.visitLabel(label1)
                                }
                                super.visitInsn(opcode)
                            }
                        }
                    }
                    "([BLjava/lang/String;)Landroid/graphics/Bitmap;" -> {
                        object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {

                            private var label1: Label? = null

                            override fun visitCode() {
                                super.visitCode()
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "isGlide", "Z")
                                val label0 = Label()
                                mv.visitJumpInsn(IFEQ, label0)
                                mv.visitFieldInsn(GETSTATIC, "com/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate", "INSTANCE", "Lcom/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate;")
                                mv.visitVarInsn(ALOAD, 1)
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "mFrameWidth", "I")
                                mv.visitVarInsn(ALOAD, 0)
                                mv.visitFieldInsn(GETFIELD, ENTITY_SVGA_NAME, "mFrameHeight", "I")
                                mv.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate", "decodeBitmapFrom", "(Ljava/lang/Object;II)Landroid/graphics/Bitmap;", false)
                                label1 = Label()
                                mv.visitJumpInsn(GOTO, label1)
                                mv.visitLabel(label0)
                            }

                            override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                                if (owner == "com/opensource/svgaplayer/bitmap/SVGABitmapByteArrayDecoder" && name == "decodeBitmapFrom") {
                                    mv.visitLabel(label1)
                                }
                            }
                        }
                    }
                    else -> {
                        super.visitMethod(access, name, descriptor, signature, exceptions)
                    }
                }
            }


            else -> {
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }
        }
    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor? {
        var va = super.visitAnnotation(descriptor, visible)
        if (descriptor == "Lkotlin/Metadata;") {
            va = MetadataAnnotationVisitor(Opcodes.ASM9, va)
        }
        return va
    }

    /**
     * 重写注解头
     */
    inner class MetadataAnnotationVisitor(api: Int, mv: AnnotationVisitor) : AnnotationVisitor(api, mv) {
        override fun visitArray(name: String?): AnnotationVisitor {
            when (name) {
                "d1" -> {
                    val annotationVisitor1 = object : AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                        override fun visit(name: String?, value: Any?) {
                            super.visit(
                                null,
                                "\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000b\n\u0002\u0008\u0007\n\u0002\u0010\u000e\n\u0002\u0008\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0012\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0017\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0018\u00002\u00020\u0001B\u0017\u0008\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B'\u0008\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u00a2\u0006\u0002\u0010\nB\u0017\u0008\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u000c\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\rB'\u0008\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u000c\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u00a2\u0006\u0002\u0010\u000eB/\u0008\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0011B/\u0008\u0016\u0012\u0006\u0010\u000b\u001a\u00020\u000c\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u00a2\u0006\u0002\u0010\u0012J\u0006\u0010L\u001a\u000202J\u001a\u0010M\u001a\u0004\u0018\u00010)2\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u0018H\u0002J\u0012\u0010M\u001a\u0004\u0018\u00010)2\u0006\u0010P\u001a\u00020\u0018H\u0002J$\u0010Q\u001a\u00020 2\u0006\u0010R\u001a\u00020S2\u0012\u0010T\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050(H\u0002J\u0018\u0010U\u001a\u00020\u00052\u0006\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020OH\u0002J\u001c\u0010X\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00050(2\u0006\u0010\u000b\u001a\u00020\u000cH\u0002J\u001c\u0010Y\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020O0(2\u0006\u0010\u000b\u001a\u00020\u000cH\u0002J\u0018\u0010Z\u001a\u00020\u00182\u0006\u0010[\u001a\u00020\u00182\u0006\u0010\\\u001a\u00020\u0018H\u0002J\u0012\u0010]\u001a\u0004\u0018\u00010?2\u0006\u0010\u000b\u001a\u00020\u000cH\u0002J\u0010\u0010^\u001a\u0002022\u0006\u0010_\u001a\u00020\u000cH\u0002J\u0010\u0010^\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J%\u0010`\u001a\u0002022\u000c\u0010a\u001a\u0008\u0012\u0004\u0012\u000202012\u0008\u0010b\u001a\u0004\u0018\u000106H\u0000\u00a2\u0006\u0002\u0008cJ\u0010\u0010d\u001a\u0002022\u0006\u0010\u000b\u001a\u00020\u000cH\u0002J\u0010\u0010d\u001a\u0002022\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001e\u0010e\u001a\u0002022\u0006\u0010\u000b\u001a\u00020\u000c2\u000c\u0010f\u001a\u0008\u0012\u0004\u0012\u00020201H\u0002J\u0010\u0010g\u001a\u0002022\u0006\u0010h\u001a\u00020\u0003H\u0002J\u0010\u0010i\u001a\u0002022\u0006\u0010j\u001a\u00020kH\u0002J\u001e\u0010l\u001a\u0002022\u0006\u0010\u000b\u001a\u00020\u000c2\u000c\u0010f\u001a\u0008\u0012\u0004\u0012\u00020201H\u0002R\u001e\u0010\u0014\u001a\u00020\u00082\u0006\u0010\u0013\u001a\u00020\u0008@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0015\u0010\u0016R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001a\u0010\u001b\"\u0004\u0008\u001c\u0010\u001dR \u0010\u001e\u001a\u0008\u0012\u0004\u0012\u00020 0\u001fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008!\u0010\"\"\u0004\u0008#\u0010\$R\u001e\u0010%\u001a\u00020\u00082\u0006\u0010\u0013\u001a\u00020\u0008@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008&\u0010\u0016R&\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020)0(X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008*\u0010+\"\u0004\u0008,\u0010-R\u000e\u0010.\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u00100\u001a\u0008\u0012\u0004\u0012\u00020201X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00103\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00104\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00105\u001a\u0004\u0018\u000106X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u00107\u001a\u0004\u0018\u00010\u000cX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u00088\u00109\"\u0004\u0008:\u0010;R\u0010\u0010<\u001a\u0004\u0018\u00010=X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010>\u001a\u0004\u0018\u00010?X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008@\u0010A\"\u0004\u0008B\u0010CR \u0010D\u001a\u0008\u0012\u0004\u0012\u00020E0\u001fX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008F\u0010\"\"\u0004\u0008G\u0010\$R\u001e\u0010I\u001a\u00020H2\u0006\u0010\u0013\u001a\u00020H@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008J\u0010K\u00a8\u0006m"
                            )
                        }
                    }
                    return annotationVisitor1
                }

                "d2" -> {
                    val annotationVisitor1 = object : AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                        private var isJump = false

                        override fun visit(name: String?, value: Any?) {
                            when (value) {
                                "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;II)V" -> {
                                    super.visit(name, value)
                                    av.visit(null, "glide")
                                    av.visit(null, "")
                                    av.visit(null, "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V")
                                    av.visit(null, "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V")
                                }

                                "antiAlias" -> {
                                    super.visit(name, value)
                                    isJump = true
                                }

                                "mCacheDir" -> {
                                    av.visit(null, "isGlide")
                                    super.visit(name, value)
                                }

                                "" -> {
                                    if (!isJump) {
                                        super.visit(name, value)
                                    }
                                }

                                else -> {
                                    super.visit(name, value)
                                }
                            }
                        }
                    }
                    return annotationVisitor1
                }

                else -> {
                    return super.visitArray(name)
                }
            }
        }
    }
}