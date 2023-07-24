package com.example.glideapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.glideapp.GlideRequests
import com.example.glideapp.R
import com.opensource.svgaplayer.SVGADrawable
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import com.svga.glide.SVGAImageViewDrawableTarget
import java.net.URL
import kotlinx.android.synthetic.main.activity_test_recyclerview.head_recycler

/**
 * Time:2022/11/25 18:23
 * Author:
 * Description:
 */
class TestRecyclerViewDemo2 : AppCompatActivity() {

    val parse by lazy {
        SVGAParser(this@TestRecyclerViewDemo2)
    }


    private val imageString = "file:///android_asset/raw/rose.svga"

    private val urls = mutableListOf(
        "https://oss.qingyujiaoyou.com/boss/pc__config_vrn66ladnywcbd34n09gkxgv0puh3ads.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_wijwmlwu1nlxv1bel211osncczj300de.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_sz63wwpe5q1m7c2zjv7pjk1nrr2s533k.svga",
        "https://cdn.qingyujiaoyou.com/uploadSource/d2a44900-1d29-11ed-814e-ef5470843827.svga",
        "https://oss.qingyujiaoyou.com/boss/pc_50047069_uploadoss_ydg7qswegdhrmu6x72cu1zpw3oz0hzg9.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_5ca5r7vhkx41pbtuk4sffrg8ivi5r9cx.svga",
        "https://oss.qingyujiaoyou.com/boss/pc_50071620_config_44hdjx13tnt0e0uts7ftdmeprfaws4kb.svga"
    )

    private val map = mutableMapOf<String, SVGAVideoEntity>()

    private val svgaUrl = "https://oss.qingyujiaoyou.com/boss/pc_50045958_config_kd6fs4mwukni0e7phpe594gkxr2tdqft.svga"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recyclerview)
        head_recycler.layoutManager = GridLayoutManager(this, 4)
        head_recycler.adapter = PopupListAdapter()
//        (Glide.with(this@TestRecyclerViewDemo) as GlideRequests).asSVGA().load(svgaUrl)
//            .setSVGATag(svgaUrl)
//            .into(SVGAImageViewDrawableTarget(head_tool))

//        SVGAGlideDecodeConfig.getBitmapPool()?.let {
//            Log.i("TestRecyclerViewDemo", "0000")
//        } ?: Log.i("TestRecyclerViewDemo", "1111")
    }

    inner class PopupListAdapter : RecyclerView.Adapter<ItemHolder>() {

        override fun getItemCount(): Int {
            return 8
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val url = urls[position % urls.size]
            (Glide.with(this@TestRecyclerViewDemo2) as GlideRequests).asSVGAResource()
                .load(url)
                .setSVGATag(url)
                .into(SVGAImageViewDrawableTarget(holder.head))
            holder.headsvga.visibility = View.GONE
        }

        override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ItemHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_head, parent, false)
            return ItemHolder(view)
        }
    }

    class ItemHolder(val item: View) : RecyclerView.ViewHolder(item) {
        val head by lazy { item.findViewById<ImageView>(R.id.head) }
        val headsvga by lazy { item.findViewById<SVGAImageView>(R.id.SVGAImageView) }
    }
}

