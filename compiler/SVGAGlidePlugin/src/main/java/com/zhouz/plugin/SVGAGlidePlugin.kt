package com.zhouz.plugin

import com.android.build.api.artifact.ScopedArtifact
import com.android.build.api.variant.ApplicationAndroidComponentsExtension
import com.android.build.api.variant.ScopedArtifacts
import com.android.build.gradle.LibraryPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register


/**
 * @author:zhouz
 * @date: 2024/8/2 17:23
 * description：svga 针对 glide 的 lib 库改造
 */
class SVGAGlidePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        Logger.make(project)
        Logger.i("SVGAGlidePlugin apply")
        project.plugins.withType(LibraryPlugin::class.java) {
            val androidComponents =
                project.extensions.getByType(ApplicationAndroidComponentsExtension::class.java)
            androidComponents.onVariants { variant ->
                val task = project.tasks.register<SVGAGlideTransferTask>("${variant.name}SVGAGlideTransferTask")
                variant.artifacts.forScope(ScopedArtifacts.Scope.ALL)
                    .use(task)
                    .toTransform(
                        ScopedArtifact.CLASSES,
                        SVGAGlideTransferTask::allJars,
                        SVGAGlideTransferTask::allDirectories,
                        SVGAGlideTransferTask::output
                    )
            }
        }
    }
}