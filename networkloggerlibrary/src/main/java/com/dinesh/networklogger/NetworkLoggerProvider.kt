package com.dinesh.networklogger

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.room.Room
import retrofit2.Retrofit
import safety.com.br.android_shake_detector.core.ShakeCallback
import safety.com.br.android_shake_detector.core.ShakeOptions
import safety.com.br.android_shake_detector.core.ShakeDetector





object NetworkLoggerProvider {

    var db: NetworkLoggerDataBase? = null


    fun init(context: Context){
        if(db == null) {
            db = Room.databaseBuilder(context, NetworkLoggerDataBase::class.java, "network-db")
                .build()
        }




    }

    fun showNetworkCalls(activity: Activity){
        val intent = Intent(activity, NetworkLoggerDetailsActivity::class.java)
        activity.startActivity(intent)
    }


}