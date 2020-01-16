package com.dinesh.networklogger

import androidx.room.Entity
import androidx.room.PrimaryKey
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import java.util.*

@Entity(tableName = TABLE_REQUEST)
data class RequestVO(
    val url: String,
    val method: String,
    val headers: String,
    val body: String,
    val code: Int,
    val created: Long = Date().time,
    val requestId: String? = null,
    @PrimaryKey(autoGenerate = true)
    val id: Int=0
){
    constructor(request: Request, code: Int, responseBody: String, requestId: String? = null) : this(
        request.url().encodedPath(), request.method()?:"", request.headers()?.toString() ?:"", responseBody, code, requestId = requestId
    )
}


enum class Method{
    GET,
    POST,
    PUT,
    DELETE;
}