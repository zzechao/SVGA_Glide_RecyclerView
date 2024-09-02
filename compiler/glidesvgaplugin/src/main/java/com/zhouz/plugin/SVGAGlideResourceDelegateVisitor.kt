package com.zhouz.plugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ACC_FINAL
import org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.Opcodes.ACONST_NULL
import org.objectweb.asm.Opcodes.ALOAD
import org.objectweb.asm.Opcodes.ASTORE
import org.objectweb.asm.Opcodes.CHECKCAST
import org.objectweb.asm.Opcodes.DUP
import org.objectweb.asm.Opcodes.GETFIELD
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.GOTO
import org.objectweb.asm.Opcodes.IADD
import org.objectweb.asm.Opcodes.ICONST_0
import org.objectweb.asm.Opcodes.IFEQ
import org.objectweb.asm.Opcodes.IFNULL
import org.objectweb.asm.Opcodes.ILOAD
import org.objectweb.asm.Opcodes.INVOKEINTERFACE
import org.objectweb.asm.Opcodes.INVOKESPECIAL
import org.objectweb.asm.Opcodes.INVOKESTATIC
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL
import org.objectweb.asm.Opcodes.IRETURN
import org.objectweb.asm.Opcodes.ISTORE
import org.objectweb.asm.Opcodes.NEW
import org.objectweb.asm.Opcodes.NOP
import org.objectweb.asm.Opcodes.POP
import org.objectweb.asm.Opcodes.RETURN


/**
 * @author:zhouz
 * @date: 2024/8/8 18:33
 * description：修改SVGAGlideResourceDelegate的修改
 */
class SVGAGlideResourceDelegateVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {

    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        return when (name) {
            "mapSize" -> {
                super.visitMethod(access, name, descriptor, signature, exceptions).apply {
                    createMapSize()
                }
            }

            "recycleImage" -> {
                super.visitMethod(access, name, descriptor, signature, exceptions).apply {
                    createRecycleImage()
                }
            }

            "getSize", "recycle" -> {
                object : MethodVisitor(api, super.visitMethod(access, name, descriptor, signature, exceptions)) {
                    override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
                        when (name) {
                            "mapSize" -> {
                                super.visitMethodInsn(opcode, owner, "mapSizeZ", descriptor, isInterface)
                            }

                            "recycleImage" -> {
                                super.visitMethodInsn(opcode, owner, "recycleImageZ", descriptor, isInterface)
                            }

                            else -> {
                                super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                            }
                        }
                    }
                }
            }

            else -> {
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }
        }
    }

    private fun createMapSize() {
        val methodVisitor = cv.visitMethod(ACC_PRIVATE or ACC_FINAL, "mapSizeZ", "()I", null, null)
        methodVisitor.visitCode()
        val label0 = Label()
        val label1 = Label()
        val label2 = Label()
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable")
        val label3 = Label()
        val label4 = Label()
        val label5 = Label()
        methodVisitor.visitTryCatchBlock(label3, label4, label5, "java/lang/Throwable")
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 1)
        methodVisitor.visitLabel(label3)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false)
        methodVisitor.visitInsn(DUP)
        val label6 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label6)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "getImageMap\$com_opensource_svgaplayer", "()Ljava/util/HashMap;", false)
        val label7 = Label()
        methodVisitor.visitJumpInsn(GOTO, label7)
        methodVisitor.visitLabel(label6)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label7)
        methodVisitor.visitVarInsn(ASTORE, 2)
        methodVisitor.visitVarInsn(ALOAD, 2)
        methodVisitor.visitInsn(DUP)
        val label8 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label8)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map")
        methodVisitor.visitVarInsn(ASTORE, 3)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 4)
        methodVisitor.visitVarInsn(ALOAD, 3)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        methodVisitor.visitVarInsn(ASTORE, 5)
        val label9 = Label()
        methodVisitor.visitLabel(label9)
        methodVisitor.visitVarInsn(ALOAD, 5)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        val label10 = Label()
        methodVisitor.visitJumpInsn(IFEQ, label10)
        methodVisitor.visitVarInsn(ALOAD, 5)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map\$Entry")
        methodVisitor.visitVarInsn(ASTORE, 6)
        methodVisitor.visitVarInsn(ALOAD, 6)
        methodVisitor.visitVarInsn(ASTORE, 7)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 8)
        methodVisitor.visitVarInsn(ILOAD, 1)
        methodVisitor.visitVarInsn(ALOAD, 7)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap")
        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/bumptech/glide/util/Util", "getBitmapByteSize", "(Landroid/graphics/Bitmap;)I", false)
        methodVisitor.visitInsn(IADD)
        methodVisitor.visitVarInsn(ISTORE, 1)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label9)
        methodVisitor.visitLabel(label10)
        methodVisitor.visitInsn(NOP)
        val label11 = Label()
        methodVisitor.visitJumpInsn(GOTO, label11)
        methodVisitor.visitLabel(label8)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitLabel(label4)
        methodVisitor.visitJumpInsn(GOTO, label11)
        methodVisitor.visitLabel(label5)
        methodVisitor.visitVarInsn(ASTORE, 2)
        methodVisitor.visitLabel(label0)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getImageMapField", "()Lcom/svga/glide/util/ReflectUtils;", false)
        methodVisitor.visitInsn(DUP)
        val label12 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label12)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/util/ReflectUtils", "get", "()Ljava/lang/Object;", false)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/HashMap")
        val label13 = Label()
        methodVisitor.visitJumpInsn(GOTO, label13)
        methodVisitor.visitLabel(label12)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label13)
        methodVisitor.visitVarInsn(ASTORE, 3)
        methodVisitor.visitVarInsn(ALOAD, 3)
        methodVisitor.visitInsn(DUP)
        val label14 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label14)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map")
        methodVisitor.visitVarInsn(ASTORE, 4)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 5)
        methodVisitor.visitVarInsn(ALOAD, 4)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        methodVisitor.visitVarInsn(ASTORE, 6)
        val label15 = Label()
        methodVisitor.visitLabel(label15)
        methodVisitor.visitVarInsn(ALOAD, 6)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        val label16 = Label()
        methodVisitor.visitJumpInsn(IFEQ, label16)
        methodVisitor.visitVarInsn(ALOAD, 6)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map\$Entry")
        methodVisitor.visitVarInsn(ASTORE, 7)
        methodVisitor.visitVarInsn(ALOAD, 7)
        methodVisitor.visitVarInsn(ASTORE, 8)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 9)
        methodVisitor.visitVarInsn(ILOAD, 1)
        methodVisitor.visitVarInsn(ALOAD, 8)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap")
        methodVisitor.visitMethodInsn(INVOKESTATIC, "com/bumptech/glide/util/Util", "getBitmapByteSize", "(Landroid/graphics/Bitmap;)I", false)
        methodVisitor.visitInsn(IADD)
        methodVisitor.visitVarInsn(ISTORE, 1)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label15)
        methodVisitor.visitLabel(label16)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label11)
        methodVisitor.visitLabel(label14)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitLabel(label1)
        methodVisitor.visitJumpInsn(GOTO, label11)
        methodVisitor.visitLabel(label2)
        methodVisitor.visitVarInsn(ASTORE, 3)
        methodVisitor.visitLabel(label11)
        methodVisitor.visitVarInsn(ILOAD, 1)
        methodVisitor.visitInsn(IRETURN)
        methodVisitor.visitMaxs(2, 10)
        methodVisitor.visitEnd()
    }

    private fun createRecycleImage() {
        val methodVisitor = cv.visitMethod(ACC_PRIVATE or ACC_FINAL, "recycleImageZ", "()V", null, null)
        methodVisitor.visitCode()
        val label0 = Label()
        val label1 = Label()
        val label2 = Label()
        methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable")
        val label3 = Label()
        val label4 = Label()
        val label5 = Label()
        methodVisitor.visitTryCatchBlock(label3, label4, label5, "java/lang/Throwable")
        methodVisitor.visitLabel(label3)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false)
        methodVisitor.visitInsn(DUP)
        val label6 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label6)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "getImageMap\$com_opensource_svgaplayer", "()Ljava/util/HashMap;", false)
        val label7 = Label()
        methodVisitor.visitJumpInsn(GOTO, label7)
        methodVisitor.visitLabel(label6)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label7)
        methodVisitor.visitVarInsn(ASTORE, 1)
        methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog\$com_opensource_svgaplayer", "()Lcom/svga/glide/log/ILog;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "TAG", "Ljava/lang/String;")
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder")
        methodVisitor.visitInsn(DUP)
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        methodVisitor.visitLdcInsn("recycle imageMap for plugin ")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getModel", "()Ljava/lang/String;", false)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitLdcInsn(" size:")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitVarInsn(ALOAD, 1)
        methodVisitor.visitInsn(DUP)
        val label8 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label8)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "size", "()I", false)
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false)
        val label9 = Label()
        methodVisitor.visitJumpInsn(GOTO, label9)
        methodVisitor.visitLabel(label8)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label9)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true)
        methodVisitor.visitVarInsn(ALOAD, 1)
        methodVisitor.visitInsn(DUP)
        val label10 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label10)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map")
        methodVisitor.visitVarInsn(ASTORE, 2)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 3)
        methodVisitor.visitVarInsn(ALOAD, 2)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        methodVisitor.visitVarInsn(ASTORE, 4)
        val label11 = Label()
        methodVisitor.visitLabel(label11)
        methodVisitor.visitVarInsn(ALOAD, 4)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        val label12 = Label()
        methodVisitor.visitJumpInsn(IFEQ, label12)
        methodVisitor.visitVarInsn(ALOAD, 4)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map\$Entry")
        methodVisitor.visitVarInsn(ASTORE, 5)
        methodVisitor.visitVarInsn(ALOAD, 5)
        methodVisitor.visitVarInsn(ASTORE, 6)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 7)
        methodVisitor.visitVarInsn(ALOAD, 6)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/graphics/Bitmap", "recycle", "()V", false)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label11)
        methodVisitor.visitLabel(label12)
        methodVisitor.visitInsn(NOP)
        val label13 = Label()
        methodVisitor.visitJumpInsn(GOTO, label13)
        methodVisitor.visitLabel(label10)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitLabel(label4)
        methodVisitor.visitJumpInsn(GOTO, label13)
        methodVisitor.visitLabel(label5)
        methodVisitor.visitVarInsn(ASTORE, 1)
        methodVisitor.visitLabel(label0)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getImageMapField", "()Lcom/svga/glide/util/ReflectUtils;", false)
        methodVisitor.visitInsn(DUP)
        val label14 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label14)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/util/ReflectUtils", "get", "()Ljava/lang/Object;", false)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/HashMap")
        val label15 = Label()
        methodVisitor.visitJumpInsn(GOTO, label15)
        methodVisitor.visitLabel(label14)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label15)
        methodVisitor.visitVarInsn(ASTORE, 2)
        methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog\$com_opensource_svgaplayer", "()Lcom/svga/glide/log/ILog;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "TAG", "Ljava/lang/String;")
        methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder")
        methodVisitor.visitInsn(DUP)
        methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false)
        methodVisitor.visitLdcInsn("recycle imageMapField ")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getModel", "()Ljava/lang/String;", false)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitLdcInsn(" size:")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitVarInsn(ALOAD, 2)
        methodVisitor.visitInsn(DUP)
        val label16 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label16)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "size", "()I", false)
        methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false)
        val label17 = Label()
        methodVisitor.visitJumpInsn(GOTO, label17)
        methodVisitor.visitLabel(label16)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(ACONST_NULL)
        methodVisitor.visitLabel(label17)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true)
        methodVisitor.visitVarInsn(ALOAD, 2)
        methodVisitor.visitInsn(DUP)
        val label18 = Label()
        methodVisitor.visitJumpInsn(IFNULL, label18)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map")
        methodVisitor.visitVarInsn(ASTORE, 3)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 4)
        methodVisitor.visitVarInsn(ALOAD, 3)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
        methodVisitor.visitVarInsn(ASTORE, 5)
        val label19 = Label()
        methodVisitor.visitLabel(label19)
        methodVisitor.visitVarInsn(ALOAD, 5)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
        val label20 = Label()
        methodVisitor.visitJumpInsn(IFEQ, label20)
        methodVisitor.visitVarInsn(ALOAD, 5)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map\$Entry")
        methodVisitor.visitVarInsn(ASTORE, 6)
        methodVisitor.visitVarInsn(ALOAD, 6)
        methodVisitor.visitVarInsn(ASTORE, 7)
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitVarInsn(ISTORE, 8)
        methodVisitor.visitVarInsn(ALOAD, 7)
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
        methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/graphics/Bitmap", "recycle", "()V", false)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label19)
        methodVisitor.visitLabel(label20)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitJumpInsn(GOTO, label13)
        methodVisitor.visitLabel(label18)
        methodVisitor.visitInsn(POP)
        methodVisitor.visitInsn(NOP)
        methodVisitor.visitLabel(label1)
        methodVisitor.visitJumpInsn(GOTO, label13)
        methodVisitor.visitLabel(label2)
        methodVisitor.visitVarInsn(ASTORE, 2)
        methodVisitor.visitLabel(label13)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(5, 9)
        methodVisitor.visitEnd()

    }

    override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
        return object : AnnotationVisitor(api, super.visitAnnotation(descriptor, visible)) {
            override fun visitArray(name: String?): AnnotationVisitor {
                when (name) {
                    "d1" -> {
                        val annotationVisitor1 = object : AnnotationVisitor(api, super.visitArray(name)) {
                            override fun visit(name: String?, value: Any?) {
                                super.visit(
                                    null,
                                    "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0003\n\u0002\u0010\u0002\n\u0002\u0008\u0003\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0008\u0010\u0007\u001a\u00020\u0002H\u0016J\u000e\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\u00020\u0009H\u0016J\u0008\u0010\n\u001a\u00020\u000bH\u0016J\u0008\u0010\u000c\u001a\u00020\u000bH\u0002J\u0008\u0010\r\u001a\u00020\u000bH\u0002J\u0008\u0010\u000e\u001a\u00020\u000fH\u0016J\u0008\u0010\u0010\u001a\u00020\u000fH\u0002J\u0008\u0010\u0011\u001a\u00020\u000fH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"
                                )
                            }
                        }
                        return annotationVisitor1
                    }

                    "d2" -> {
                        val annotationVisitor1 = object : AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                            override fun visit(name: String?, value: Any?) {
                                when (value) {
                                    "mapSize" -> {
                                        super.visit(name, value)
                                        av.visit(null, "mapSizeZ")
                                    }

                                    "recycleImage" -> {
                                        super.visit(name, value)
                                        av.visit(null, "recycleImageZ")
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
}