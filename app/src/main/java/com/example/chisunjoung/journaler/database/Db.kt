package com.example.chisunjoung.journaler.database

import android.content.ContentValues
import android.util.Log
import com.example.chisunjoung.journaler.model.Note
import com.example.chisunjoung.journaler.model.Todo

/**
 * Created by chisunjoung on 2017. 12. 28..
 */

object Db {
    private val tag = "Db"
    private val version = 1
    private val name = "students"

    val NOTE = object : Crud<Note> {
        override fun insert(what: Note): Long {
            val inserted = insert(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }

        override fun insert(what: Collection<Note>): List<Long> {
            val db = DbHelper(name, version).writableDatabase
            db.beginTransaction()
            var inserted = 0
            val items = mutableListOf<Long>()
            what.forEach { item ->
                val values = ContentValues()
                val table = DbHelper.TABLE_NOTES
                values.put(DbHelper.COLUMN_TITLE,item.title)
                values.put(DbHelper.COLUMN_MESSAGE, item.message)
                values.put(DbHelper.COLUMN_LOCATION_LATITUDE, item.location.latitude)
                values.put(DbHelper.COLUMN_LOCATION_LONGITUDE, item.location.longitude)
                val id = db.insert(table,null,values)
                if (id > 0) {
                    items.add(id)
                    Log.v(tag,"Entry ID assigned [ $id ]")
                    inserted++
                }
            }
            val success = inserted == what.size
            if (success) {
                db.setTransactionSuccessful()
            } else {
                items.clear()
            }
            db.endTransaction()
            db.close()
            return items
        }

    }

    val TODO = object : Crud<Todo>{
        override fun insert(what: Todo): Long {
            val inserted = insert(listOf(what))
            if (!inserted.isEmpty()) return inserted[0]
            return 0
        }

        override fun insert(what: Collection<Todo>): List<Long> {
            val db = DbHelper(name, version).writableDatabase
            db.beginTransaction()
            var inserted = 0
            val items = mutableListOf<Long>()
            what.forEach { item ->
                val table = DbHelper.TABLE_TODOS
                val values = ContentValues()
                values.put(DbHelper.COLUMN_TITLE, item.title)
                values.put(DbHelper.COLUMN_MESSAGE, item.message)
                values.put(DbHelper.COLUMN_LOCATION_LATITUDE,
                        item.location.latitude)
                values.put(DbHelper.COLUMN_LOCATION_LONGITUDE,
                        item.location.longitude)
                values.put(DbHelper.COLUMN_SCHEDULED, item.scheduledFor)
                val id = db.insert(table, null, values)
                if (id > 0) {
                    item.id = id
                    Log.v(tag, "Entry ID assigned [ $id ]")
                    inserted++
                }
            }
            val success = inserted == what.size
            if (success) {
                db.setTransactionSuccessful()
            } else {
                items.clear()
            }
            db.endTransaction()
            db.close()
            return items
        }
    }
}