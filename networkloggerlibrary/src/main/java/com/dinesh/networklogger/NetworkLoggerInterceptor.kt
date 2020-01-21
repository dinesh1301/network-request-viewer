package com.dinesh.networklogger

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody
import org.jetbrains.anko.doAsync

class NetworkLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val contentType = response.body()?.contentType()
        val bodyString = response.body()?.string()
        val code = response.code()
        val requestVO = RequestVO(request, code, bodyString?:"", response.header("request-id"))
        val body = ResponseBody.create(contentType, bodyString?:"")


       doAsync {
           try {
               NetworkLoggerProvider.db!!.requestLogDao().insert(requestVO)
           }
           catch (exception: Exception){
               Log.e("NetworlLoggingError", exception.message?:"")
           }
       }

        return response.newBuilder().body(body).build()
    }
}