package com.dinesh.networklogger

import okhttp3.Interceptor
import okhttp3.Response

class NetworkLoggerInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val requestVO = RequestVO(request, response)

        NetworkLoggerProvider.db!!.requestLogDao().insert(requestVO)
        return response
    }
}