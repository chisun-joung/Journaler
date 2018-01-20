package com.example.chisunjoung.journaler.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

/**
 * Created by chisunjoung on 2018. 1. 17..
 */
class BootReceiver : BroadcastReceiver(){
    val tag = "Boot receiver"
    override fun onReceive(p0: Context?, p1: Intent?) {
        Log.i(tag,"Boot completed")

    }
}