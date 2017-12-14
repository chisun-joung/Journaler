package com.example.chisunjoung.journaler.fragment

import android.support.v4.app.Fragment
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * Created by chisunjoung on 14/12/2017.
 */


abstract class BaseFragement : Fragment() {
    protected abstract val logTag : String
    protected abstract fun getLayout(): Int

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        Log.d(logTag, "[ ON CREATE VIEW ]")
        return inflater?.inflate(getLayout(), container, false)
    }

    override fun onPause() {
        super.onPause()
        Log.v(logTag, "[ ON PAUSE ]")
    }

    override fun onResume() {
        super.onResume()
        Log.v(logTag, "[ ON RESUME ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(logTag, "[ ON DESTROY ]")
    }
}