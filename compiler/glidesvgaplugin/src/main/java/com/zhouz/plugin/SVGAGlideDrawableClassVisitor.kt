package com.zhouz.plugin

import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.Opcodes.ACC_FINAL
import org.objectweb.asm.Opcodes.ACC_PRIVATE
import org.objectweb.asm.Opcodes.ALOAD
import org.objectweb.asm.Opcodes.GETFIELD
import org.objectweb.asm.Opcodes.GETSTATIC
import org.objectweb.asm.Opcodes.ICONST_0
import org.objectweb.asm.Opcodes.INVOKEINTERFACE
import org.objectweb.asm.Opcodes.INVOKEVIRTUAL
import org.objectweb.asm.Opcodes.RETURN


/**
 * @author:zhouz
 * @date: 2024/8/14 17:54
 * description：针对com.svga.glide.SVGAAnimationDrawable的反射调用的修改
 */
class SVGAGlideDrawableClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {

    override fun visitField(access: Int, name: String?, descriptor: String?, signature: String?, value: Any?): FieldVisitor? {
        return when (name) {
            "clearedField",
            "currentFrameField" -> {
                null
            }

            else -> {
                super.visitField(access, name, descriptor, signature, value)
            }
        }
    }


    override fun visitMethod(access: Int, name: String?, descriptor: String?, signature: String?, exceptions: Array<out String>?): MethodVisitor? {
        return when (name) {
            "setUpDrawableClear", "updateDrawableFrame" -> {
                null
            }

            else -> {
                super.visitMethod(access, name, descriptor, signature, exceptions)
            }
        }
    }

    override fun visitEnd() {
        setUpDrawableClearCreate()
        updateDrawableFrameCreate()
        super.visitEnd()
    }

    private fun setUpDrawableClearCreate() {
        val methodVisitor = cv.visitMethod(ACC_PRIVATE or ACC_FINAL, "setUpDrawableClear", "()V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAAnimationDrawable", "TAG", "Ljava/lang/String;")
        methodVisitor.visitLdcInsn("setUpDrawableClear for plugin")
        methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true)
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAAnimationDrawable", "drawer", "Lcom/opensource/svgaplayer/SVGADrawable;")
        methodVisitor.visitInsn(ICONST_0)
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGADrawable", "setCleared\$com_opensource_svgaplayer", "(Z)V", false)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(3, 1)
        methodVisitor.visitEnd()
    }

    private fun updateDrawableFrameCreate() {
        val methodVisitor = cv.visitMethod(ACC_PRIVATE or ACC_FINAL, "updateDrawableFrame", "()V", null, null)
        methodVisitor.visitCode()
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAAnimationDrawable", "drawer", "Lcom/opensource/svgaplayer/SVGADrawable;")
        methodVisitor.visitVarInsn(ALOAD, 0)
        methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAAnimationDrawable", "currentFrame", "I")
        methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGADrawable", "setCurrentFrame\$com_opensource_svgaplayer", "(I)V", false)
        methodVisitor.visitInsn(RETURN)
        methodVisitor.visitMaxs(2, 1)
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
                                    "\u0000~\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\n\n\u0002\u0018\u0002\n\u0002\u0008\r\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0010\n\u0002\u0018\u0002\n\u0002\u0008\u0008\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B/\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0008\u0012\u0006\u0010\u0009\u001a\u00020\u0008\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0008\u0008\u0002\u0010\u000c\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0010\u00105\u001a\u0002062\u0006\u00107\u001a\u000208H\u0016J\u0008\u00109\u001a\u00020:H\u0002J\u0008\u0010;\u001a\u00020\u0008H\u0016J\u0008\u0010<\u001a\u00020\rH\u0016J\u0010\u0010=\u001a\u0002062\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010@\u001a\u0002062\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010A\u001a\u0002062\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010B\u001a\u0002062\u0006\u0010>\u001a\u00020?H\u0016J\u0010\u0010C\u001a\u0002062\u0006\u0010>\u001a\u00020\u0018H\u0016J\u0010\u0010D\u001a\u0002062\u0008\u0008\u0002\u0010E\u001a\u00020\rJ\u0010\u0010F\u001a\u0002062\u0008\u0008\u0002\u0010E\u001a\u00020\rJ\u0010\u0010G\u001a\u0002062\u0006\u0010H\u001a\u00020\u0008H\u0016J(\u0010I\u001a\u0002062\u0006\u0010J\u001a\u00020\u00082\u0006\u0010K\u001a\u00020\u00082\u0006\u0010L\u001a\u00020\u00082\u0006\u0010M\u001a\u00020\u0008H\u0016J\u0012\u0010N\u001a\u0002062\u0008\u0010O\u001a\u0004\u0018\u00010PH\u0016J\u0008\u0010Q\u001a\u000206H\u0002J\u0018\u0010R\u001a\u00020\r2\u0006\u0010S\u001a\u00020\r2\u0006\u0010T\u001a\u00020\rH\u0016J\u0008\u0010U\u001a\u000206H\u0016J\u0008\u0010V\u001a\u000206H\u0016J\u0008\u0010W\u001a\u000206H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u0008\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0019\u0010\u001aR\u0011\u0010\u0009\u001a\u00020\u0008\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u001b\u0010\u001aR$\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001c\u001a\u00020\u001d@FX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001f\u0010 \"\u0004\u0008!\u0010\"R\u001a\u0010\u000c\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008#\u0010$\"\u0004\u0008%\u0010&R\u001c\u0010'\u001a\u0004\u0018\u00010(X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008)\u0010*\"\u0004\u0008+\u0010,R\u001a\u0010-\u001a\u00020\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008.\u0010/\"\u0004\u00080\u00101R\u000e\u00102\u001a\u00020\u0008X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0008\n\u0000\u001a\u0004\u00083\u00104\u00a8\u0006X"
                                )
                            }
                        }
                        return annotationVisitor1
                    }

                    "d2" -> {
                        val annotationVisitor1 = object : AnnotationVisitor(Opcodes.ASM9, super.visitArray(name)) {
                            override fun visit(name: String?, value: Any?) {
                                when (value) {
                                    "clearedField", "Ljava/lang/reflect/Field;", "currentFrameField" -> {
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