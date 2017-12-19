package com.example.chisunjoung.journaler.activity

import android.os.Bundle
import com.example.chisunjoung.journaler.R
import kotlinx.android.synthetic.main.activity_todo.*

/**
 * Created by chisunjoung on 13/12/2017.
 */

class TodoActivity : ItemActivity() {

    companion object {
        val EXTRA_DATE = "EXTRA_DATE"
        val EXTRA_TIME = "EXTRA_TIME"
    }


    override fun getLayout(): Int {
        return R.layout.activity_todo
    }

    override val tag: String
        get() = "ToDo Activity"

    override fun getActivityTitle() = R.string.app_name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val data = intent.extras
        data?.let {
            val date = data.getString(EXTRA_DATE,"")
            val time = data.getString(EXTRA_TIME, "")
            pick_date.text = date
            pick_time.text = time
        }
    }
}