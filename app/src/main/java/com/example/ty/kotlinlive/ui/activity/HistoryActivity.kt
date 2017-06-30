/*
 * Created by 动脑科技-Tim on 17-6-20 下午11:09
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午11:09
 */

package com.example.ty.kotlinlive.ui.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.dn.tim.timganapp.ui.fragment.epg.RecommdFragment
import com.example.ty.kotlinlive.R
import kotlinx.android.synthetic.main.activity_article_detail.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history_detail)
        val date = intent.getStringExtra("date")
        initToolBar(date)
        addContainer(date)
    }

    private fun addContainer(date: String) {
        supportFragmentManager.beginTransaction()
                .add(R.id.container, RecommdFragment.newInstance(date))
                .commit()
    }

    private fun initToolBar(date: String) {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = date
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
