package com.dinesh.networklogger

import android.app.Application
import com.idescout.sql.SqlScoutServer

class DemoApplication : Application(){

    private lateinit var sqlScoutServer: SqlScoutServer

    override fun onCreate() {
        super.onCreate()
        NetworkLoggerProvider.init(this)
        sqlScoutServer = SqlScoutServer.create(this, packageName)
    }
}