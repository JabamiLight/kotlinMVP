package com.example.ty.kotlinlive.ui.adapter

import android.content.Context
import android.text.format.DateUtils
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.example.ty.kotlinlive.R
import com.example.ty.kotlinlive.repository.Article
import java.text.SimpleDateFormat

/*
* Created by TY on 2017/6/29.
*/

class ArticleAdapter(var context: Context, layoutId: Int) : BaseQuickAdapter<Article, BaseViewHolder>(layoutId) {

    var date: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    override fun convert(helper: BaseViewHolder, item: Article?) {

        helper.setText(R.id.title, item?.desc)
        helper.setText(R.id.who, item?.who)
        helper.setText(R.id.type, item?.type)
        helper.setText(R.id.publishedAt, DateUtils.getRelativeTimeSpanString(date.parse(item?.publishedAt).time))

        var image: ImageView = helper.getView(R.id.image)


        if (item?.images == null || item.images.isEmpty()) {
            image.visibility = View.GONE
        } else {
            image.visibility = View.VISIBLE
            var width: Int = context.resources.getDimension(R.dimen.article_image_width).toInt()
            Glide.with(context).load("${item.images[0]}?imageView2/0/w/$width").into(image)
        }


    }

}