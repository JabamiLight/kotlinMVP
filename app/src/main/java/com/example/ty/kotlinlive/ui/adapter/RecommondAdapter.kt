package com.example.ty.kotlinlive.ui.adapter

import android.text.Html
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.TextView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.repository.Article

/*
* Created by TY on 2017/6/29.
*/

class RecommendAdapter(layoutId: Int, data: List<Article>) : BaseQuickAdapter<Article, BaseViewHolder>(layoutId, data) {
    override fun convert(helper: BaseViewHolder?, item: Article?) {
        val fromat = "<font color = '#757575'>${item?.who}</font>"
        val desc = helper?.getView<TextView>(R.id.content)
        desc?.text = Html.fromHtml("${item?.desc}($fromat)")
//        val desc = Html.fromHtml("${item?.desc}($fromat)")
        val titleTv = helper?.getView<TextView>(R.id.title)
        titleTv?.text = item?.type
        val position = helper?.layoutPosition!! - this.headerLayoutCount
        when (position) {
            0 -> {
                titleTv?.visibility = VISIBLE
            }
            else -> {
                if (item?.type == getItem(position - 1)!!.type) {
                    titleTv?.visibility = GONE
                } else {
                    titleTv?.visibility = VISIBLE
                }
            }
        }

    }


}
