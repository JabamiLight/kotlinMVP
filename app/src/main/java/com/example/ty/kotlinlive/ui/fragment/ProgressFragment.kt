package com.example.ty.kotlinlive.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

/*
* Created by TY on 2017/6/27.
*/

class ProgressFragment : android.support.v4.app.DialogFragment() {


    companion object {
        fun newInstance(): ProgressFragment {
            return ProgressFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return ProgressBar(activity)
    }

}