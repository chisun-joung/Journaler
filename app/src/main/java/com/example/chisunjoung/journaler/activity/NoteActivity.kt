package com.example.chisunjoung.journaler.activity

import com.example.chisunjoung.journaler.R

/**
 * Created by chisunjoung on 13/12/2017.
 */

class NoteActivity : ItemActivity() {
    override fun getLayout(): Int {
        return    R.layout.activity_note
    }

    override val tag: String
        get() = "Note Activity"

    override fun getActivityTitle() = R.string.app_name
}