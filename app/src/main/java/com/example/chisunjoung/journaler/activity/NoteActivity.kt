package com.example.chisunjoung.journaler.activity

import android.location.Location
import android.location.LocationListener
import android.os.AsyncTask
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.database.Db
import com.example.chisunjoung.journaler.execution.TaskExecutor
import com.example.chisunjoung.journaler.location.LocationProvider
import com.example.chisunjoung.journaler.model.Note
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
    private val executor = TaskExecutor.getInstance(1)
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
                executor.execute {
                    val param = note
                    var result = false
                    param?.let { result = Db.NOTE.insert(param) > 0 }
                    if (result) {
                        Log.i(tag, "Note inserted.")
                    } else {
                        Log.e(tag, "Note not inserted.")
                    }
                    handler?.post {
                        var color = R.color.vermilion
                        if (result) {
                            color = R.color.green
                        }
                        indicator.setBackgroundColor(
                                ContextCompat.getColor(
                                        this@NoteActivity,
                                        color
                                )
                        )
                    }
                }

            }
        }
        override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        override fun onProviderEnabled(p0: String?) {}
        override fun onProviderDisabled(p0: String?) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler = Handler(Looper.getMainLooper())
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
            executor.execute {
                val param = note
                var result = false
                param?.let { result = Db.NOTE.update(param) > 0 }
                if (result) {
                    Log.i(tag, "Note updated.")
                } else {
                    Log.e(tag, "Note not updated.")
                }

                handler?.post {
                    var color = R.color.vermilion
                    if (result) {
                        color = R.color.green
                    }
                    indicator.setBackgroundColor(
                            ContextCompat.getColor(
                            this@NoteActivity,
                            color
                    ))
                }
            }
        }
    }

    private fun getNoteContent(): String {
        return note_content.text.toString()
    }

    private fun getNoteTitle(): String {
        return note_title.text.toString()
    }
}