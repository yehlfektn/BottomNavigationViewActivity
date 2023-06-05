package com.csi.bottomnavigationactivity.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import timber.log.Timber

class MyBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        Timber.e("MyBroadcastReceiver, получили сообщение")
        Timber.e("MyBroadcastReceiver, ${intent.data}")
    }
}