### Glide加载模式（Glide版本是4.16.0以后，并添加compiler模块）

#### 采用glide对svga的加载进行优化  
  1、可以应用于recyclerview里，并通过普通imageview进行加载。</br>
  2、对内部的每一帧小图片图片采用glide.bitmappool进行优化（inBitmap复用内存空间），防止内存抖动。(io.github.zzechao.glide-svga插件提供)。</br>
  3、第一层：图片的采样率优化（CustomViewTarget监听布局ViewTreeObserver.OnDrawListener后回调到解析器进行解析）, 第二层：inTargetDensity和inDensity使用对于inBitmap内存申请优化。（io.github.zzechao.glide-svga插件提供）。</br>
  4、SVGAVideoEntity的Glide三级缓存复用优化，对于相同svga资源公用同个解析对象，防止多度下载过度解析。</br>
  5、普通imageview加载svga的构造（SVGAImageViewDrawableTarget，并对imageview的相同drawable进行复用，以及恢复暂停清除的生命周期控制）。</br>
  6、采用okio重写解析器SVGAParser，减少io多次Array.copy的内存抖动，并对SVGAVideoEntity进行glide.bitmappool回收处理。</br>
  7、glide 一般采取okhttp的连用，间接优化svga的下载连接池的复用，减少通道创建。</br>

### 用法 
#### rootDir#build.gradle
```groovy
    repositories {
        mavencenter() // glide、glidesvga
        maven { url 'https://jitpack.io' } // svga
    }

    dependencies {
        classpath "io.github.zzechao.gradle:glidesvgaplugin:1.0.1" // 导入插件和对应版本
    }
```
#### app#build.gradle
```groovy
    apply plugin: 'io.github.zzechao.glide-svga' // 应用插件
    apply plugin: 'kotlin-kapt'

    dependencies {
        // glide svga
        implementation("io.github.zzechao:libglidesvga:1.0.1")
        
        // svga
        implementation("com.github.yyued:SVGAPlayer-Android:2.6.1")
        
        // glide
        implementation("com.github.bumptech.glide:glide:4.16.0")
        kapt("com.github.bumptech.glide:compiler:4.16.0")
    }
```

### 思路架构图
![image](https://github.com/zzechao/svgaplayer-android-glide_feature/blob/master/process.png)

```kotlin
//自定义class继承AppGlideModule中添加SVGA的Glide解码模块，cachePath是缓存路径，是针对SVGA 1.0版本的文件缓存路径：
@GlideModule
class CustomGlideModule : AppGlideModule() {
    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        super.registerComponents(context, glide, registry)
        val cachePath = context.cacheDir.absolutePath + File.separatorChar + "glide-svga"
        SVGAGlideEx.register(glide, registry, cachePath)
    }
}
```

### 加载方式：
```kotlin
(Glide.with(actvity) as GlideRequests).asSVGAResource().load(url).into(SVGAImageViewDrawableTarget(ImageView))
```

### 另外提供setSVGATag方法设置svga的别名setSVGATag(String),方便日志跟踪是哪个svga加载及加载过程：
```kotlin
(Glide.with(actvity) as GlideRequests).asSVGAResource().load(url)
        .setSVGATag(String).into(SVGAImageViewDrawableTarget(ImageView))
```

### SVGAImageViewDrawableTarget提供6个参数：
```kotlin
/**
 * times 显示次数
 * repeatMode是显示模式
 * dynamicItem是SVGA的原有的填充模块，替换头像或者文案
 * svgaCallback是原有的SVGA的回调
 * showLastFrame 播放结束后，是否显示最后一帧， false 不显示， true 显示
 */
class SVGAImageViewDrawableTarget(
    val imageView: ImageView, 
    var times: Int = 1,
    var repeatMode: Int = ValueAnimator.RESTART,
    var dynamicItem: SVGADynamicEntity = SVGADynamicEntity(),
    var svgaCallback: SVGACallback? = null,
    var showLastFrame: Boolean = false
    )

/**
 * SVGAAnimationDrawable 的主动停止播放
 */
fun ImageView.stopSVGADrawable() {
  (this.drawable as? SVGAAnimationDrawable)?.stop()
}

/**
 * SVGAAnimationDrawable 的是否在播放中
 */
fun ImageView.isSVGARunning(): Boolean{
  return (this.drawable as? SVGAAnimationDrawable)?.isRunning ?: false
}
```    