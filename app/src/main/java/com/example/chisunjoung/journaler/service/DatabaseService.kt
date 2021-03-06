package com.example.chisunjoung.journaler.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import com.example.chisunjoung.journaler.database.Crud
import com.example.chisunjoung.journaler.database.Content
import com.example.chisunjoung.journaler.model.MODE
import com.example.chisunjoung.journaler.model.Note

/**
 * Created by chisunjoung on 2018. 1. 13..
 */

class DatabaseService :
        IntentService("DatabaseService") {

    companion object {
        val EXTRA_ENTRY = "entry"
        val EXTRA_OPERATION = "operation"
    }

    private val tag = "Database service"

    override fun onCreate() {
        super.onCreate()
        Log.v(tag, "[ ON CREATE ]")
    }

    override fun onLowMemory() {
        super.onLowMemory()
        Log.w(tag, "[ ON LOW MEMORY ]")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v(tag, "[ ON DESTROY ]")
    }

    override fun onHandleIntent(p0: Intent?) {
        p0?.let {
            val note = p0.getParcelableExtra<Note>(EXTRA_ENTRY)
            note?.let {
                val operation = p0.getIntExtra(EXTRA_OPERATION, -1)
                when (operation) {
                    MODE.CREATE.mode -> {
                        val result = Content.NOTE.insert(note)
                        if (result > 0) {
                            Log.i(tag, "Note inserted.")
                        } else {
                            Log.e(tag, "Note not inserted.")
                        }
                        broadcastId(result)
                    }
                    MODE.EDIT.mode -> {
                        var result = 0
                        try {
                            result = Content.NOTE.update(note)
                        } catch (e: Exception){
                            Log.e(tag, "Error: $e")
                        }
                        if (result > 0) {
                            Log.i(tag, "Note updated.")
                        } else {
                            Log.e(tag, "Note not updated.")
                        }
                    }
                    else -> {
                        Log.w(tag, "Unknown mode [ $operation ]")
                    }

                }

            }

        }

    }

    private fun broadcastId(id: Long) {
        val intent = Intent(Crud.BROADCAST_ACTION)
        intent.putExtra(Crud.BROADCAST_EXTRAS_KEY_CRUD_OPERATION_RESULT, id)
        sendBroadcast(intent)
    }

}