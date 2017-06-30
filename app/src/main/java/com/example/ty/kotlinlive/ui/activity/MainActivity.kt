package com.example.ty.kotlinlive.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.ui.fragment.ArticleContainerFragment
import com.example.ty.kotlinlive.ui.fragment.HistoryFragment
import com.example.ty.kotlinlive.ui.fragment.WelfareFragment
import com.example.ty.kotlinlive.utils.showFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lastIndex = -1

    var lastFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.action_recommend -> {
                    changeTab(0)
                }
                R.id.action_girl -> {
                    changeTab(1)
                }
                R.id.action_history -> {
                    changeTab(2)
                }
            }
            true
        }
        changeTab(0)

    }


    private fun changeTab(position: Int) {
        if (lastIndex == position) {
            return
        }
        lastIndex = position
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        if (lastFragment != null) {
            fragmentTransaction.hide(lastFragment)
        }
        when (position) {
            0 -> {
                lastFragment = fragmentManager.showFragment(ArticleContainerFragment::class, R.id.container, fragmentTransaction)
            }
            1 -> {
                lastFragment = fragmentManager.showFragment(WelfareFragment::class, R.id.container, fragmentTransaction)
            }
            2 -> {
                lastFragment = fragmentManager.showFragment(HistoryFragment::class, R.id.container, fragmentTransaction)
            }

        }


    }
}
