### Glide加载模式（Glide版本是4.13.2以后，并添加compiler模块）

 采用glide对svga的加载进行优化  
 1、对内部的图片采用glide.bitmappool进行复用优化（inBitmap复用内存空间），防止内存抖动  
 2、图片的采样率优化（CustomViewTarget监听布局ViewTreeObserver.OnDrawListener后回调到解析器进行解析）  
 3、SVGAVideoEntity复用优化，对于相同svga资源公用同个解析对象，过度解析  
 4、普通imageview加载svga的构造（SVGAImageViewDrawableTarget，并对imageview的相同drawable进行复用，以及恢复暂停清除的生命周期控制）  
 5、采用okio重写解析器SVGAParser，减少io多次Array.copy的内存抖动  

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
 * detachedToStop是否移出屏幕就停止SVGA动画，默认是开启，如果repeatCount是ValueAnimator.INFINITE，就会重新返回屏幕后重新恢复SVGA动画
 */
class SVGAImageViewDrawableTarget(
    val imageView: ImageView, 
    var times: Int = 1,
    var repeatMode: Int = ValueAnimator.RESTART,
    var dynamicItem: SVGADynamicEntity = SVGADynamicEntity(),
    var svgaCallback: SVGACallback? = null,
    var detachedToStop: Boolean = true
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