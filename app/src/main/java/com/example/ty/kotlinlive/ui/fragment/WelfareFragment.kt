package com.example.ty.kotlinlive.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.common.Type
import com.example.ty.kotlinlive.repository.Article
import com.example.ty.kotlinlive.ui.activity.PhotoActivity
import com.example.ty.kotlinlive.ui.adapter.GirlAdpter
import com.example.ty.kotlinlive.utils.toast
import kotlinx.android.synthetic.main.fragment_base.*

/*
* Created by TY on 2017/6/27.
*/
class WelfareFragment : BaseFragment() {

    var adapter: GirlAdpter? = null

    companion object {
        fun newInstance(): WelfareFragment {
            return WelfareFragment()
        }
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar.visibility = View.VISIBLE
    }


    override fun initRecyclerView() {
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var simpleAnimator: SimpleItemAnimator = recyclerView.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false
        adapter = GirlAdpter(activity, R.layout.item_girl)
        adapter?.setOnItemClickListener { adapter, view, position ->
            start2PhotoAcivity(adapter.getItem(position) as Article)
        }
        adapter?.setOnLoadMoreListener({
            pageNum++
            isRefresh = false
            loadData(pageSize, pageNum)
        }, recyclerView)
        recyclerView.adapter = adapter
    }

    override fun getType(): String {
        return Type.福利.name
    }

    override fun loadError() {
        activity?.toast(" girl load data error")
    }

    override fun loadSuccess(data: List<Article>) {
        if (isRefresh) {
            adapter?.setNewData(data)
        } else {
            adapter?.addData(data)
        }
    }

    override fun loadFinish() {
        if (swipeLayout.isRefreshing) {
            swipeLayout.isRefreshing = false
        }
        adapter?.loadMoreComplete()
    }

    private fun start2PhotoAcivity(article: Article) {
        val intent = Intent(activity, PhotoActivity::class.java)
        intent.putExtra("url", article.url)
        activity?.startActivity(intent)
    }


}