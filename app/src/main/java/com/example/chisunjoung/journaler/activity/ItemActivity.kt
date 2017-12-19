package com.example.chisunjoung.journaler.activity

import android.os.Bundle
import android.util.Log
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.model.MODE

/**
 * Created by chisunjoung on 13/12/2017.
 */
abstract class ItemActivity : BaseActivity(){
    protected var mode = MODE.VIEW
    override fun getActivityTitle(): Int {
        return R.string.app_name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val modeToSet = intent.getIntExtra(MODE.EXTRAS_KEY,MODE.VIEW.mode)
        mode = MODE.getByValue(modeToSet)
        Log.v(tag, "Mode[ $mode  ]")
    }
}