package com.dinesh.networklogger

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Observable

@Dao
interface RequestLogDao{
    @Insert
    fun insert(requestVO: RequestVO)

    @Query("SELECT * from $TABLE_REQUEST ORDER BY created DESC")
    fun getRequests(): Observable<List<RequestVO>>

    @Query("DELETE FROM $TABLE_REQUEST ")
    fun deleteRequests(): Completable
}