package com.example.ty.kotlinlive.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.repository.History

/*
* Created by TY on 2017/6/27.
*/


class HistoryAdapter(layoutResId: Int, data: List<History>) : BaseQuickAdapter<History, BaseViewHolder>(layoutResId, data) {

    override fun convert(helper: BaseViewHolder?, item: History?) {
        helper?.setText(R.id.content, "${item?.content}${item?.date}")
    }

}