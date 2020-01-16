package com.dinesh.networklogger

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dinesh.networklogger.requestdetails.RequestDetailBodyFragment

const val RESPONSE_BODY = 0
const val HEADERS = 1
class RequestDetailsFragmentAdapter (fragmentActivity: FragmentActivity, private val requestId: Int): FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            RESPONSE_BODY -> RequestDetailBodyFragment.instance(requestId)
            else -> RequestDetailBodyFragment.instance(requestId)
        }
    }
}