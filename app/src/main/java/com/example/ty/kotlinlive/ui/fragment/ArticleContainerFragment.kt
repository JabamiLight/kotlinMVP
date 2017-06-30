package com.example.ty.kotlinlive.ui.fragment

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dn.tim.timganapp.ui.fragment.epg.*
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.net.Api
import com.example.ty.kotlinlive.repository.PublishedDate
import com.example.ty.kotlinlive.ui.adapter.MainAdapter
import com.example.ty.kotlinlive.ui.fragment.epg.ExpendFragmet
import com.example.ty.kotlinlive.utils.dismissProgress
import com.example.ty.kotlinlive.utils.showProgress
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_article_container.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/*
* Created by TY on 2017/6/29.
*/

class ArticleContainerFragment : Fragment() {


    companion object {
        fun newInstance(): ArticleContainerFragment = ArticleContainerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_article_container, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadPublishData()
    }

    private fun loadPublishData() {
        activity?.showProgress()
        Api.create().getPublishedDate()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ result -> (parseResult(result)) }, {}, {
                    activity?.dismissProgress()
                    setupViews()
                })
    }

    private fun setupViews() {
        val fragments = ArrayList<Fragment>()
        fragments.add(RecommdFragment.newInstance(pulished!!))
        fragments.add(AndroidFragmet.newInstance())
        fragments.add(ExpendFragmet.newInstance())
        fragments.add(IOSFragmet.newInstance())
        fragments.add(VideoFragmet.newInstance())
        fragments.add(WebFragmet.newInstance())
        val mainAdapter: MainAdapter = MainAdapter(fragments, resources.getStringArray(R.array.title), fragmentManager)
        viewPager.adapter = mainAdapter
        viewPager.offscreenPageLimit = 6
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.tabMode = TabLayout.MODE_SCROLLABLE

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewPager.setCurrentItem(tab!!.position, false)
            }

        })


    }

    private var pulished: String? = null

    private fun parseResult(result: PublishedDate) {
        if (result.error || result.results == null || result.results.size == 0) {
            pulished = SimpleDateFormat("yyyy/MM/dd").format(Date())
            return
        }
        pulished = result.results[0].replace("-", "/")
    }


}