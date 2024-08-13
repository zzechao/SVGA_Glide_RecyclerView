package com.zhouz.plugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author:zhouz
 * @date: 2024/8/5 17:24
 * description：修改SVGAVideoEntity的 prepare方法为 public
 */
class SVGAEntityClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
    override fun visitMethod(
        access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?
    ): MethodVisitor {
        return when {
            name == HookParams.ENTITY_SVGA_CLASS_METHOD -> {
                Logger.i("SVGAEntityClassVisitor visitMethod name:${name}")
                super.visitMethod(
                    Opcodes.ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD_NEW, descriptor, signature, exceptions
                )
            }

            name == HookParams.ENTITY_SVGA_CLASS_METHOD2 -> {
                super.visitMethod(
                    Opcodes.ACC_PUBLIC, HookParams.ENTITY_SVGA_CLASS_METHOD2, descriptor, signature, exceptions
                )
            }

            name == "getAntiAlias" && descriptor == "()Z" -> {
                val fieldVisitor = cv.visitField(Opcodes.ACC_PRIVATE, "isGlide", "Z", null, null)
                fieldVisitor.visitEnd()
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }

            name == "<init>" && descriptor == "(Lorg/json/JSONObject;Ljava/io/File;)V" -> {
                val initMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                    override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                        if (owner == "com/opensource/svgaplayer/SVGAVideoEntity") {
                            this.mv.visitInsn(Opcodes.ICONST_0)
                            this.mv.visitIntInsn(Opcodes.BIPUSH, 16);
                            this.mv.visitInsn(Opcodes.ACONST_NULL)
                            super.visitMethodInsn(opcode, owner, name, "(Lorg/json/JSONObject;Ljava/io/File;IIZILkotlin/jvm/internal/DefaultConstructorMarker;)V", isInterface)
                        } else {
                            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                        }
                    }

                    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                        super.visitMaxs(8, maxLocals)
                    }
                }
                initMethod
            }

            name == "<init>" && descriptor == "(Lorg/json/JSONObject;Ljava/io/File;II)V" -> {
                val initMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V", signature, exceptions)) {
                    override fun visitAnnotableParameterCount(parameterCount: Int, visible: Boolean) {
                        super.visitAnnotableParameterCount(5, visible)
                    }

                    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
                        super.visitFieldInsn(opcode, owner, name, descriptor)
                        if (name == "mCacheDir") {
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitVarInsn(Opcodes.ILOAD, 5)
                            mv.visitFieldInsn(Opcodes.PUTFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "isGlide", "Z")
                        }
                    }

                    override fun visitVarInsn(opcode: Int, varIndex: Int) {
                        val newVarIndex = when (varIndex) {
                            5 -> 6
                            6 -> 7
                            else -> {
                                varIndex
                            }
                        }
                        super.visitVarInsn(opcode, newVarIndex)
                    }

                    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                        super.visitMaxs(maxStack, 8)
                    }
                }
                initMethod.apply {
                    val methodVisitor = cv.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_SYNTHETIC, "<init>", "(Lorg/json/JSONObject;Ljava/io/File;IIZILkotlin/jvm/internal/DefaultConstructorMarker;)V", null, null)
                    methodVisitor.visitCode()
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 6)
                    methodVisitor.visitIntInsn(Opcodes.BIPUSH, 16)
                    methodVisitor.visitInsn(Opcodes.IAND)
                    val label0 = Label()
                    methodVisitor.visitJumpInsn(Opcodes.IFEQ, label0)
                    methodVisitor.visitInsn(Opcodes.ICONST_0)
                    methodVisitor.visitVarInsn(Opcodes.ISTORE, 5)
                    methodVisitor.visitLabel(label0)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 2)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 3)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 4)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 5)
                    methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/opensource/svgaplayer/SVGAVideoEntity", "<init>", "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V", false)
                    methodVisitor.visitInsn(Opcodes.RETURN)
                    methodVisitor.visitMaxs(6, 8)
                    methodVisitor.visitEnd()
                }
            }

            name == "<init>" && descriptor == "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;)V" -> {
                val initMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                    override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                        if (owner == "com/opensource/svgaplayer/SVGAVideoEntity") {
                            this.mv.visitInsn(Opcodes.ICONST_0)
                            this.mv.visitIntInsn(Opcodes.BIPUSH, 16);
                            this.mv.visitInsn(Opcodes.ACONST_NULL)
                            super.visitMethodInsn(opcode, owner, name, "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZILkotlin/jvm/internal/DefaultConstructorMarker;)V", isInterface)
                        } else {
                            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                        }
                    }

                    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                        super.visitMaxs(8, maxLocals)
                    }
                }
                initMethod
            }

            name == "<init>" && descriptor == "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;II)V" -> {
                val initMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V", signature, exceptions)) {
                    override fun visitAnnotableParameterCount(parameterCount: Int, visible: Boolean) {
                        super.visitAnnotableParameterCount(5, visible)
                    }

                    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
                        super.visitFieldInsn(opcode, owner, name, descriptor)
                        if (name == "movieItem") {
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitVarInsn(Opcodes.ILOAD, 5)
                            mv.visitFieldInsn(Opcodes.PUTFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "isGlide", "Z")
                        }
                    }

                    override fun visitVarInsn(opcode: Int, varIndex: Int) {
                        val newVarIndex = when (varIndex) {
                            5 -> 6
                            6 -> 7
                            7 -> 8
                            else -> {
                                varIndex
                            }
                        }
                        super.visitVarInsn(opcode, newVarIndex)
                    }

                    override fun visitMaxs(maxStack: Int, maxLocals: Int) {
                        super.visitMaxs(maxStack, 9)
                    }
                }
                initMethod.apply {
                    val methodVisitor = cv.visitMethod(Opcodes.ACC_PUBLIC or Opcodes.ACC_SYNTHETIC, "<init>", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZILkotlin/jvm/internal/DefaultConstructorMarker;)V", null, null)
                    methodVisitor.visitCode()
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 6)
                    methodVisitor.visitIntInsn(Opcodes.BIPUSH, 16)
                    methodVisitor.visitInsn(Opcodes.IAND)
                    val label0 = Label()
                    methodVisitor.visitJumpInsn(Opcodes.IFEQ, label0)
                    methodVisitor.visitInsn(Opcodes.ICONST_0)
                    methodVisitor.visitVarInsn(Opcodes.ISTORE, 5)
                    methodVisitor.visitLabel(label0)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 0)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 1)
                    methodVisitor.visitVarInsn(Opcodes.ALOAD, 2)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 3)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 4)
                    methodVisitor.visitVarInsn(Opcodes.ILOAD, 5)
                    methodVisitor.visitMethodInsn(Opcodes.INVOKESPECIAL, "com/opensource/svgaplayer/SVGAVideoEntity", "<init>", "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V", false)
                    methodVisitor.visitInsn(Opcodes.RETURN)
                    methodVisitor.visitMaxs(6, 8)
                    methodVisitor.visitEnd()
                }
            }

            name == "createBitmap" && descriptor == "(Ljava/lang/String;)Landroid/graphics/Bitmap;" -> {
                val createBitmapMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {

                    private var label1: Label? = null

                    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
                        if (owner == "com/opensource/svgaplayer/bitmap/SVGABitmapFileDecoder" && name == "INSTANCE") {
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "isGlide", "Z")
                            val label0 = Label()
                            mv.visitJumpInsn(Opcodes.IFEQ, label0)
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate", "INSTANCE", "Lcom/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate;")
                            mv.visitVarInsn(Opcodes.ALOAD, 1)
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "mFrameWidth", "I")
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "mFrameHeight", "I")
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/svga/glide/bitmap/SVGAGlideBitmapFileDecoderDelegate", "decodeBitmapFrom", "(Ljava/lang/Object;II)Landroid/graphics/Bitmap;", false)
                            label1 = Label()
                            mv.visitJumpInsn(Opcodes.GOTO, label1)
                            mv.visitLabel(label0)
                        }
                        super.visitFieldInsn(opcode, owner, name, descriptor)
                    }

                    override fun visitInsn(opcode: Int) {
                        if (Opcodes.ARETURN == opcode) {
                            mv.visitLabel(label1)
                        }
                        super.visitInsn(opcode)
                    }
                }
                createBitmapMethod
            }

            name == "createBitmap" && descriptor == "([BLjava/lang/String;)Landroid/graphics/Bitmap;" -> {
                val createBitmapMethod = object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {

                    private var label2: Label? = null

                    override fun visitFieldInsn(opcode: Int, owner: String?, name: String?, descriptor: String?) {
                        if (owner == "com/opensource/svgaplayer/bitmap/SVGABitmapByteArrayDecoder" && name == "INSTANCE") {
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "isGlide", "Z")
                            val label1 = Label()
                            mv.visitJumpInsn(Opcodes.IFEQ, label1)
                            mv.visitFieldInsn(Opcodes.GETSTATIC, "com/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate", "INSTANCE", "Lcom/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate;")
                            mv.visitVarInsn(Opcodes.ALOAD, 1)
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "mFrameWidth", "I")
                            mv.visitVarInsn(Opcodes.ALOAD, 0)
                            mv.visitFieldInsn(Opcodes.GETFIELD, "com/opensource/svgaplayer/SVGAVideoEntity", "mFrameHeight", "I")
                            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/svga/glide/bitmap/SVGAGlideBitmapByteDecoderDelegate", "decodeBitmapFrom", "(Ljava/lang/Object;II)Landroid/graphics/Bitmap;", false)
                            label2 = Label()
                            Logger.i("visitFieldInsn label2 set")
                            mv.visitJumpInsn(Opcodes.GOTO, label2)
                            mv.visitLabel(label1)
                            super.visitFieldInsn(opcode, owner, name, descriptor)
                        } else {
                            super.visitFieldInsn(opcode, owner, name, descriptor)
                        }
                    }

                    override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                        super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                        if (owner == "com/opensource/svgaplayer/bitmap/SVGABitmapByteArrayDecoder" && name == "decodeBitmapFrom") {
                            Logger.i("visitMethodInsn label2 visitLabel")
                            mv.visitLabel(label2)
                        }
                    }
                }
                createBitmapMethod
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


    override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor {
        return super.visitField(access, name, descriptor, signature, value)
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
                                "\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0010\u000e\n\u0002\u0008\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0007\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0006\n\u0002\u0010\u0012\n\u0002\u0008\u0003\n\u0002\u0018\u0002\n\u0002\u0008\u0017\n\u0002\u0018\u0002\n\u0002\u0008\u0002\u0018\u00002\u00020\u0001B\u0017\u0008\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006B1\u0008\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u0012\u0008\u0008\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\u000cB\u0017\u0008\u0016\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u000fB1\u0008\u0016\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u0012\u0008\u0008\u0002\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0002\u0010\u0010J\u0006\u0010J\u001a\u000200J\u001a\u0010K\u001a\u0004\u0018\u00010'2\u0006\u0010L\u001a\u00020M2\u0006\u0010N\u001a\u00020\u0016H\u0002J\u0012\u0010K\u001a\u0004\u0018\u00010'2\u0006\u0010N\u001a\u00020\u0016H\u0002J$\u0010O\u001a\u00020\u001e2\u0006\u0010P\u001a\u00020Q2\u0012\u0010R\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00050&H\u0002J\u0018\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020\u00052\u0006\u0010U\u001a\u00020MH\u0002J\u001c\u0010V\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00050&2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u001c\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020M0&2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010X\u001a\u00020\u00162\u0006\u0010Y\u001a\u00020\u00162\u0006\u0010Z\u001a\u00020\u0016H\u0002J\u0012\u0010[\u001a\u0004\u0018\u00010=2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\\\u001a\u0002002\u0006\u0010]\u001a\u00020\u000eH\u0002J\u0010\u0010\\\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J%\u0010^\u001a\u0002002\u000c\u0010_\u001a\u0008\u0012\u0004\u0012\u0002000/2\u0008\u0010`\u001a\u0004\u0018\u000104H\u0000\u00a2\u0006\u0002\u0008aJ\u0010\u0010b\u001a\u0002002\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010b\u001a\u0002002\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u001e\u0010c\u001a\u0002002\u0006\u0010\r\u001a\u00020\u000e2\u000c\u0010d\u001a\u0008\u0012\u0004\u0012\u0002000/H\u0002J\u0010\u0010e\u001a\u0002002\u0006\u0010f\u001a\u00020\u0003H\u0002J\u0010\u0010g\u001a\u0002002\u0006\u0010h\u001a\u00020iH\u0002J\u001e\u0010j\u001a\u0002002\u0006\u0010\r\u001a\u00020\u000e2\u000c\u0010d\u001a\u0008\u0012\u0004\u0012\u0002000/H\u0002R\u001e\u0010\u0012\u001a\u00020\u00082\u0006\u0010\u0011\u001a\u00020\u0008@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u00020\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0018\u0010\u0019\"\u0004\u0008\u001a\u0010\u001bR \u0010\u001c\u001a\u0008\u0012\u0004\u0012\u00020\u001e0\u001dX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001f\u0010 \"\u0004\u0008!\u0010\"R\u001e\u0010#\u001a\u00020\u00082\u0006\u0010\u0011\u001a\u00020\u0008@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008$\u0010\u0014R&\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020'0&X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008(\u0010)\"\u0004\u0008*\u0010+R\u000e\u0010,\u001a\u00020\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010.\u001a\u0008\u0012\u0004\u0012\u0002000/X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u00101\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u00103\u001a\u0004\u0018\u000104X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u00105\u001a\u0004\u0018\u00010\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u00086\u00107\"\u0004\u00088\u00109R\u0010\u0010:\u001a\u0004\u0018\u00010;X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010<\u001a\u0004\u0018\u00010=X\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008>\u0010?\"\u0004\u0008@\u0010AR \u0010B\u001a\u0008\u0012\u0004\u0012\u00020C0\u001dX\u0080\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008D\u0010 \"\u0004\u0008E\u0010\"R\u001e\u0010G\u001a\u00020F2\u0006\u0010\u0011\u001a\u00020F@BX\u0086\u000e\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008H\u0010I\u00a8\u0006k"
                            )
                        }
                    }
                    return annotationVisitor1
                }

                "d2" -> {
                    val annotationVisitor1 = object : AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                        override fun visit(name: String?, value: Any?) {
                            when (value) {
                                "(Lorg/json/JSONObject;Ljava/io/File;II)V" -> {
                                    visit(null, "glide")
                                    visit(null, "")
                                    super.visit(name, "(Lorg/json/JSONObject;Ljava/io/File;IIZ)V")
                                }

                                "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;II)V" -> {
                                    super.visit(name, "(Lcom/opensource/svgaplayer/proto/MovieEntity;Ljava/io/File;IIZ)V")
                                }

                                "getAntiAlias" -> {
                                    visit(null, "");
                                    super.visit(name, value)
                                }

                                "mCacheDir" -> {
                                    visit(null, "isGlide")
                                    super.visit(name, value)
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