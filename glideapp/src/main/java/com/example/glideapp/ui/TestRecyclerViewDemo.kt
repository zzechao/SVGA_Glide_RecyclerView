package com.example.glideapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.transition.Transition
import com.example.glideapp.GlideRequests
import com.example.glideapp.R
import com.opensource.svgaplayer.SVGAImageView
import com.opensource.svgaplayer.SVGAParser
import com.opensource.svgaplayer.SVGAVideoEntity
import com.zhouz.glidesvga.SVGAImageViewDrawableTarget
import com.zhouz.glidesvga.SVGAResource

import kotlinx.android.synthetic.main.activity_test_recyclerview.head_recycler
import kotlinx.android.synthetic.main.activity_test_recyclerview.head_tool
import kotlinx.android.synthetic.main.activity_test_recyclerview.left

/**
 * Time:2022/11/25 18:23
 * Author:
 * Description:
 */
class TestRecyclerViewDemo : AppCompatActivity() {

    val parse by lazy {
        SVGAParser(this@TestRecyclerViewDemo)
    }


    private val imageString = "file:///android_asset/raw/rose.svga"

    private val urls = mutableListOf(
        "https://oss.qingyujiaoyou.com/boss/pc__config_vrn66ladnywcbd34n09gkxgv0puh3ads.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_wijwmlwu1nlxv1bel211osncczj300de.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_sz63wwpe5q1m7c2zjv7pjk1nrr2s533k.svga",
        "https://cdn.qingyujiaoyou.com/uploadSource/d2a44900-1d29-11ed-814e-ef5470843827.svga",
        "https://oss.qingyujiaoyou.com/boss/pc_50047069_uploadoss_ydg7qswegdhrmu6x72cu1zpw3oz0hzg9.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_5ca5r7vhkx41pbtuk4sffrg8ivi5r9cx.svga",
        "https://oss.qingyujiaoyou.com/boss/pc__config_wijwmlwu1nlxv1bel211osncczj300de.svga",
        "https://oss.qingyujiaoyou.com/boss/pc_50071620_config_44hdjx13tnt0e0uts7ftdmeprfaws4kb.svga"
    )

    private val svgaVersion1 =
        "https://oss.qingyujiaoyou.com/boss/pc_50045958_config_kd6fs4mwukni0e7phpe594gkxr2tdqft.svga"

    private val map = mutableMapOf<String, SVGAVideoEntity>()

    private val svgaUrl =
        "https://turnover-cn.oss-cn-hangzhou.aliyuncs.com/newfindyou/svga/360558.svga"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_recyclerview)
        head_recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        head_recycler.adapter = PopupListAdapter()
        left?.setOnClickListener {
            (Glide.with(this@TestRecyclerViewDemo) as GlideRequests).asSVGAResource()
                .load(svgaVersion1).skipMemoryCache(true)
                .setSVGATag(svgaVersion1)
                .into(object : SVGAImageViewDrawableTarget(head_tool) {
                    override fun onResourceReady(resource: SVGAResource, transition: Transition<in SVGAResource>?) {
                        super.onResourceReady(resource, transition)
                    }
                })
        }
        //        (Glide.with(this@TestRecyclerViewDemo) as GlideRequests).asSVGA().load(svgaUrl)
        //            .setSVGATag(svgaUrl)
        //            .into(SVGAImageViewDrawableTarget(head_tool))
    }

    inner class PopupListAdapter : RecyclerView.Adapter<ItemHolder>() {


        override fun getItemCount(): Int {
            return 100
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int) {
            val url = urls[position % urls.size]
            (Glide.with(this@TestRecyclerViewDemo) as GlideRequests).asSVGAResource()
                .load(url)
                .setSVGATag(url)
                .into(SVGAImageViewDrawableTarget(holder.head))
            holder.headsvga.visibility = View.GONE
            holder.head.visibility = View.VISIBLE
        }

        override fun onBindViewHolder(holder: ItemHolder, position: Int, payloads: MutableList<Any>) {
            super.onBindViewHolder(holder, position, payloads)
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

