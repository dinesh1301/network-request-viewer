package com.dinesh.networklogger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dinesh.networkloggerlibrary.R
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NetworkLoggerDetailsActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_logger_details)
        disposable.add(
            NetworkLoggerProvider
                .db!!
                .requestLogDao()
                .getRequests()
                .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
                .subscribe { requests ->
                    displayRequests(requests)
                }
        )


    }

    private fun displayRequests(requests: List<RequestVO>) {
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = RequestListAdapter(requests)

        findViewById<RecyclerView>(R.id.rvRequests).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }
}
