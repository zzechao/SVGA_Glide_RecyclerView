package com.svga.glide.log

import com.opensource.svgaplayer.utils.log.LogUtils

/**
 * @author:zhouzechao
 * @date: 2/24/21
 * description：
 */
open class DefaultLog : ILog {
    override fun logcatVisible(visible: Boolean) {
    }

    override fun v(tag: String, message: () -> Any?) {
    }

    override fun v(tag: String, format: String, vararg args: Any?) {
    }

    override fun v(tag: String, message: String) {
    }

    override fun d(tag: String, message: () -> Any?) {
        message?.toString()?.let { LogUtils.debug(tag, it) }
    }

    override fun d(tag: String, format: String, vararg args: Any?) {
        LogUtils.debug(tag, format.format(args))
    }

    override fun d(tag: String, message: String) {
        LogUtils.debug(tag, message)
    }

    override fun i(tag: String, message: () -> Any?) {
        message?.toString()?.let { LogUtils.info(tag, it) }
    }

    override fun i(tag: String, format: String, vararg args: Any?) {
        LogUtils.info(tag, format.format(args))
    }

    override fun i(tag: String, message: String) {
        LogUtils.info(tag, message)
    }

    override fun w(tag: String, message: () -> Any?) {
    }

    override fun w(tag: String, format: String, vararg args: Any?) {
    }

    override fun w(tag: String, message: String) {
    }

    override fun e(tag: String, message: () -> Any?, error: Throwable?) {
    }

    override fun e(tag: String, format: String, error: Throwable?, vararg args: Any?) {
    }

    override fun e(tag: String, message: String, error: Throwable?) {
        error?.let {
            LogUtils.error(tag, message, error)
        } ?: LogUtils.error(tag, message)
    }
}