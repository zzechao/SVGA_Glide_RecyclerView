package com.zhouz.plugin

import org.objectweb.asm.ClassVisitor

/**
 * @author:zhouz
 * @date: 2024/8/8 18:33
 * description：修改SVGAImageViewDrawableTarget的SVGAVideoEntity的 prepare方法调用
 */
class SVGAImageViewDrawableTargetClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
}