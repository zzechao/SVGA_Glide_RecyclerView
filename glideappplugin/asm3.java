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
public class SVGAImageViewDrawableTarget$onResourceReady$2Dump implements Opcodes {

public static byte[] dump () throws Exception {

ClassWriter classWriter = new ClassWriter(0);
FieldVisitor fieldVisitor;
RecordComponentVisitor recordComponentVisitor;
MethodVisitor methodVisitor;
AnnotationVisitor annotationVisitor0;

classWriter.visit(V1_8, ACC_FINAL | ACC_SUPER, "com/svga/glide/SVGAImageViewDrawableTarget$onResourceReady$2", "Lkotlin/jvm/internal/Lambda;Lkotlin/jvm/functions/Function0<Lkotlin/Unit;>;", "kotlin/jvm/internal/Lambda", new String[] { "kotlin/jvm/functions/Function0" });

classWriter.visitOuterClass("com/svga/glide/SVGAImageViewDrawableTarget", "onResourceReady", "(Lcom/svga/glide/SVGAResource;Lcom/bumptech/glide/request/transition/Transition;)V");

{
annotationVisitor0 = classWriter.visitAnnotation("Lkotlin/Metadata;", true);
annotationVisitor0.visit("mv", new int[] {1,7,1});
annotationVisitor0.visit("k", new Integer(3));
annotationVisitor0.visit("xi", new Integer(48));
{
AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d1");
annotationVisitor1.visit(null, "\u0000\u0008\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n\u00a2\u0006\u0002\u0008\u0002");
annotationVisitor1.visitEnd();
}
{
AnnotationVisitor annotationVisitor1 = annotationVisitor0.visitArray("d2");
annotationVisitor1.visit(null, "<anonymous>");
annotationVisitor1.visit(null, "");
annotationVisitor1.visit(null, "invoke");
annotationVisitor1.visitEnd();
}
annotationVisitor0.visitEnd();
}
classWriter.visitInnerClass("com/svga/glide/SVGAImageViewDrawableTarget$onResourceReady$2", null, null, ACC_FINAL | ACC_STATIC);

{
fieldVisitor = classWriter.visitField(ACC_FINAL | ACC_SYNTHETIC, "$drawable", "Lcom/svga/glide/SVGAAnimationDrawable;", null, null);
fieldVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(0, "<init>", "(Lcom/svga/glide/SVGAAnimationDrawable;)V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitVarInsn(ALOAD, 1);
methodVisitor.visitFieldInsn(PUTFIELD, "com/svga/glide/SVGAImageViewDrawableTarget$onResourceReady$2", "$drawable", "Lcom/svga/glide/SVGAAnimationDrawable;");
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitInsn(ICONST_0);
methodVisitor.visitMethodInsn(INVOKESPECIAL, "kotlin/jvm/internal/Lambda", "<init>", "(I)V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(2, 2);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_FINAL, "invoke", "()V", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitFieldInsn(GETFIELD, "com/svga/glide/SVGAImageViewDrawableTarget$onResourceReady$2", "$drawable", "Lcom/svga/glide/SVGAAnimationDrawable;");
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAAnimationDrawable", "start", "()V", false);
methodVisitor.visitInsn(RETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
{
methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_BRIDGE | ACC_SYNTHETIC, "invoke", "()Ljava/lang/Object;", null, null);
methodVisitor.visitCode();
methodVisitor.visitVarInsn(ALOAD, 0);
methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "com/svga/glide/SVGAImageViewDrawableTarget$onResourceReady$2", "invoke", "()V", false);
methodVisitor.visitFieldInsn(GETSTATIC, "kotlin/Unit", "INSTANCE", "Lkotlin/Unit;");
methodVisitor.visitInsn(ARETURN);
methodVisitor.visitMaxs(1, 1);
methodVisitor.visitEnd();
}
classWriter.visitEnd();

return classWriter.toByteArray();
}
}