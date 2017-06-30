package com.example.ty.kotlinlive.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.net.Api
import com.example.ty.kotlinlive.repository.History
import com.example.ty.kotlinlive.ui.activity.HistoryActivity
import com.example.ty.kotlinlive.ui.adapter.HistoryAdapter
import com.example.ty.kotlinlive.utils.dismissProgress
import com.example.ty.kotlinlive.utils.showProgress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_history.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/*
* Created by TY on 2017/6/27.
*/


class HistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        loadPublsiedDate()
    }

    private fun loadPublsiedDate() {
        activity?.showProgress()
        val api = Api.create()
        api.getHistory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    setUpRecyclerView(parseHtml(it.string()))
                }, {}, {})

    }

    private fun setUpRecyclerView(parseHtml: List<History>) {
        activity?.dismissProgress()
        val adapter = HistoryAdapter(R.layout.item_history, parseHtml)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener { adapter, view, position ->
            var history: History = adapter.getItem(position) as History
            start2HistoryAcivity(history.date)

        }
    }

    private fun parseHtml(html: String): List<History> {
        val doc: Document = Jsoup.parse(html)
        val typo: Elements = doc.getElementsByClass("typo")
        val data: MutableList<History> = arrayListOf()
        var history: History
        typo.select("a").listIterator().forEach {
            history = History(it.attr("href").substring(1), it.text())
            data.add(history)
        }
        return data
    }

    private fun start2HistoryAcivity(date: String) {
        val intent = Intent(activity, HistoryActivity::class.java)
        intent.putExtra("date", date)
        activity?.startActivity(intent)
    }


    companion object {

        fun newInstanse(): HistoryFragment {
            return HistoryFragment()
        }


    }

}