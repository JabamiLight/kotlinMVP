package com.example.ty.kotlinlive.utils

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.example.ty.kotlinlive.ui.fragment.ProgressFragment
import kotlin.reflect.KClass

/*
* Created by TY on 2017/6/27.
*/


fun FragmentActivity.showProgress() {
    val dialog = ProgressFragment.newInstance()
    dialog.show(supportFragmentManager, ProgressFragment::class.java.simpleName)
}

fun FragmentActivity.dismissProgress() {
    val dialog = supportFragmentManager.findFragmentByTag(ProgressFragment::class.java.simpleName) as ProgressFragment?
    dialog?.dismiss()
}

fun Context.toast(msg: String) {
    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
}

fun <T : Fragment> FragmentManager.showFragment(className: KClass<T>, layoutId: Int, fragmentTransaction: FragmentTransaction): T {
    var willShowFragment = findFragmentByTag(className.simpleName)
    if (willShowFragment == null) {
        //        willShowFragment = calssName.newInstance()
//        willShowFragment = calssName.createInstance() //1
//        willShowFragment = calssName.primaryConstructor?.call() //2
        willShowFragment = className.constructors.first().call()
        fragmentTransaction.add(layoutId, willShowFragment, className.simpleName)
    } else {
        fragmentTransaction.show(willShowFragment)
    }
    fragmentTransaction.commit()
    return willShowFragment as T
}