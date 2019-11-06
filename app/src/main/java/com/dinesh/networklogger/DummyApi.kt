package com.dinesh.networklogger

import com.google.gson.JsonArray
import io.reactivex.Observable
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.http.GET

interface DummyApi{
    @GET("https://reqres.in/api/users?page=2")
    fun getData():Observable<ApiData>


}