package com.example.ty.kotlinlive.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/*
* Created by TY on 2017/6/29.
*/

class MainAdapter(val datas: List<Fragment>, var title: Array<String>, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return datas[position]
    }

    override fun getCount(): Int {
        return datas.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return title[position]
    }
}