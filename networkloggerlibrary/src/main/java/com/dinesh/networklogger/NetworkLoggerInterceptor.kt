package com.dinesh.networklogger

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response
import org.jetbrains.anko.doAsync

class NetworkLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestVO = RequestVO(request, response)

       doAsync {
           try {
               NetworkLoggerProvider.db!!.requestLogDao().insert(requestVO)
           }
           catch (exception: Exception){
               Log.e("NetworlLoggingError", exception.message?:"")
           }
       }

        return response
    }
}