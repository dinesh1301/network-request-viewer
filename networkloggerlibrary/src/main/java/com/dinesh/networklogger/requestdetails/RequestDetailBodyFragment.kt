package com.dinesh.networklogger.requestdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.dinesh.networklogger.NetworkLoggerProvider
import com.dinesh.networkloggerlibrary.R
import com.yuyh.jsonviewer.library.JsonRecyclerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_request_body.*

private val REQUEST_ID_KEY = "requestId"
class RequestDetailBodyFragment : Fragment(){
    var requestId: Int = 0
    val disposable = CompositeDisposable()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_request_body, container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { safeArguement ->
            requestId = safeArguement.getInt(REQUEST_ID_KEY) }
        disposable.add(
        NetworkLoggerProvider
            .db!!
            .requestLogDao()
            .getRequests()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { requests -> displayJson(requests.firstOrNull()?.body?:"", rvJson) }
        )


    }

    private fun displayJson(body: String, rvJson: JsonRecyclerView?) {
        rvJson?.bindJson(body)
    }

    companion object {
        fun instance(requestId: Int): RequestDetailBodyFragment {
            val fragment = RequestDetailBodyFragment()
            fragment.arguments = bundleOf(Pair(REQUEST_ID_KEY, requestId))
            return fragment
        }
    }
}