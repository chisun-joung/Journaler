package com.example.chisunjoung.journaler.database

import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.chisunjoung.journaler.Journaler

/**
 * Created by chisunjoung on 2017. 12. 25..
 */

class DbHelper(val dbName: String, val version: Int) : SQLiteOpenHelper(
        Journaler.ctx, dbName, null,version
){
    companion object {
        val ID: String = "_id"
        val TABLE_TODOS = "todos"
        val TABLE_NOTES = "notes"
        val COLUMN_TITLE: String = "title"
        val COLUMN_MESSAGE: String = "message"
        val COLUMN_SCHEDULED: String = "scheduled"
        val COLUMN_LOCATION: String = "location"

    }

    private val tag = "DbHelper"

    private val createTableNotes = """
        CREATE TABLE if not exists $TABLE_NOTES
            (
                $ID integer PRIMARY KEY autoincrement,
                $COLUMN_TITLE text,
                $COLUMN_MESSAGE text,
                $COLUMN_LOCATION text
            )
        """
    private val createTableTodos =  """
        CREATE TABLE if not exists $TABLE_TODOS
           (
              $ID integer PRIMARY KEY autoincrement,
              $COLUMN_TITLE text,
              $COLUMN_MESSAGE text,
              $COLUMN_SCHEDULED integer,
              $COLUMN_LOCATION text
           )
         """

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(tag, "Database [ CREATING ] ")
        db?.execSQL(createTableNotes)
        db?.execSQL(createTableTodos)
        Log.d(tag, "Database [ CREATED ] ")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //
    }
}