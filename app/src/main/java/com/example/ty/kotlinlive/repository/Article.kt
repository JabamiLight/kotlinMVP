package com.example.ty.kotlinlive.repository

/*
* Created by TY on 2017/6/27.
*/



data class Article(val _id: String, val createdAt: String, val desc: String,
                   val images: Array<String>,
                   val publishedAt: String, val source: String, val type: String,
                   val url: String, val used: Boolean, val who: String) {

}
