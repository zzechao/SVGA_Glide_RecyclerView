package com.zhouz.plugin

import org.objectweb.asm.ClassVisitor


/**
 * @author:zhouz
 * @date: 2024/8/12 19:59
 * description：修改SVGAGlideResourceDelegate对resource.videoItem?.imageMap的回收
 */
class SVGAGlideResourceDelegateClassVisitor(api: Int, cv: ClassVisitor) : ClassVisitor(api, cv) {
}