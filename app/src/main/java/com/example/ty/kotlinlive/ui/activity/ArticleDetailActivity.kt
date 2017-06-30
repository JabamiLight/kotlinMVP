package com.example.ty.kotlinlive.ui.activity

import android.graphics.Bitmap
import android.net.http.SslError
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.utils.dismissProgress
import com.example.ty.kotlinlive.utils.showProgress
import kotlinx.android.synthetic.main.activity_article_detail.*

class ArticleDetailActivity : AppCompatActivity() {

    var title: String = ""
    var url: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        initData()
        initView()
    }

    private fun initView() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        webView.settings.javaScriptEnabled = true
        webView.settings.useWideViewPort = true

        webView.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                view?.loadUrl(url)
                return true
            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                showProgress()
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                dismissProgress()
                super.onPageFinished(view, url)

            }
        })
        webView.loadUrl(url)
    }

    private fun initData() {
        title = intent.getStringExtra("desc")
        url = intent.getStringExtra("url")

    }

    override fun onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean =

            when (item?.itemId) {
                R.id.home -> {
                    finish()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(item)
                }
            }

}
