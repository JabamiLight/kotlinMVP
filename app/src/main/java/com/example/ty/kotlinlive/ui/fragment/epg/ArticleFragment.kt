package com.example.ty.kotlinlive.ui.fragment.epg

import android.support.v7.widget.LinearLayoutManager
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.repository.Article
import com.example.ty.kotlinlive.ui.activity.ArticleDetailActivity
import com.example.ty.kotlinlive.ui.adapter.ArticleAdapter
import com.example.ty.kotlinlive.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_article_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.toast

/*
* Created by TY on 2017/6/29.
*/

abstract class ArticleFragment : BaseFragment(), AnkoLogger {

    var adapter: ArticleAdapter? = null


    override fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = ArticleAdapter(activity, R.layout.item_article)
        recyclerView.adapter = adapter
        adapter?.setOnLoadMoreListener({ loadMore() }, recyclerView)
        adapter?.setOnItemClickListener { adapter, view, position ->
            startDetailActivity(adapter.data[position] as Article)
        }
    }

    private fun loadMore() {
        pageNum++
        isRefresh = false
        loadData(pageSize, pageNum)
    }

    override fun loadError() {
        activity?.toast("load error")
    }

    override fun loadSuccess(results: List<Article>) {
        info("loadsuccess" + results)
        if (isRefresh) {
            adapter?.setNewData(results)
        } else {
            adapter?.addData(results)
        }
    }

    override fun loadFinish() {
        if (swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = false
        }
        adapter?.loadMoreComplete()
    }

    private fun startDetailActivity(article: Article) {
        startActivity<ArticleDetailActivity>("desc" to article.desc, "url" to article.url)

    }


}