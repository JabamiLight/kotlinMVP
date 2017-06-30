package com.example.ty.kotlinlive.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.net.Api
import com.example.ty.kotlinlive.repository.Article
import com.example.ty.kotlinlive.repository.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_base.*

/*
* Created by TY on 2017/6/27.
*/


abstract class BaseFragment : Fragment() {

    val pageSize = 10
    var pageNum = 1
    var isRefresh = false


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_base, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        swipeLayout.setOnRefreshListener {
            isRefresh = true
            pageNum = 1
            loadData(pageSize, pageNum)
        }
        loadData(pageSize, pageNum)
    }

    protected fun loadData(pageSize: Int, pageNum: Int) {

        val api = Api.create()
        api.getData(getType(), pageSize, pageNum)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    parseResult(it)
                }


    }

    private fun parseResult(result: Result) {

        if (result.error) {
            loadError()
        } else {
            loadSuccess(result.results)
        }
        loadFinish()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }


    override fun onDetach() {
        super.onDetach()
    }


    abstract fun initRecyclerView()

    abstract fun getType(): String

    abstract fun loadError()

    abstract fun loadSuccess(results: List<Article>)

    abstract fun loadFinish()


}