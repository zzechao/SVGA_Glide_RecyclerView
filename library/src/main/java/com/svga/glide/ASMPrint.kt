package com.svga.glide

import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.ASMifier
import org.objectweb.asm.util.Textifier
import org.objectweb.asm.util.TraceClassVisitor
import java.io.PrintWriter


/**
 * @author:zhouz
 * @date: 2024/8/6 17:28
 * description：输出ASM转化
 */
fun main(args: Array<String>) {
    val clazzName = "com.svga.glide.SVGAGlideResourceDelegate"
    val parsingOption = ClassReader.SKIP_FRAMES or ClassReader.SKIP_DEBUG
    val asmCode = true

    val printer = if (asmCode) {
        ASMifier()
    } else {
        Textifier()
    }
    val printWriter = PrintWriter(System.out, true)
    val traceClassVisitor = TraceClassVisitor(null, printer, printWriter)
    ClassReader(clazzName).accept(traceClassVisitor, parsingOption)
}