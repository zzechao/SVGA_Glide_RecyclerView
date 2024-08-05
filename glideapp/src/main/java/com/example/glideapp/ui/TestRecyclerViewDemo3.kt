package com.example.glideapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.glideapp.GlideRequests
import com.example.glideapp.R
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import com.zhouz.glidesvga.SVGACallback2
import com.zhouz.glidesvga.SVGAImageViewDrawableTarget
import com.zhouz.glidesvga.setSvgaClickMapListener
import kotlinx.android.synthetic.main.activity_test_recyclerview.head_tool
import kotlinx.android.synthetic.main.activity_test_recyclerview.head_tool_svga
import kotlinx.android.synthetic.main.activity_test_recyclerview.left
import kotlinx.android.synthetic.main.activity_test_recyclerview.right

/**
 * Time:2022/11/25 18:23
 * Author:
 * Description: 采样率
 */
class TestRecyclerViewDemo3 : AppCompatActivity() {

    val parse by lazy {
        SVGAParser(this@TestRecyclerViewDemo3)
    }

    private val head1 =
        "https://oss.qingyujiaoyou.com/makefriends/e648dbe7aaf74e8d80621f8b7234e519.jpg"
    private val head2 =
        "https://oss.qingyujiaoyou.com/makefriends/8770f03ad5c544bd934476f7a132755a.jpg"

    private val svgaUrl = "https://oss.qingyujiaoyou.com/boss/pc__uploadoss_73uwt9a3m9lzhyxv81sxif2u1z7mdngr.svga"

    private val list = mutableListOf<SVGAVideoEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recyclerview)
        (Glide.with(this@TestRecyclerViewDemo3) as GlideRequests)
            .asSVGAResource()
            .load(svgaUrl)
            .setSVGATag(svgaUrl)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .skipMemoryCache(true)
            .preload()
        left.setOnClickListener {
            parse.decodeFromAssets("test2.svga", object : SVGAParser.ParseCompletion {
                override fun onComplete(videoItem: SVGAVideoEntity) {
                    list.add(videoItem)
                    val drawable = SVGADrawable(videoItem)
                    head_tool_svga.setImageDrawable(drawable)
                    head_tool_svga.loops = 1
                    head_tool_svga.startAnimation()
                }

                override fun onError() {
                }
            })
            head_tool_svga.visibility = View.VISIBLE
        }
        var index = 0
        right.setOnClickListener {
            index += 1
            (Glide.with(this@TestRecyclerViewDemo3) as GlideRequests)
                .asSVGAResource()
                .load("file:///android_asset/test2.svga")
                .setSVGATag(svgaUrl)
                .into(SVGAImageViewDrawableTarget(head_tool, 1, svgaCallback = object : SVGACallback2 {
                    override fun onFinished() {
                        super.onFinished()
                        Log.i("TestRecyclerViewDemo3", "onFinished index:$index")
                    }
                }, showLastFrame = (index % 2) == 0).apply {
                    setSvgaClickMapListener(listOf("touxiang1", "touxiang2")) {
                        Log.i("TestRecyclerViewDemo3", "click $it")
                    }
                })
            head_tool_svga.visibility = View.GONE
        }
    }
}

