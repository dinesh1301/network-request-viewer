package com.dinesh.networklogger

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dinesh.networkloggerlibrary.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RequestListAdapter(private val requests: List<RequestVO>):
    RecyclerView.Adapter<RequestViewHolder>() {
    private val clickSubject: PublishSubject<View> = PublishSubject.create()
    private val longClickSubject: PublishSubject<View> = PublishSubject.create()

    fun getClickObservable(): Observable<View> = clickSubject

    fun getLongClickObservable(): Observable<View> = longClickSubject

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.request_item, parent, false)
        view.setOnClickListener {
            val bodyView = it.findViewById<View>(R.id.jsonView)
            if(bodyView.visibility == View.GONE)
                bodyView.visibility = View.VISIBLE
            else
                bodyView.visibility = View.GONE
            clickSubject.onNext(it)
        }
        view.setOnLongClickListener { longClickSubject.onNext(it)
            return@setOnLongClickListener true
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