/*
 * Created by 动脑科技-Tim on 17-6-20 下午9:45
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-6-20 下午9:45
 */

package com.dn.tim.timganapp.ui.fragment.epg

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.common.Type
import com.example.ty.kotlinlive.net.Api
import com.example.ty.kotlinlive.repository.Article
import com.example.ty.kotlinlive.ui.activity.ArticleDetailActivity
import com.example.ty.kotlinlive.ui.adapter.RecommendAdapter
import com.example.ty.kotlinlive.utils.toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recommend.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.support.v4.startActivity
import org.json.JSONObject

class RecommdFragment : Fragment(), AnkoLogger {

    companion object {
        fun newInstance(date: String): RecommdFragment {
            val fragment: RecommdFragment = RecommdFragment()
            val args: Bundle = Bundle()
            if (!date.isEmpty()) {
                args.putString("date", date)
            }
            fragment.arguments = args
            return fragment
        }
    }

    var date: String? = null
    var iamgeUrl: String? = null


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        date = arguments.get("date") as String?
        return inflater?.inflate(R.layout.fragment_recommend, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    private fun loadData() {
        Api.create()
                .getDataByDate(date!!)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe { result ->
                    setUpView(parseRsult(result.string())!!)
                }
    }

    private fun parseRsult(result: String?): List<Article>? {
        val jsonObject = JSONObject(result)
        val error = jsonObject.getBoolean("error")
        if (error) {
            activity?.toast("Service data error!")
            return null
        }
        val results = jsonObject.getJSONObject("results")
        val data: MutableList<Article> = arrayListOf()

        val gson = Gson()

        val type = object : TypeToken<List<Article>>() {}.type
        results.keys().forEach {
            if (it != Type.福利.name) {
                data.addAll(gson.fromJson<List<Article>>(results.getJSONArray(it).toString(), type))
            } else {
                val array = results.getJSONArray(it)
                if (array.length() >= 0) {
                    iamgeUrl = array.getJSONObject(0).getString("url")
                }
            }
        }
//        data.forEach {
//            info(it)
//        }
        return data


    }

    private fun setUpView(data: List<Article>) {
        Glide.with(activity)
                .load(iamgeUrl)
                .into(welFare)

        val adapter = RecommendAdapter(R.layout.item_recommend, data)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.isNestedScrollingEnabled = false
        recyclerView.adapter = adapter
        adapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            start2DetailAcivity(adapter.getItem(position) as Article)
        }
    }

    private fun start2DetailAcivity(article: Article) {
        startActivity<ArticleDetailActivity>("desc" to article.desc, "url" to article.url)
    }


}


