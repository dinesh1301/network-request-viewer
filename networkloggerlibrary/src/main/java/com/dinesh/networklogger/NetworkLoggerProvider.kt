package com.dinesh.networklogger

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.room.Room






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