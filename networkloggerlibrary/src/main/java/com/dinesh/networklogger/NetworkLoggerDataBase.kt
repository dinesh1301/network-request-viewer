package com.dinesh.networklogger

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import io.reactivex.Completable

@Database(
    entities = [RequestVO::class],
    version = 1
)
abstract class NetworkLoggerDataBase : RoomDatabase(){

    abstract fun requestLogDao(): RequestLogDao

}