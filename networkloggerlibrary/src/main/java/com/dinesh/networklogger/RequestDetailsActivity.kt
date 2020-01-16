package com.dinesh.networklogger

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.dinesh.networkloggerlibrary.R
import kotlinx.android.synthetic.main.activity_request_details.*
const val REQUEST_ID_KEY = "requestId"
class RequestDetailsActivity : AppCompatActivity() {

    private var requestId = 0
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request_details)
        requestId = savedInstanceState?.getInt(REQUEST_ID_KEY)?:0
        view_pager.adapter = RequestDetailsFragmentAdapter(this, requestId)
        view_pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
    }


}