package com.example.chisunjoung.journaler.activity

import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.*
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.location.LocationProvider
import com.example.chisunjoung.journaler.model.MODE
import com.example.chisunjoung.journaler.model.Note
import com.example.chisunjoung.journaler.service.DatabaseService
import kotlinx.android.synthetic.main.activity_note.*

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

    private var note : Note? = null
    private var location : Location? = null
    private var handler: Handler? = null
    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            updateNote()
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }

    private val locationListener = object : LocationListener {
        override fun onLocationChanged(p0: Location?) {
            p0?.let {
                LocationProvider.unsubscribe(this)
                location = p0
                val title = getNoteTitle()
                val content = getNoteContent()
                note = Note(title, content, p0)
                // Switching to intent service.
                val dbIntent = Intent(this@NoteActivity,
                        DatabaseService::class.java)
                dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
                dbIntent.putExtra(DatabaseService.EXTRA_OPERATION,
                        MODE.CREATE.mode)
                startService(dbIntent)
                sendMessage(true)

            }
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = object : Handler(Looper.getMainLooper()){
            override fun handleMessage(msg: Message?) {
                msg?.let {
                    var color = R.color.vermilion
                    if (msg.arg1 > 0) {
                        color = R.color.green
                    }
                    indicator.setBackgroundColor(ContextCompat.getColor(
                            this@NoteActivity,
                            color
                    ))
                }
                super.handleMessage(msg)
            }
        }
        note_title.addTextChangedListener(textWatcher)
        note_content.addTextChangedListener(textWatcher)
    }

    private fun updateNote() {
        if (note == null) {
            if (!TextUtils.isEmpty(getNoteTitle()) &&
                    !TextUtils.isEmpty(getNoteContent())) {
                LocationProvider.subscribe(locationListener)
            }
        } else {
            note?.title = getNoteTitle()
            note?.message = getNoteContent()
            // Switching to intent service.
            val dbIntent = Intent(this@NoteActivity,
                    DatabaseService::class.java)
            dbIntent.putExtra(DatabaseService.EXTRA_ENTRY, note)
            dbIntent.putExtra(DatabaseService.EXTRA_OPERATION,
                    MODE.EDIT.mode)
            startService(dbIntent)
            sendMessage(true)
        }
    }

    private fun sendMessage(result: Boolean) {
        val msg = handler?.obtainMessage()
        if (result) {
            msg?.arg1 = 1
        } else {
            msg?.arg1 = 0
        }
        handler?.sendMessage(msg)
    }
    private fun getNoteContent(): String {
        return note_content.text.toString()
    }

    private fun getNoteTitle(): String {
        return note_title.text.toString()
    }
}