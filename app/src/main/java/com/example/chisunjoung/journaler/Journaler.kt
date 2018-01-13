package com.example.chisunjoung.journaler

import android.app.Application
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.chisunjoung.journaler.service.MainService

/**
 * Created by chisunjoung on 12/12/2017.
 */

class Journaler : Application(){
    companion object {
        val tag = "Journaler"
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
        Log.v(tag,"[ ON CREATE ]")
        startService()
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

    private fun startService() {
        val serviceIntent = Intent(this, MainService::class.java)
        startService(serviceIntent)
    }

    private fun stopService(){
        val serviceIntent = Intent(this,MainService::class.java)
        stopService(serviceIntent)
    }
}