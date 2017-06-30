/*
 * Created by 动脑科技-Tim on 17-6-20 下午9:44
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午9:41
 */

package com.dn.tim.timganapp.ui.fragment.epg

import com.example.ty.kotlinlive.common.Type

import com.example.ty.kotlinlive.ui.fragment.epg.ArticleFragment


class WebFragmet : ArticleFragment() {

    companion object {
        fun newInstance(): WebFragmet {
            return WebFragmet()
        }
    }

    override fun getType(): String {
        return Type.前端.name
    }
}