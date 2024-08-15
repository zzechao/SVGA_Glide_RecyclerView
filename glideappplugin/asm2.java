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
public class SVGAImageViewDrawableTargetDump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
RecordComponentVisitor recordComponentVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/svga/glide/SVGAImageViewDrawableTarget", "Lcom/bumptech/glide/request/target/CustomViewTarget<Landroid/widget/ImageView;Lcom/svga/glide/SVGAResource;>;", "com/bumptech/glide/request/target/CustomViewTarget", null);

{
annotationVisitor0 = classWriter.visitAnnotation("Lkotlin/Metadata;", true);
annotationVisitor0.visit("mv", new int[] {1,7,1});
annotationVisitor0.visit("k", new Integer(1));
annotationVisitor0.visit("xi", new Integer(48));
{
AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d1");
annotationVisitor1.visit(null, "\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0008\u0002\n\u0002\u0010\u0008\n\u0002\u0008\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0008\u0002\n\u0002\u0010\u000e\n\u0002\u0008\u0011\n\u0002\u0010\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0002\u0008\u0005\n\u0002\u0018\u0002\n\u0002\u0008\u0004\n\u0002\u0018\u0002\n\u0000\u0008\u0016\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001BA\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0008\u0008\u0002\u0010\u0005\u001a\u00020\u0006\u0012\u0008\u0008\u0002\u0010\u0007\u001a\u00020\u0006\u0012\u0008\u0008\u0002\u0010\u0008\u001a\u00020\u0009\u0012\n\u0008\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0008\u0008\u0002\u0010\u000c\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0010H\u0002J\u0008\u0010$\u001a\u00020\"H\u0016J\u0012\u0010%\u001a\u00020\"2\u0008\u0010&\u001a\u0004\u0018\u00010'H\u0016J\u0012\u0010(\u001a\u00020\"2\u0008\u0010)\u001a\u0004\u0018\u00010'H\u0014J\"\u0010*\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u00032\u0010\u0010,\u001a\u000c\u0012\u0006\u0008\u0000\u0012\u00020\u0003\u0018\u00010-H\u0016J\u0008\u0010.\u001a\u00020\"H\u0016J\u0008\u0010/\u001a\u00020\"H\u0016J\u0018\u00100\u001a\u00020\"2\u0006\u0010+\u001a\u00020\u00032\u0006\u00101\u001a\u000202H\u0002R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0008\u001a\u00020\u0009\u00a2\u0006\u0008\n\u0000\u001a\u0004\u0008\u0011\u0010\u0012R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0013\u0010\u0014\"\u0004\u0008\u0015\u0010\u0016R\u001a\u0010\u000c\u001a\u00020\rX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u0017\u0010\u0018\"\u0004\u0008\u0019\u0010\u001aR\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001b\u0010\u001c\"\u0004\u0008\u001d\u0010\u001eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\u0008\u001f\u0010\u0014\"\u0004\u0008 \u0010\u0016\u00a8\u00063");
annotationVisitor1.visitEnd();
}
{
AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d2");
annotationVisitor1.visit(null, "Lcom/svga/glide/SVGAImageViewDrawableTarget;");
annotationVisitor1.visit(null, "Lcom/bumptech/glide/request/target/CustomViewTarget;");
annotationVisitor1.visit(null, "Landroid/widget/ImageView;");
annotationVisitor1.visit(null, "Lcom/svga/glide/SVGAResource;");
annotationVisitor1.visit(null, "imageView");
annotationVisitor1.visit(null, "times");
annotationVisitor1.visit(null, "");
annotationVisitor1.visit(null, "repeatMode");
annotationVisitor1.visit(null, "dynamicItem");
annotationVisitor1.visit(null, "Lcom/opensource/svgaplayer/SVGADynamicEntity;");
annotationVisitor1.visit(null, "svgaCallback");
annotationVisitor1.visit(null, "Lcom/opensource/svgaplayer/SVGACallback2;");
annotationVisitor1.visit(null, "showLastFrame");
annotationVisitor1.visit(null, "");
annotationVisitor1.visit(null, "(Landroid/widget/ImageView;IILcom/opensource/svgaplayer/SVGADynamicEntity;Lcom/opensource/svgaplayer/SVGACallback2;Z)V");
annotationVisitor1.visit(null, "TAG");
annotationVisitor1.visit(null, "");
annotationVisitor1.visit(null, "getDynamicItem");
annotationVisitor1.visit(null, "()Lcom/opensource/svgaplayer/SVGADynamicEntity;");
annotationVisitor1.visit(null, "getRepeatMode");
annotationVisitor1.visit(null, "()I");
annotationVisitor1.visit(null, "setRepeatMode");
annotationVisitor1.visit(null, "(I)V");
annotationVisitor1.visit(null, "getShowLastFrame");
annotationVisitor1.visit(null, "()Z");
annotationVisitor1.visit(null, "setShowLastFrame");
annotationVisitor1.visit(null, "(Z)V");
annotationVisitor1.visit(null, "getSvgaCallback");
annotationVisitor1.visit(null, "()Lcom/opensource/svgaplayer/SVGACallback2;");
annotationVisitor1.visit(null, "setSvgaCallback");
annotationVisitor1.visit(null, "(Lcom/opensource/svgaplayer/SVGACallback2;)V");
annotationVisitor1.visit(null, "getTimes");
annotationVisitor1.visit(null, "setTimes");
annotationVisitor1.visit(null, "clearDrawable");
annotationVisitor1.visit(null, "");
annotationVisitor1.visit(null, "reason");
annotationVisitor1.visit(null, "onDestroy");
annotationVisitor1.visit(null, "onLoadFailed");
annotationVisitor1.visit(null, "errorDrawable");
annotationVisitor1.visit(null, "Landroid/graphics/drawable/Drawable;");
annotationVisitor1.visit(null, "onResourceCleared");
annotationVisitor1.visit(null, "placeholder");
annotationVisitor1.visit(null, "onResourceReady");
annotationVisitor1.visit(null, "resource");
annotationVisitor1.visit(null, "transition");
annotationVisitor1.visit(null, "Lcom/bumptech/glide/request/transition/Transition;");
annotationVisitor1.visit(null, "onStart");
annotationVisitor1.visit(null, "onStop");
annotationVisitor1.visit(null, "prepare");
annotationVisitor1.visit(null, "drawable");
annotationVisitor1.visit(null, "Lcom/svga/glide/SVGAAnimationDrawable;");
annotationVisitor1.visit(null, "com.opensource.svgaplayer");
annotationVisitor1.visitEnd();
}
annotationVisitor0.visitEnd();
}
classWriter.visitInnerClass("com/svga/glide/SVGAImageViewDrawableTarget$prepare$1", null, null, ACC_FINAL | ACC_STATIC);

{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "times", "I", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "repeatMode", "I", null, null);
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE | ACC_FINAL, "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;", null, null);
{
annotationVisitor0 = fieldVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
annotationVisitor0.visitEnd();
}
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;", null, null);
{
annotationVisitor0 = fieldVisitor.visitAnnotation("Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
fieldVisitor.visitEnd();
}
{
fieldVisitor = classWriter.visitField(ACC_PRIVATE, "showLastFrame", "Z", null, null);
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
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "(Landroid/widget/ImageView;IILcom/opensource/svgaplayer/SVGADynamicEntity;Lcom/opensource/svgaplayer/SVGACallback2;Z)V", null, null);
methodVisitor.visitAnnotableParameterCount(6, false);
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/NotNull;", false);
annotationVisitor0.visitEnd();
}
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(3, "Lorg/jetbrains/annotations/NotNull;", false);
annotationVisitor0.visitEnd();
}
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(4, "Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn("imageView");
methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitLdcInsn("dynamicItem");
methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(CHECKCAST, "android/view/View");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/bumptech/glide/request/target/CustomViewTarget", "<init>", "(Landroid/view/View;)V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 2);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "times", "I");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 3);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "repeatMode", "I");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 5);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 6);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "showLastFrame", "Z");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitLdcInsn("SVGAImageViewDrawableTarget");
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;");
methodVisitor.visitInsn(NOP);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAImageViewDrawableTarget", "getRequest", "()Lcom/bumptech/glide/request/Request;", false);
methodVisitor.visitInsn(DUP);
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label0);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/bumptech/glide/request/Request", "clear", "()V", true);
Label label1 = new Label();
methodVisitor.visitJumpInsn(GOTO, label1);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitLabel(label1);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAImageViewDrawableTarget", "setRequest", "(Lcom/bumptech/glide/request/Request;)V", false);
methodVisitor.visitInsn(NOP);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 7);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_SYNTHETIC, "<init>", "(Landroid/widget/ImageView;IILcom/opensource/svgaplayer/SVGADynamicEntity;Lcom/opensource/svgaplayer/SVGACallback2;ZILkotlin/jvm/internal/DefaultConstructorMarker;)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ILOAD, 7);
methodVisitor.visitInsn(ICONST_2);
methodVisitor.visitInsn(IAND);
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label0);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 2);
methodVisitor.visitLabel(label0);
methodVisitor.visitVarInsn(ILOAD, 7);
methodVisitor.visitInsn(ICONST_4);
methodVisitor.visitInsn(IAND);
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label1);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitVarInsn(ISTORE, 3);
methodVisitor.visitLabel(label1);
methodVisitor.visitVarInsn(ILOAD, 7);
methodVisitor.visitIntInsn(BIPUSH, 8);
methodVisitor.visitInsn(IAND);
Label label2 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label2);
methodVisitor.visitTypeInsn(NEW, "com/opensource/svgaplayer/SVGADynamicEntity");
methodVisitor.visitInsn(DUP);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/opensource/svgaplayer/SVGADynamicEntity", "<init>", "()V", false);
methodVisitor.visitVarInsn(ASTORE, 4);
methodVisitor.visitLabel(label2);
methodVisitor.visitVarInsn(ILOAD, 7);
methodVisitor.visitIntInsn(BIPUSH, 16);
methodVisitor.visitInsn(IAND);
Label label3 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label3);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitVarInsn(ASTORE, 5);
methodVisitor.visitLabel(label3);
methodVisitor.visitVarInsn(ILOAD, 7);
methodVisitor.visitIntInsn(BIPUSH, 32);
methodVisitor.visitInsn(IAND);
Label label4 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label4);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 6);
methodVisitor.visitLabel(label4);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitVarInsn(ILOAD, 2);
methodVisitor.visitVarInsn(ILOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 4);
methodVisitor.visitVarInsn(ALOAD, 5);
methodVisitor.visitVarInsn(ILOAD, 6);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget", "<init>", "(Landroid/widget/ImageView;IILcom/opensource/svgaplayer/SVGADynamicEntity;Lcom/opensource/svgaplayer/SVGACallback2;Z)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(7, 9);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "getTimes", "()I", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "times", "I");
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "setTimes", "(I)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "times", "I");
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "getRepeatMode", "()I", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "repeatMode", "I");
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "setRepeatMode", "(I)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "repeatMode", "I");
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "getDynamicItem", "()Lcom/opensource/svgaplayer/SVGADynamicEntity;", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/NotNull;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;");
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "getSvgaCallback", "()Lcom/opensource/svgaplayer/SVGACallback2;", null, null);
{
annotationVisitor0 = methodVisitor.visitAnnotation("Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "setSvgaCallback", "(Lcom/opensource/svgaplayer/SVGACallback2;)V", null, null);
methodVisitor.visitAnnotableParameterCount(1, false);
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "getShowLastFrame", "()Z", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "showLastFrame", "Z");
methodVisitor.visitInsn(IRETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "setShowLastFrame", "(Z)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ILOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "showLastFrame", "Z");
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onLoadFailed", "(Landroid/graphics/drawable/Drawable;)V", null, null);
methodVisitor.visitAnnotableParameterCount(1, false);
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitLdcInsn("onLoadFailed");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget", "clearDrawable", "(Ljava/lang/String;)V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitInsn(DUP);
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label0);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/opensource/svgaplayer/SVGACallback2", "onFailure", "()V", true);
Label label1 = new Label();
methodVisitor.visitJumpInsn(GOTO, label1);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onResourceReady", "(Lcom/svga/glide/SVGAResource;Lcom/bumptech/glide/request/transition/Transition;)V", "(Lcom/svga/glide/SVGAResource;Lcom/bumptech/glide/request/transition/Transition<-Lcom/svga/glide/SVGAResource;>;)V", null);
methodVisitor.visitAnnotableParameterCount(2, false);
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/NotNull;", false);
annotationVisitor0.visitEnd();
}
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(1, "Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitLdcInsn("resource");
methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullParameter", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
methodVisitor.visitInsn(DUP);
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFNONNULL, label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 4);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitInsn(DUP);
Label label1 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label1);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/opensource/svgaplayer/SVGACallback2", "onFailure", "()V", true);
Label label2 = new Label();
methodVisitor.visitJumpInsn(GOTO, label2);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(POP);
methodVisitor.visitLabel(label2);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitTypeInsn(NEW, "com/svga/glide/SVGAAnimationDrawable");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "times", "I");
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(ISUB);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "repeatMode", "I");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "dynamicItem", "Lcom/opensource/svgaplayer/SVGADynamicEntity;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "showLastFrame", "Z");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAAnimationDrawable", "<init>", "(Lcom/opensource/svgaplayer/SVGAVideoEntity;IILcom/opensource/svgaplayer/SVGADynamicEntity;Z)V", false);
methodVisitor.visitVarInsn(ASTORE, 3);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getModel", "()Ljava/lang/String;", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "setTag", "(Ljava/lang/String;)V", false);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "svgaCallback", "Lcom/opensource/svgaplayer/SVGACallback2;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "setSvgaCallback", "(Lcom/opensource/svgaplayer/SVGACallback2;)V", false);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getScaleType", "()Landroid/widget/ImageView$ScaleType;", false);
methodVisitor.visitInsn(DUP);
methodVisitor.visitLdcInsn("view.scaleType");
methodVisitor.visitMethodInsn(INVOKESTATIC, "kotlin/jvm/internal/Intrinsics", "checkNotNullExpressionValue", "(Ljava/lang/Object;Ljava/lang/String;)V", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "setScaleType", "(Landroid/widget/ImageView$ScaleType;)V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitTypeInsn(CHECKCAST, "android/graphics/drawable/Drawable");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageDrawable", "(Landroid/graphics/drawable/Drawable;)V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget", "prepare", "(Lcom/svga/glide/SVGAResource;Lcom/svga/glide/SVGAAnimationDrawable;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(7, 5);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_FINAL, "prepare", "(Lcom/svga/glide/SVGAResource;Lcom/svga/glide/SVGAAnimationDrawable;)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;");
methodVisitor.visitLdcInsn("prepare for plugin");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
methodVisitor.visitInsn(DUP);
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFNONNULL, label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(POP);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAResource", "getVideoItem", "()Lcom/opensource/svgaplayer/SVGAVideoEntity;", false);
methodVisitor.visitTypeInsn(NEW, "com/svga/glide/SVGAImageViewDrawableTarget$prepare$1");
methodVisitor.visitInsn(DUP);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget$prepare$1", "<init>", "(Lcom/svga/glide/SVGAAnimationDrawable;)V", false);
methodVisitor.visitTypeInsn(CHECKCAST, "kotlin/jvm/functions/Function0");
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/opensource/svgaplayer/SVGAVideoEntity", "prepare$com_opensource_svgaplayer", "(Lkotlin/jvm/functions/Function0;Lcom/opensource/svgaplayer/SVGAParser$PlayCallback;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(4, 3);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PROTECTED, "onResourceCleared", "(Landroid/graphics/drawable/Drawable;)V", null, null);
methodVisitor.visitAnnotableParameterCount(1, false);
{
annotationVisitor0 = methodVisitor.visitParameterAnnotation(0, "Lorg/jetbrains/annotations/Nullable;", false);
annotationVisitor0.visitEnd();
}
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitLdcInsn("");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget", "clearDrawable", "(Ljava/lang/String;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PRIVATE | ACC_FINAL, "clearDrawable", "(Ljava/lang/String;)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getDrawable", "()Landroid/graphics/drawable/Drawable;", false);
methodVisitor.visitVarInsn(ASTORE, 2);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitTypeInsn(INSTANCEOF, "com/svga/glide/SVGAAnimationDrawable");
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label0);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitTypeInsn(CHECKCAST, "com/svga/glide/SVGAAnimationDrawable");
Label label1 = new Label();
methodVisitor.visitJumpInsn(GOTO, label1);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(DUP);
Label label2 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label2);
methodVisitor.visitVarInsn(ASTORE, 3);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitVarInsn(ASTORE, 4);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 5);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(CHECKCAST, "java/lang/CharSequence");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "java/lang/CharSequence", "length", "()I", true);
Label label3 = new Label();
methodVisitor.visitJumpInsn(IFLE, label3);
methodVisitor.visitInsn(ICONST_1);
Label label4 = new Label();
methodVisitor.visitJumpInsn(GOTO, label4);
methodVisitor.visitLabel(label3);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitLabel(label4);
Label label5 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label5);
methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;");
methodVisitor.visitTypeInsn(NEW, "java/lang/StringBuilder");
methodVisitor.visitInsn(DUP);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
methodVisitor.visitLdcInsn("clearDrawable ");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true);
methodVisitor.visitLabel(label5);
methodVisitor.visitInsn(NOP);
methodVisitor.visitVarInsn(ALOAD, 3);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "stop", "()V", false);
Label label6 = new Label();
methodVisitor.visitJumpInsn(GOTO, label6);
methodVisitor.visitLabel(label2);
methodVisitor.visitInsn(POP);
methodVisitor.visitInsn(NOP);
methodVisitor.visitLabel(label6);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "setImageDrawable", "(Landroid/graphics/drawable/Drawable;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(4, 6);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onStart", "()V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getDrawable", "()Landroid/graphics/drawable/Drawable;", false);
methodVisitor.visitVarInsn(ASTORE, 1);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(INSTANCEOF, "com/svga/glide/SVGAAnimationDrawable");
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(CHECKCAST, "com/svga/glide/SVGAAnimationDrawable");
Label label1 = new Label();
methodVisitor.visitJumpInsn(GOTO, label1);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(DUP);
Label label2 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label2);
methodVisitor.visitVarInsn(ASTORE, 2);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitVarInsn(ASTORE, 3);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 4);
methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;");
methodVisitor.visitLdcInsn("onStart");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/svga/glide/SVGAAnimationDrawable", "resume$default", "(Lcom/svga/glide/SVGAAnimationDrawable;ZILjava/lang/Object;)V", false);
Label label3 = new Label();
methodVisitor.visitJumpInsn(GOTO, label3);
methodVisitor.visitLabel(label2);
methodVisitor.visitInsn(POP);
methodVisitor.visitLabel(label3);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(4, 5);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onStop", "()V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/bumptech/glide/request/target/CustomViewTarget", "view", "Landroid/view/View;");
methodVisitor.visitTypeInsn(CHECKCAST, "android/widget/ImageView");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "android/widget/ImageView", "getDrawable", "()Landroid/graphics/drawable/Drawable;", false);
methodVisitor.visitVarInsn(ASTORE, 1);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(INSTANCEOF, "com/svga/glide/SVGAAnimationDrawable");
Label label0 = new Label();
methodVisitor.visitJumpInsn(IFEQ, label0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(CHECKCAST, "com/svga/glide/SVGAAnimationDrawable");
Label label1 = new Label();
methodVisitor.visitJumpInsn(GOTO, label1);
methodVisitor.visitLabel(label0);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitLabel(label1);
methodVisitor.visitInsn(DUP);
Label label2 = new Label();
methodVisitor.visitJumpInsn(IFNULL, label2);
methodVisitor.visitVarInsn(ASTORE, 2);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitVarInsn(ASTORE, 3);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitVarInsn(ISTORE, 4);
methodVisitor.visitFieldInsn(GETSTATIC, "com/svga/glide/SVGAGlideEx", "INSTANCE", "Lcom/svga/glide/SVGAGlideEx;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAGlideEx", "getLog", "()Lcom/svga/glide/log/ILog;", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget", "TAG", "Ljava/lang/String;");
methodVisitor.visitLdcInsn("onStop");
methodVisitor.visitMethodInsn(INVOKEINTERFACE, "com/svga/glide/log/ILog", "d", "(Ljava/lang/String;Ljava/lang/String;)V", true);
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitInsn(ICONST_1);
methodVisitor.visitInsn(ACONST_NULL);
methodVisitor.visitMethodInsn(INVOKESTATIC, "com/svga/glide/SVGAAnimationDrawable", "pause$default", "(Lcom/svga/glide/SVGAAnimationDrawable;ZILjava/lang/Object;)V", false);
Label label3 = new Label();
methodVisitor.visitJumpInsn(GOTO, label3);
methodVisitor.visitLabel(label2);
methodVisitor.visitInsn(POP);
methodVisitor.visitLabel(label3);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(4, 5);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "onDestroy", "()V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/bumptech/glide/request/target/CustomViewTarget", "onDestroy", "()V", false);
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitLdcInsn("onDestroy");
methodVisitor.visitMethodInsn(INVOKESPECIAL, "com/svga/glide/SVGAImageViewDrawableTarget", "clearDrawable", "(Ljava/lang/String;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, "onResourceReady", "(Ljava/lang/Object;Lcom/bumptech/glide/request/transition/Transition;)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitTypeInsn(CHECKCAST, "com/svga/glide/SVGAResource");
methodVisitor.visitVarInsn(ALOAD, 2);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAImageViewDrawableTarget", "onResourceReady", "(Lcom/svga/glide/SVGAResource;Lcom/bumptech/glide/request/transition/Transition;)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(3, 3);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}

Process finished with exit code 0
