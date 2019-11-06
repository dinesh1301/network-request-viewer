package com.dinesh.networklogger

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory




class MainActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val api = getRetrofit().create(DummyApi::class.java)


        btClickToView.setOnClickListener {
            NetworkLoggerProvider.showNetworkCalls(this)
        }

        disposable.add(
        api.getData()
            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ jsonObject ->
            Log.d("request","request: $jsonObject")
        },{error ->
                Log.e("error", error.message?:"")
            })
        )

    }




    fun getRetrofit(): Retrofit{
        val httpClient = OkHttpClient
            .Builder()
            .addInterceptor(NetworkLoggerInterceptor())
            .build()

        return Retrofit
            .Builder()
            .callFactory(httpClient)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .baseUrl("https://www.google.com")
            .build()
    }
}
