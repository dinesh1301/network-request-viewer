package com.dinesh.networklogger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dinesh.networkloggerlibrary.R

class RequestListAdapter(private val requests: List<RequestVO>):
    RecyclerView.Adapter<RequestViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        view.setOnClickListener {
            val bodyView = it.findViewById<View>(R.id.jsonView)
            if(bodyView.visibility == View.GONE)
                bodyView.visibility = View.VISIBLE
            else
                bodyView.visibility = View.GONE
        }
        return RequestViewHolder(view)
    }

    override fun getItemCount(): Int {
        return requests.count()
    }

    override fun onBindViewHolder(holder: RequestViewHolder, position: Int) {
        holder.bind(requests[position])
    }
}