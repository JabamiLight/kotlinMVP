package com.example.ty.kotlinlive.convert;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

final class MyGsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    MyGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {


        //流只能读取一次,读取一次后就会抛出异常，流读取方法
//        InputStreamReader i = new InputStreamReader(value.source().inputStream(), "utf-8");
//        Log.d("tedu", "length: "+value.contentLength());
//        char[] buffer = new char[1024];
//        StringBuilder builder=new StringBuilder();
//        int len=0;
//        while (-1 != (len = i.read(buffer, 0, 1024))) {
//            builder.append(buffer,0,len);
//        }
//        Log.d("tedu", "convert: "+builder.toString());
//        MediaType contentType = value.contentType();
//        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
//        InputStream inputStream = new ByteArrayInputStream(builder.toString().getBytes());
//        Reader reader = new InputStreamReader(inputStream, charset);
//        JsonReader jsonReader = gson.newJsonReader(reader);



        //改变后能用代码
        String response = value.string();
        Log.d("tedu", "convert: "+response);
        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.toString().getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);

            //原始代码
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());

        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
