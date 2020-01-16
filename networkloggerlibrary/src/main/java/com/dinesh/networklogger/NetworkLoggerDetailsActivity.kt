package com.dinesh.networklogger

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dinesh.networkloggerlibrary.R
import com.google.gson.Gson
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class NetworkLoggerDetailsActivity : AppCompatActivity() {

    val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network_logger_details)
        setSupportActionBar(findViewById(R.id.my_toolbar))
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

    private fun deleteRequests(): Boolean {
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
        return true

    }


    private fun displayRequests(requests: List<RequestVO>) {
        val viewManager = LinearLayoutManager(this)
        val viewAdapter = RequestListAdapter(requests)


        val recyclerView = findViewById<RecyclerView>(R.id.rvRequests).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
        disposable.add(
        viewAdapter.getLongClickObservable()
            .map { view -> recyclerView.getChildLayoutPosition(view) }
            .map { position -> requests[position] }
            .subscribe{request -> openShareRequestIntent(request)}
        )

    }

    private fun openShareRequestIntent(request: RequestVO) {
        val sendIntent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, request.id)
            type="text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    private fun openRequestDetailActivity(requestId: Int) {
        val intent = Intent(this, RequestDetailsActivity::class.java)
        intent.putExtra(REQUEST_ID_KEY, requestId)
        startActivity(intent)
    }
}
