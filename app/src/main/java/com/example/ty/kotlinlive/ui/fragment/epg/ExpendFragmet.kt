/*
 * Created by 动脑科技-Tim on 17-6-20 下午9:38
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午9:38
 */

package com.example.ty.kotlinlive.ui.fragment.epg

import com.example.ty.kotlinlive.common.Type


class ExpendFragmet : ArticleFragment() {

    companion object {
        fun newInstance(): ExpendFragmet {
            return ExpendFragmet()
        }
    }

    override fun getType(): String {
        return Type.拓展资源.name
    }
}