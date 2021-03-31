package com.sampleassignment.yozotechnologies.model.retrofit;

import com.sampleassignment.yozotechnologies.common.Common;

import io.reactivex.plugins.RxJavaPlugins;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    public static Retrofit retrofitInstance;

    public static Retrofit getInstance()
    {
        if (retrofitInstance==null)
        {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        }
        return retrofitInstance;
    }
}
