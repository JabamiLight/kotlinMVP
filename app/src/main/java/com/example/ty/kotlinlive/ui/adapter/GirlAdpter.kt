package com.example.ty.kotlinlive.ui.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.repository.Article

/*
* Created by TY on 2017/6/27.
*/

class GirlAdpter(val context: Context, layoutResId: Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutResId) {

    override fun convert(helper: BaseViewHolder?, item: Article?) {
        val imageView = helper?.getView<ImageView>(R.id.image)
        Glide.with(context).load(item?.url).into(imageView)
    }


}