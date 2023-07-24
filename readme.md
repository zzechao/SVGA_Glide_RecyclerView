# SVGAPlayer

## 介绍

`SVGAPlayer` 是一个轻量的动画渲染库。你可以使用[工具](http://svga.io/designer.html)从 `Adobe Animate CC` 或者 `Adobe After Effects` 中导出动画文件，然后使用 `SVGAPlayer` 在移动设备上渲染并播放。

`SVGAPlayer-Android` 使用原生 Android Canvas 库渲染动画，为你提供高性能、低开销的动画体验。

如果你想要了解更多细节，请访问[官方网站](http://svga.io/)。

## 用法

我们在这里介绍 `SVGAPlayer-Android` 的用法。想要知道如何导出动画，点击[这里](http://svga.io/designer.html)。

### 使用 Gradle 安装

在应用 `build.gradle` 中添加依赖。

```
compile 'com.duowan.mobile:SVGAPlayer-Android:latest'
```

### Parser 单例支持
SVGAParser 单例需要在使用之前初始化，
否则会上报错误信息：
`Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")`


### 遮罩支持
请参阅此处 [Dynamic · Matte Layer](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-%C2%B7-Matte-Layer)

### 混淆规则

```
-keep class com.squareup.wire.** { *; }
-keep class com.opensource.svgaplayer.proto.** { *; }
```

### 放置 svga 文件

SVGAPlayer 可以从本地 `assets` 目录，或者远端服务器上加载动画文件。

### 使用 XML

你可以使用 `layout.xml` 添加一个 `SVGAImageView`。

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <com.opensource.svgaplayer.SVGAImageView
        android:layout_height="match_parent"
        android:layout_width="match_parent"
        app:source="posche.svga"
        app:autoPlay="true"
        android:background="#000" />

</RelativeLayout>
```

在 XML 中，允许定义以下这些标签：

#### source: String
用于表示 svga 文件的路径，提供一个在 `assets` 目录下的文件名，或者提供一个 http url 地址。

#### autoPlay: Boolean
默认为 `true`，当动画加载完成后，自动播放。

#### loopCount: Int
默认为 `0`，设置动画的循环次数，0 表示无限循环。

#### ~~clearsAfterStop: Boolean~~
默认为 `false`，当动画播放完成后，是否清空画布，以及 SVGAVideoEntity 内部数据。
不再推荐使用，开发者可以通过 clearAfterDetached 控制资源释放，或者手动通过 SVGAVideoEntity#clear 控制资源释放

#### clearsAfterDetached: Boolean
默认为 `false`，当 SVGAImageView 触发 onDetachedFromWindow 方法时，是否清空画布。

#### fillMode: String

默认为 `Forward`，可以是 `Forward`、 `Backward`、 `Clear`。

`Forward` 表示动画结束后，将停留在最后一帧。

`Backward` 表示动画结束后，将停留在第一帧。

`Clear` 表示动画播放完后，清空所有画布内容，但仅仅是画布，不涉及 SVGAVideoEntity 内部数据。

### 使用代码

也可以使用代码添加 `SVGAImageView`。

#### 创建一个 `SVGAImageView` 实例

```kotlin
SVGAImageView imageView = new SVGAImageView(this);
```

#### 声明一个 `SVGAParser` 单例.

```kotlin
parser = SVGAParser.shareParser()
```

#### 初始化 `SVGAParser` 单例

必须在使用 `SVGAParser` 单例前初始化，
```
SVGAParser.shareParser().init(this);
```

否则会上报错误信息：
`Log.e("SVGAParser", "在配置 SVGAParser context 前, 无法解析 SVGA 文件。")`

你也可以自行创建 `SVGAParser` 实例。

#### 创建一个 `SVGAParser` 实例，加载 assets 中的动画。

```kotlin
parser = new SVGAParser(this);
// 第三个为可缺省参数，默认为 null，如果设置该方法，则内部不在处理音频的解析以及播放，会通过 PlayCallback 把音频 File 实例回传给开发者，有开发者自行控制音频的播放与停止。
parser.decodeFromAssets("posche.svga", object : SVGAParser.ParseCompletion {
    // ...
}, object : SVGAParser.PlayCallback {
    // The default is null, can not be set
})
```

#### 创建一个 `SVGAParser` 实例，加载远端服务器中的动画。

```kotlin
parser = new SVGAParser(this);
// 第三个为可缺省参数，默认为 null，如果设置该方法，则内部不在处理音频的解析以及播放，会通过 PlayCallback 把音频 File 实例回传给开发者，有开发者自行控制音频的播放与停止。
parser.decodeFromURL(new URL("https://github.com/yyued/SVGA-Samples/blob/master/posche.svga?raw=true"), new SVGAParser.ParseCompletion() {
    // ...
}, object : SVGAParser.PlayCallback {
    // The default is null, can not be set
})
```

#### 创建一个 `SVGADrawable` 实例，并赋值给 `SVGAImageView`，然后播放动画。

```kotlin
parser = new SVGAParser(this);
parser.decodeFromURL(..., new SVGAParser.ParseCompletion() {
    @Override
    public void onComplete(@NotNull SVGAVideoEntity videoItem) {
        SVGADrawable drawable = new SVGADrawable(videoItem);
        imageView.setImageDrawable(drawable);
        imageView.startAnimation();
    }
    @Override
    public void onError() {

    }
});
```

### 缓存

`SVGAParser` 不会管理缓存，你需要自行实现缓存器。

#### 设置 HttpResponseCache

`SVGAParser` 依赖 `URLConnection`, `URLConnection` 使用 `HttpResponseCache` 处理缓存。

添加代码至 `Application.java:onCreate` 以设置缓存。

```kotlin
val cacheDir = File(context.applicationContext.cacheDir, "http")
HttpResponseCache.install(cacheDir, 1024 * 1024 * 128)
```

### SVGALogger
更新了内部 log 输出，可通过 SVGALogger 去管理和控制，默认是未启用 log 输出，开发者们也可以实现 ILogger 接口，做到外部捕获收集 log，方便排查问题。
通过 `setLogEnabled` 方法设置日志是否开启。
通过 `injectSVGALoggerImp` 方法注入自定义 ILogger 实现类。

```kotlin

// 默认情况下，SVGA 内部不会输出任何 log，所以需要手动设置为 true
SVGALogger.setLogEnabled(true)

// 如果希望收集 SVGA 内部输出的日志，则可通过下面方式获取
SVGALogger.injectSVGALoggerImp(object: ILogger {
// 实现相关接口进行接收 log
})
```

### SVGASoundManager
新增 SVGASoundManager 控制 SVGA 音频，需要手动调用 init 方法进行初始化，否则按照默认的音频加载逻辑。
另外通过 SVGASoundManager#setVolume 可控制 SVGA 播放时的音量大小，范围值在 [0f, 1f]，默认控制所有 SVGA 播放时的音量，
而且该方法可设置第二个可缺省参数：SVGAVideoEntity，表示仅控制当前 SVGA 的音量大小，其他 SVGA 的音量保持不变。

```kotlin
// 初始化音频管理器，方便管理音频播放
// 如果没有初始化，则默认按照原有方式加载音频
SVGASoundManager.init()

// 释放音频资源
SVGASoundManager.release()

/**
* 设置音量大小，entity 默认为空
* 当 entity 为空，则控制所有通过 SVGASoundManager 加载的音频音量大小，即包括当前正在播放的音频以及后续加载的音频
* 当 entity 不为空，则仅控制该实例的 SVGA 音频音量大小，其他则不受影响
* 
* @param volume 取值范围为 [0f, 1f]
* @param entity 即 SVGAParser 回调回来的实例
*/
SVGASoundManager.setVolume(volume, entity)
```

### Glide加载模式（2.8.0.0版本以后支持，Glide版本是4.13.2以后，并添加compiler模块）
```kotlin
//自定义class继承AppGlideModule中添加SVGA的Glide解码模块，cachePath是缓存路径，是针对SVGA 1.0版本的文件缓存路径：
val cachePath = context.cacheDir.absolutePath + File.separatorChar + "glide-svga"
registry.prepend(
            Registry.BUCKET_ANIMATION,
            InputStream::class.java, SVGAResource::class.java,
            SVGAResourceStreamDecoder(cachePath, glide)
        )
```

加载方式：
```kotlin
(Glide.with(actvity) as GlideRequests).asSVGA().load(url).into(SVGAImageViewDrawableTarget(ImageView))
```

另外提供setSVGATag方法设置svga的别名setSVGATag(String),方便日志跟踪是哪个svga加载及加载过程：
```kotlin
(Glide.with(actvity) as GlideRequests).asSVGA().load(url)
        .setSVGATag(String).into(SVGAImageViewDrawableTarget(ImageView))
```

SVGAImageViewDrawableTarget提供6个参数：
```kotlin
/**
 * repeatCount是重复次数，显示1次就设置0
 * repeatMode是显示模式
 * dynamicItem是SVGA的原有的填充模块，替换头像或者文案
 * svgaCallback是原有的SVGA的回调
 * detachedToStop是否移出屏幕就停止SVGA动画，默认是开启，如果repeatCount是ValueAnimator.INFINITE，就会重新返回屏幕后重新恢复SVGA动画
 */
SVGAImageViewDrawableTarget(
    imageView: ImageView, 
    var repeatCount: Int = ValueAnimator.INFINITE,
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






## 功能示例

* [使用位图替换指定元素。](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-Image)
* [在指定元素上绘制文本。](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-Text)
* [在指定元素上绘制富文本。](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-Text-Layout)
* [隐藏指定元素。](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-Hidden)
* [在指定元素上自由绘制。](https://github.com/yyued/SVGAPlayer-Android/wiki/Dynamic-Drawer)

## APIs

请参阅此处 [https://github.com/yyued/SVGAPlayer-Android/wiki/APIs](https://github.com/yyued/SVGAPlayer-Android/wiki/APIs)

## CHANGELOG

请参阅此处 [CHANGELOG](./CHANGELOG.md)
