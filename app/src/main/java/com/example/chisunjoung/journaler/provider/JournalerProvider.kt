package com.example.chisunjoung.journaler.provider

import android.content.ContentProvider
import android.content.UriMatcher
import android.database.sqlite.SQLiteDatabase
import com.example.chisunjoung.journaler.database.DbHelper

/**
 * Created by chisunjoung on 01/02/2018.
 */

class JournalerProvider : ContentProvider() {
    private val version = 1
    private val name = "journaler"
    private val db: SQLiteDatabase by lazy {
        DbHelper(name,version).writableDatabase
    }

    companion object {
        private val dataTypeNote = "note"
        private val dataTypeNotes = "notes"
        private val dataTypeTodo = "todo"
        private val dataTypeTodos = "todos"
        val AUTHORITY = "com.journaler.provider"
        val URL_NOTE = "content://$AUTHORITY/$dataTypeNote"
        val URL_TODO = "content://$AUTHORITY/$dataTypeTodo"
        val URL_NOTES = "content://$AUTHORITY/$dataTypeNotes"
        val URL_TODOS = "content://$AUTHORITY/$dataTypeTodos"
        private val matcher = UriMatcher(UriMatcher.NO_MATCH)
        private val NOTE_ALL = 1
        private val NOTE_ITEM = 2
        private val TODO_ALL = 3
        private val TODO_ITEM = 4
    }

    /**
     * We register uri paths in the following format:
     *
     * <prefix>://<authority>/<data_type>/<id>
     * <prefix> - This is always set to content://
     * <authority> - Name for the content provider
     * <data_type> - The type of data we provide in this Uri
     * <id> - Record ID.
     */
    init {
        /**
         * The calls to addURI() go here,
         * for all of the content URI patterns that the provider should
        recognize.
         *
         * First:
         *
         * Sets the integer value for multiple rows in notes (TODOs) to
        1.
         * Notice that no wildcard is used in the path.
         *
         * Second:
         *
         * Sets the code for a single row to 2. In this case, the "#"
        wildcard is
         * used. "content://com.journaler.provider/note/3" matches, but
         * "content://com.journaler.provider/note doesn't.
         *
         * The same applies for TODOs.
         *
         * addUri() params:
         *
         * authority    - String: the authority to match
         *
         * path         - String: the path to match.
         *              * may be used as a wild card for any text,
         *              and # may be used as a wild card for numbers.
         *
         * code              - int: the code that is returned when a
        URI
         *              is matched against the given components.
         */
        matcher.addURI(AUTHORITY, dataTypeNote, NOTE_ALL)
        matcher.addURI(AUTHORITY, "$dataTypeNotes/#", NOTE_ITEM)
        matcher.addURI(AUTHORITY, dataTypeTodo, TODO_ALL)
        matcher.addURI(AUTHORITY, "$dataTypeTodos/#", TODO_ITEM)
    }

    /**
     * True - if the provider was successfully loaded
     */
    override fun onCreate() = true


}