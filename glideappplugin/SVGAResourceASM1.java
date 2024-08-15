package asm.com.svga.glide;
import org.objectweb.asm.AnnotationVisitor;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.ConstantDynamic;
import org.objectweb.asm.FieldVisitor;
import org.objectweb.asm.Handle;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.RecordComponentVisitor;
import org.objectweb.asm.Type;
import org.objectweb.asm.TypePath;
public class SVGAGlideResourceDelegateDump implements Opcodes {

    public static byte[] dump () throws Exception {

        ClassWriter classWriter = new ClassWriter(0);
        FieldVisitor fieldVisitor;
        RecordComponentVisitor recordComponentVisitor;
        MethodVisitor methodVisitor;
        AnnotationVisitor annotationVisitor0;

        classWriter.visit(V1_8, ACC_PUBLIC | ACC_FINAL | ACC_SUPER, "com/svga/glide/SVGAGlideResourceDelegate", "Ljava/lang/Object;Lcom/bumptech/glide/load/engine/Resource<Lcom/svga/glide/SVGAResource;>;", "java/lang/Object", new String[] { "com/bumptech/glide/load/engine/Resource" });

        {
            annotationVisitor0 = classWriter.visitAnnotation("Lkotlin/Metadata;", true);
            annotationVisitor0.visit("mv", new int[] {1,7,1});
            annotationVisitor0.visit("k", new Integer(1));
            annotationVisitor0.visit("xi", new Integer(48));
            {
                AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d1");
                annotationVisitor1.visit(null, "\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0003\n\u0002\u0010\u000e\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0010\u0002\n\u0002\u0008\u0002\u0018\u00002\u0008\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u00a2\u0006\u0002\u0010\u0004J\u0008\u0010\u0007\u001a\u00020\u0002H\u0016J\u000e\u0010\u0008\u001a\u0008\u0012\u0004\u0012\u00020\u00020\u0009H\u0016J\u0008\u0010\n\u001a\u00020\u000bH\u0016J\u0008\u0010\u000c\u001a\u00020\u000bH\u0002J\u0008\u0010\r\u001a\u00020\u000eH\u0016J\u0008\u0010\u000f\u001a\u00020\u000eH\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0002X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010");
                annotationVisitor1.visitEnd();
            }
            {
                AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d2");
                annotationVisitor1.visit(null, "Lcom/svga/glide/SVGAGlideResourceDelegate;");
                annotationVisitor1.visit(null, "Lcom/bumptech/glide/load/engine/Resource;");
                annotationVisitor1.visit(null, "Lcom/svga/glide/SVGAResource;");
                annotationVisitor1.visit(null, "resource");
                annotationVisitor1.visit(null, "(Lcom/svga/glide/SVGAResource;)V");
                annotationVisitor1.visit(null, "TAG");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "get");
                annotationVisitor1.visit(null, "getResourceClass");
                annotationVisitor1.visit(null, "Ljava/lang/Class;");
                annotationVisitor1.visit(null, "getSize");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "mapSize");
                annotationVisitor1.visit(null, "recycle");
                annotationVisitor1.visit(null, "");
                annotationVisitor1.visit(null, "recycleImage");
                annotationVisitor1.visit(null, "com.opensource.svgaplayer");
                annotationVisitor1.visitEnd();
            }
            annotationVisitor0.visitEnd();
        }
        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL, "resource", "Lcom/svga/glide/SVGAResource;", null, null);
            {
                annotationVisitor0 = fieldVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            fieldVisitor.visitEnd();
        }
        {
            fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL, "TAG", "Ljava/lang/String;", null, null);
            {
                annotationVisitor0 = fieldVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            fieldVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Lcom/svga/glide/SVGAResource;)V", null, null);
            methodVisitor.visitAnnotableParameterCount(1, false);
            {
                annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitLdcInsn("resource");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitLdcInsn("SVGAGlideResourceDelegate");
            methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "TAG", "Ljava/lang/String;");
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 2);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getResourceClass", "()Ljava/lang/Class;", "()Ljava/lang/Class<Lcom/svga/glide/SVGAResource;>;", null);
            {
                annotationVisitor0 = methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            methodVisitor.visitCode();
            methodVisitor.visitLdcInsn(Type.getType("Lcom/svga/glide/SVGAResource;"));
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "get", "()Lcom/svga/glide/SVGAResource;", null, null);
            {
                annotationVisitor0 = methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
                annotationVisitor0.visitEnd();
            }
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_FINAL, "mapSize", "()I", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable");
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 1);
            methodVisitor.visitLabel(label0);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getImageMapField", "()Lcom/svga/glide/util/ReflectUtils;", false);
            methodVisitor.visitInsn(DUP);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/util/ReflectUtils", "get", "()Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/HashMap");
            Label label4 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label4);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitLabel(label4);
            methodVisitor.visitVarInsn(ASTORE, 2);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitInsn(DUP);
            Label label5 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label5);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map");
            methodVisitor.visitVarInsn(ASTORE, 3);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 4);
            methodVisitor.visitVarInsn(ALOAD, 3);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true);
            methodVisitor.visitVarInsn(ASTORE, 5);
            Label label6 = new Label();
            methodVisitor.visitLabel(label6);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label label7 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label7);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map$Entry");
            methodVisitor.visitVarInsn(ASTORE, 6);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitVarInsn(ASTORE, 7);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 8);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitVarInsn(ALOAD, 7);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map$Entry", "getValue", "()Ljava/lang/Object;", true);
            methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap");
            methodVisitor.visitMethodInsn(INVOKESTATIC, "com/bumptech/glide/util/Util", "getBitmapByteSize", "(Landroid/graphics/Bitmap;)I", false);
            methodVisitor.visitInsn(IADD);
            methodVisitor.visitVarInsn(ISTORE, 1);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitJumpInsn(GOTO, label6);
            methodVisitor.visitLabel(label7);
            methodVisitor.visitInsn(NOP);
            Label label8 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label8);
            methodVisitor.visitLabel(label5);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitJumpInsn(GOTO, label8);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitVarInsn(ASTORE, 2);
            methodVisitor.visitLabel(label8);
            methodVisitor.visitVarInsn(ILOAD, 1);
            methodVisitor.visitInsn(IRETURN);
            methodVisitor.visitMaxs(2, 9);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_FINAL, "recycleImage", "()V", null, null);
            methodVisitor.visitCode();
            Label label0 = new Label();
            Label label1 = new Label();
            Label label2 = new Label();
            methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/Throwable");
            methodVisitor.visitLabel(label0);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getImageMapField", "()Lcom/svga/glide/util/ReflectUtils;", false);
            methodVisitor.visitInsn(DUP);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label3);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/util/ReflectUtils", "get", "()Ljava/lang/Object;", false);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/HashMap");
            Label label4 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label4);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitLabel(label4);
            methodVisitor.visitVarInsn(ASTORE, 1);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "TAG", "Ljava/lang/String;");
            methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
            methodVisitor.visitInsn(DUP);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
            methodVisitor.visitLdcInsn("recycle ");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getModel", "()Ljava/lang/String;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitLdcInsn(" size:");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(DUP);
            Label label5 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label5);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/util/HashMap", "size", "()I", false);
            methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            Label label6 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label6);
            methodVisitor.visitLabel(label5);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitLabel(label6);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true);
            methodVisitor.visitVarInsn(ALOAD, 1);
            methodVisitor.visitInsn(DUP);
            Label label7 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label7);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map");
            methodVisitor.visitVarInsn(ASTORE, 2);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 3);
            methodVisitor.visitVarInsn(ALOAD, 2);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map", "entrySet", "()Ljava/util/Set;", true);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Set", "iterator", "()Ljava/util/Iterator;", true);
            methodVisitor.visitVarInsn(ASTORE, 4);
            Label label8 = new Label();
            methodVisitor.visitLabel(label8);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "hasNext", "()Z", true);
            Label label9 = new Label();
            methodVisitor.visitJumpInsn(IFEQ, label9);
            methodVisitor.visitVarInsn(ALOAD, 4);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Iterator", "next", "()Ljava/lang/Object;", true);
            methodVisitor.visitTypeInsn(CHECKCAST, "java/util/Map$Entry");
            methodVisitor.visitVarInsn(ASTORE, 5);
            methodVisitor.visitVarInsn(ALOAD, 5);
            methodVisitor.visitVarInsn(ASTORE, 6);
            methodVisitor.visitInsn(ICONST_0);
            methodVisitor.visitVarInsn(ISTORE, 7);
            methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getBitmapPool", "()Lcom/bumptech/glide/load/engine/bitmap_recycle/BitmapPool;", false);
            methodVisitor.visitVarInsn(ALOAD, 6);
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/util/Map$Entry", "getValue", "()Ljava/lang/Object;", true);
            methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/Bitmap");
            methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/bumptech/glide/load/engine/bitmap_recycle/BitmapPool", "put", "(Landroid/graphics/Bitmap;)V", true);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitJumpInsn(GOTO, label8);
            methodVisitor.visitLabel(label9);
            methodVisitor.visitInsn(NOP);
            Label label10 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label10);
            methodVisitor.visitLabel(label7);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitInsn(NOP);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitJumpInsn(GOTO, label10);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitVarInsn(ASTORE, 1);
            methodVisitor.visitLabel(label10);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(5, 8);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "getSize", "()I", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAGlideResourceDelegate", "mapSize", "()I", false);
            methodVisitor.visitInsn(IRETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "recycle", "()V", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAGlideResourceDelegate", "recycleImage", "()V", false);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
            methodVisitor.visitInsn(DUP);
            Label label0 = new Label();
            methodVisitor.visitJumpInsn(IFNONNULL, label0);
            methodVisitor.visitInsn(POP);
            Label label1 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label1);
            methodVisitor.visitLabel(label0);
            methodVisitor.visitInsn(ACONST_NULL);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "setMovieItem", "(Lcom/opensource/svgaplayer/proto/MovieEntity;)V", false);
            methodVisitor.visitLabel(label1);
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAGlideResourceDelegate", "resource", "Lcom/svga/glide/SVGAResource;");
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
            methodVisitor.visitInsn(DUP);
            Label label2 = new Label();
            methodVisitor.visitJumpInsn(IFNULL, label2);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "clear", "()V", false);
            Label label3 = new Label();
            methodVisitor.visitJumpInsn(GOTO, label3);
            methodVisitor.visitLabel(label2);
            methodVisitor.visitInsn(POP);
            methodVisitor.visitLabel(label3);
            methodVisitor.visitInsn(RETURN);
            methodVisitor.visitMaxs(2, 1);
            methodVisitor.visitEnd();
        }
        {
            methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, "get", "()Ljava/lang/Object;", null, null);
            methodVisitor.visitCode();
            methodVisitor.visitVarInsn(ALOAD, 0);
            methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideResourceDelegate", "get", "()Lcom/svga/glide/SVGAResource;", false);
            methodVisitor.visitInsn(ARETURN);
            methodVisitor.visitMaxs(1, 1);
            methodVisitor.visitEnd();
        }
        classWriter.visitEnd();

        return classWriter.toByteArray();
    }
}

Process finished with exit code 0

public final void com.opensource.svgaplayer.SVGAVideoEntity.prepare$com_opensource_svgaplayer(kotlin.jvm.functions.Function0,com.opensource.svgaplayer.SVGAParser$PlayCallback)
