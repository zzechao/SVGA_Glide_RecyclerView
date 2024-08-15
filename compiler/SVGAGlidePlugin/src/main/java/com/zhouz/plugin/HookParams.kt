package com.zhouz.plugin


/**
 * @author:zhouz
 * @date: 2024/8/2 18:44
 * description：hook的class信息
 */
object HookParams {

    const val ENTITY_SVGA_NAME = "com/opensource/svgaplayer/SVGAVideoEntity"

    const val ENTITY_SVGA_CLASS = "$ENTITY_SVGA_NAME.class"

    const val ENTITY_SVGA_CLASS_METHOD = "prepare\$com_opensource_svgaplayer"

    const val ENTITY_SVGA_CLASS_METHOD2 = "getImageMap\$com_opensource_svgaplayer"

    const val ENTITY_SVGA_CLASS_METHOD_NEW = ENTITY_SVGA_CLASS_METHOD

    const val ENTITY_SVGA_TARGET_METHOD = "prepare"

    const val ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME = "com/svga/glide/SVGAImageViewDrawableTarget\$onResourceReady\$2"

    const val ENTITY_SVGA_TARGET_CLASS_PREPARE_2_CLASS = "$ENTITY_SVGA_TARGET_CLASS_PREPARE_2_NAME.class"

    const val ENTITY_SVGA_TARGET_NAME = "com/svga/glide/SVGAImageViewDrawableTarget"

    const val ENTITY_SVGA_TARGET_CLASS = "$ENTITY_SVGA_TARGET_NAME.class"

    const val ENTITY_SVGA_ANIMATION_DRAWABLE_NAME = "com/svga/glide/SVGAAnimationDrawable"

    const val ENTITY_SVGA_RESOURCE_NAME = "com/svga/glide/SVGAResource"

    const val SVGA_GLIDE_PARSER_CLASS = "com/svga/glide/GlideSVGAParser.class"

    const val SVGA_GLIDE_RESOURCE_DELEGATE_CLASS = "com/svga/glide/SVGAGlideResourceDelegate.class"

    const val SVGA_GLIDE_DRAWABLE_CLASS = "com/svga/glide/SVGAAnimationDrawable.class"
}