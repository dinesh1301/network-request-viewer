package com.dinesh.networklogger

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import safety.com.br.android_shake_detector.core.ShakeBroadCastReceiver

class ShakeReceiver : ShakeBroadCastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        super.onReceive(context, intent)
        if (null != intent && intent.action.equals("shake.detector")) {
            Log.d("shakeDetector", "ShakeDetector: shake detected!")
        }
    }
}