package com.dinesh.networklogger

import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.dinesh.networkloggerlibrary.R
import com.yuyh.jsonviewer.library.JsonRecyclerView
import kotlinx.android.synthetic.main.request_item.view.*
import java.lang.Exception

class RequestViewHolder(private val view: View): RecyclerView.ViewHolder(view){

    fun bind(requestVO: RequestVO){
        view.findViewById<TextView>(R.id.tvUrl).text = requestVO.url
        val jsonRecyclerView = view.findViewById<JsonRecyclerView>(R.id.rvJson)
        try {
            jsonRecyclerView.bindJson(requestVO.body)
        }
        catch (exception: Exception){
            Log.e(exception)
            jsonRecyclerView.bindJson("{'error':'invalid_json'}")

        }

        val codeView = view.findViewById<TextView>(R.id.tvCode)
        codeView.text = requestVO.code.toString()

        if(requestVO.code in 200..299)
            codeView.setTextColor( view.context.resources.getColor(R.color.green) )
        else
            codeView.setTextColor( view.context.resources.getColor(R.color.red) )


    }
}