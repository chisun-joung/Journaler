package com.example.chisunjoung.journaler

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.util.Log
import com.example.chisunjoung.journaler.service.MainService
import com.example.chisunjoung.journaler.service.NetworkReceiver

/**
 * Created by chisunjoung on 12/12/2017.
 */

class Journaler : Application(){

    private val networkReceiver = NetworkReceiver()
    companion object {
        val tag = "Journaler"
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        Log.v(tag,"[ ON CREATE ]")
        val filter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(networkReceiver, filter)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag,"[ ON LOW MEMORY ]")
        stopService()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        Log.d(tag, "[ ON TRIM MEMORY ]: $level")
    }


    private fun stopService(){
        val serviceIntent = Intent(this,MainService::class.java)
        stopService(serviceIntent)
    }
}