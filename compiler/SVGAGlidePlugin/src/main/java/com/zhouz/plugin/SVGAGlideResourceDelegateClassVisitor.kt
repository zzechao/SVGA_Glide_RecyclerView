package com.zhouz.plugin

import com.zhouz.plugin.HookParams.ENTITY_SVGA_NAME
import com.zhouz.plugin.HookParams.ENTITY_SVGA_RESOURCE_NAME
import com.zhouz.plugin.HookParams.SVGA_GLIDE_RESOURCE_DELEGATE_NAME
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.Label
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes


/**
 * @author:zhouz
 * @date: 2024/8/12 19:59
 * description：修改SVGAGlideResourceDelegate对resource.videoItem?.imageMap的回收
 */
class SVGAGlideResourceDelegateClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        var mv = super.visitMethod(access, name, descriptor, signature, exceptions)
        if (name == "getSize") {
            mv = GetSizeMethod(Opcodes.ASM9, mv)
        } else if (name == "recycle") {
            mv = RecycleMethod(Opcodes.ASM9, mv)
        }
        return mv
    }

    /**
     * com.zhouz.glidesvga.SVGAGlideResourceDelegate.getSize
     */
    inner class GetSizeMethod(api: Int, mv: MethodVisitor) : MethodVisitor(api, mv) {
        override fun visitCode() {
            super.visitCode()
            mv.visitInsn(Opcodes.ICONST_0)
            mv.visitVarInsn(Opcodes.ISTORE, 1)
            mv.visitVarInsn(Opcodes.ALOAD, 0)
            mv.visitFieldInsn(Opcodes.GETFIELD, SVGA_GLIDE_RESOURCE_DELEGATE_NAME, "resource", "L$ENTITY_SVGA_RESOURCE_NAME;")
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_RESOURCE_NAME, "getVideoItem", "()L$ENTITY_SVGA_NAME;", false)
            mv.visitInsn(Opcodes.DUP)
            val label0 = Label()
            mv.visitJumpInsn(Opcodes.IFNULL, label0)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_NAME, "getImageMap\$com_opensource_svgaplayer", "()Ljava/util/HashMap;", false)
            mv.visitInsn(Opcodes.DUP)
            mv.visitJumpInsn(Opcodes.IFNULL, label0)
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/HashMap", "values", "()Ljava/util/Collection;", false)
            mv.visitInsn(Opcodes.DUP)
            mv.visitJumpInsn(Opcodes.IFNULL, label0)
            mv.visitTypeInsn(Opcodes.CHECKCAST, "java/lang/Iterable")
            mv.visitVarInsn(Opcodes.ASTORE, 2)
            mv.visitInsn(Opcodes.ICONST_0)
            mv.visitVarInsn(Opcodes.ISTORE, 3)
            mv.visitVarInsn(Opcodes.ALOAD, 2)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/lang/Iterable", "iterator", "()Ljava/util/Iterator;", true)
            mv.visitVarInsn(Opcodes.ASTORE, 4)
            val label1 = Label()
            mv.visitLabel(label1)
            mv.visitVarInsn(Opcodes.ALOAD, 4)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
            val label2 = Label()
            mv.visitJumpInsn(Opcodes.IFEQ, label2)
            mv.visitVarInsn(Opcodes.ALOAD, 4)
            mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
            mv.visitVarInsn(Opcodes.ASTORE, 5)
            mv.visitVarInsn(Opcodes.ALOAD, 5)
            mv.visitTypeInsn(Opcodes.CHECKCAST, "android/graphics/Bitmap")
            mv.visitVarInsn(Opcodes.ASTORE, 6)
            mv.visitInsn(Opcodes.ICONST_0)
            mv.visitVarInsn(Opcodes.ISTORE, 7)
            mv.visitVarInsn(Opcodes.ILOAD, 1)
            mv.visitVarInsn(Opcodes.ALOAD, 6)
            mv.visitMethodInsn(Opcodes.INVOKESTATIC, "com/bumptech/glide/util/Util", "getBitmapByteSize", "(Landroid/graphics/Bitmap;)I", false)
            mv.visitInsn(Opcodes.IADD)
            mv.visitVarInsn(Opcodes.ISTORE, 1)
            mv.visitInsn(Opcodes.NOP)
            mv.visitInsn(Opcodes.NOP)
            mv.visitJumpInsn(Opcodes.GOTO, label1)
            mv.visitLabel(label2)
            mv.visitInsn(Opcodes.NOP)
            val label3 = Label()
            mv.visitJumpInsn(Opcodes.GOTO, label3)
            mv.visitLabel(label0)
            mv.visitInsn(Opcodes.POP)
            mv.visitInsn(Opcodes.NOP)
            mv.visitLabel(label3)
            mv.visitVarInsn(Opcodes.ILOAD, 1)
            mv.visitInsn(Opcodes.IRETURN)
            mv.visitMaxs(2, 8)
            mv.visitEnd()
        }
    }

    inner class RecycleMethod(api: Int, mv: MethodVisitor) : MethodVisitor(api, mv) {
        private var isMatchSetMovieItem = false
        private var isMatchGetVideoItem = false
        private var label2: Label? = null

        override fun visitMethodInsn(opcode: Int, owner: String?, name: String?, descriptor: String?, isInterface: Boolean) {
            if (name == "setMovieItem") {
                isMatchSetMovieItem = true
            } else if (name == "getVideoItem") {
                isMatchGetVideoItem = true
            }
            super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
        }

        override fun visitMaxs(maxStack: Int, maxLocals: Int) {
            super.visitMaxs(4, 9)
        }

        override fun visitLabel(label: Label?) {
            super.visitLabel(label)
            if (isMatchSetMovieItem) {
                isMatchSetMovieItem = false
                mv.visitFieldInsn(Opcodes.GETSTATIC, "com/zhouz/glidesvga/SVGAGlideEx", "INSTANCE", "Lcom/zhouz/glidesvga/SVGAGlideEx;")
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "com/zhouz/glidesvga/SVGAGlideEx", "getBitmapPool", "()Lcom/bumptech/glide/load/engine/bitmap_recycle/BitmapPool;", false)
                mv.visitInsn(Opcodes.DUP)
                label2 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, label2)
                mv.visitVarInsn(Opcodes.ASTORE, 1)
                mv.visitInsn(Opcodes.ICONST_0)
                mv.visitVarInsn(Opcodes.ISTORE, 2)
            }
        }

        override fun visitInsn(opcode: Int) {
            super.visitInsn(opcode)
            if (Opcodes.DUP == opcode && isMatchGetVideoItem) {
                isMatchGetVideoItem = false
                val label3 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, label3)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_NAME, "getImageMap\$com_opensource_svgaplayer", "()Ljava/util/HashMap;", false)
                mv.visitInsn(Opcodes.DUP)
                mv.visitJumpInsn(Opcodes.IFNULL, label3)
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/util/Map")
                mv.visitVarInsn(Opcodes.ASTORE, 3)
                mv.visitInsn(Opcodes.ICONST_0)
                mv.visitVarInsn(Opcodes.ISTORE, 4)
                mv.visitVarInsn(Opcodes.ALOAD, 3)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true)
                mv.visitVarInsn(Opcodes.ASTORE, 5)
                val label4 = Label()
                mv.visitLabel(label4)
                mv.visitVarInsn(Opcodes.ALOAD, 5)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true)
                val label5 = Label()
                mv.visitJumpInsn(Opcodes.IFEQ, label5)
                mv.visitVarInsn(Opcodes.ALOAD, 5)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true)
                mv.visitTypeInsn(Opcodes.CHECKCAST, "java/util/Map\$Entry")
                mv.visitVarInsn(Opcodes.ASTORE, 6)
                mv.visitVarInsn(Opcodes.ALOAD, 6)
                mv.visitVarInsn(Opcodes.ASTORE, 7)
                mv.visitInsn(Opcodes.ICONST_0)
                mv.visitVarInsn(Opcodes.ISTORE, 8)
                mv.visitVarInsn(Opcodes.ALOAD, 1)
                mv.visitVarInsn(Opcodes.ALOAD, 7)
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "java/util/Map\$Entry", "getValue", "()Ljava/lang/Object;", true)
                mv.visitTypeInsn(Opcodes.CHECKCAST, "android/graphics/Bitmap")
                mv.visitMethodInsn(Opcodes.INVOKEINTERFACE, "com/bumptech/glide/load/engine/bitmap_recycle/BitmapPool", "put", "(Landroid/graphics/Bitmap;)V", true)
                mv.visitInsn(Opcodes.NOP)
                mv.visitInsn(Opcodes.NOP)
                mv.visitJumpInsn(Opcodes.GOTO, label4)
                mv.visitLabel(label5)
                mv.visitInsn(Opcodes.NOP)
                val label6 = Label()
                mv.visitJumpInsn(Opcodes.GOTO, label6)
                mv.visitLabel(label3)
                mv.visitInsn(Opcodes.POP)
                mv.visitInsn(Opcodes.NOP)
                mv.visitLabel(label6)
                val label7 = Label()
                mv.visitJumpInsn(Opcodes.GOTO, label7)
                mv.visitLabel(label2)
                mv.visitInsn(Opcodes.POP)
                mv.visitInsn(Opcodes.NOP)
                mv.visitLabel(label7)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, SVGA_GLIDE_RESOURCE_DELEGATE_NAME, "resource", "L$ENTITY_SVGA_RESOURCE_NAME;")
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_RESOURCE_NAME, "getVideoItem", "()L$ENTITY_SVGA_NAME;", false)
                mv.visitInsn(Opcodes.DUP)
                val label8 = Label()
                mv.visitJumpInsn(Opcodes.IFNULL, label8)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_NAME, "getImageMap", "()Ljava/util/HashMap;", false)
                mv.visitInsn(Opcodes.DUP)
                mv.visitJumpInsn(Opcodes.IFNULL, label8)
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/HashMap", "clear", "()V", false)
                val label9 = Label()
                mv.visitJumpInsn(Opcodes.GOTO, label9)
                mv.visitLabel(label8)
                mv.visitInsn(Opcodes.POP)
                mv.visitLabel(label9)
                mv.visitVarInsn(Opcodes.ALOAD, 0)
                mv.visitFieldInsn(Opcodes.GETFIELD, SVGA_GLIDE_RESOURCE_DELEGATE_NAME, "resource", "L$ENTITY_SVGA_RESOURCE_NAME;")
                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, ENTITY_SVGA_RESOURCE_NAME, "getVideoItem", "()L$ENTITY_SVGA_NAME;", false)
                mv.visitInsn(Opcodes.DUP)
            }
        }
    }
}