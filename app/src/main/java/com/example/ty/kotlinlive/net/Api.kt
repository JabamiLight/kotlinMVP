package com.example.ty.kotlinlive.net

import com.example.ty.kotlinlive.convert.MyConvertFactory
import com.example.ty.kotlinlive.repository.PublishedDate
import com.example.ty.kotlinlive.repository.Result
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/*
* Created by TY on 2017/6/27.
*/

interface Api {

    //http://gank.io/   api/data/iOS/10/1
    @GET("api/data/{type}/{pageSize}/{pageNumber}")
    fun getData(@Path("type") type: String,
                @Path("pageSize") pageSize: Int,
                @Path("pageNumber") pageNumber: Int): Observable<Result>

    @GET("history")
    fun getHistory(): Observable<ResponseBody>


    @GET("api/day/history")
    fun getPublishedDate(): Observable<PublishedDate>

    @GET("api/day/{date}")
    fun getDataByDate(@Path("date") date: String): Observable<ResponseBody>


    companion object {
        fun create(): Api {
            val log = HttpLoggingInterceptor()
            log.level = HttpLoggingInterceptor.Level.BASIC
            val client = OkHttpClient.Builder()
                    .addInterceptor(log)
                    .build()
            val retrofit = Retrofit.Builder()
                    .client(client)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MyConvertFactory.create())
                    .baseUrl("http://gank.io/")
                    .build()
            return retrofit.create(Api::class.java)
        }

    }
}