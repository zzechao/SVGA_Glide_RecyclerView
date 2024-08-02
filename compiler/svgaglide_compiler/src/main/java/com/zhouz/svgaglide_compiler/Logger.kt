package com.zhouz.svgaglide_compiler

import org.gradle.api.Project


/**
 * @author:zhouz
 * @date: 2024/6/26 17:05
 * description：日志输出
 */
object Logger {
    private lateinit var logger: org.gradle.api.logging.Logger

    fun make(project: Project) {
        logger = project.logger
    }

    fun i(info: String) {
        println("SVGA_Plugin::info >>> $info")
    }

    fun e(error: String) {
        logger.error("SVGA_Plugin::error >>> $error")
    }

    fun w(warning: String) {
        logger.warn("SVGA_Plugin::warn >>> $warning")
    }
}