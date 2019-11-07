package com.dinesh.networklogger

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete -> deleteRequests()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteRequests() {
        disposable.add(
        NetworkLoggerProvider.db!!
            .requestLogDao()
            .deleteRequests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("", "Deletion succesful")
            },
                {
                    error ->
                    Log.e("error", error.stackTrace.toString())
                })
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
