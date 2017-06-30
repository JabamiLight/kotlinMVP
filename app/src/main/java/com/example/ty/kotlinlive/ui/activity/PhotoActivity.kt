/*
 * Created by 动脑科技-Tim on 17-6-20 下午10:58
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午10:58
 */

package com.example.ty.kotlinlive.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.TextUtils
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.utils.toast
import kotlinx.android.synthetic.main.activity_history_detail.*
import kotlinx.android.synthetic.main.activity_photo.*

class PhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo)
        initToolBar()
        val url = intent.getStringExtra("url")
        if (TextUtils.isEmpty(url)) {
            this.toast("url is null")
            finish()
            return
        }
        Glide.with(this).load(url).into(photoView)
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = ""
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}
