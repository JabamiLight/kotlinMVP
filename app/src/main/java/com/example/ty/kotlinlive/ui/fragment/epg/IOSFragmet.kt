/*
 * Created by 动脑科技-Tim on 17-6-20 下午9:43
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午9:41
 */

package com.dn.tim.timganapp.ui.fragment.epg

import com.example.ty.kotlinlive.common.Type
import com.example.ty.kotlinlive.ui.fragment.epg.ArticleFragment


class IOSFragmet : ArticleFragment() {

    companion object {
        fun newInstance(): IOSFragmet {
            return IOSFragmet()
        }
    }

    override fun getType(): String {
        return Type.iOS.name
    }
}