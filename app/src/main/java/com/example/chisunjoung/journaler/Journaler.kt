package com.example.chisunjoung.journaler

import android.app.Application
import android.content.Context

/**
 * Created by chisunjoung on 12/12/2017.
 */

class Journaler : Application(){
    companion object {
        var ctx: Context? = null
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }
}