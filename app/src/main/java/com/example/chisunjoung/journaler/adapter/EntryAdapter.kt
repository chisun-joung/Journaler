package com.example.chisunjoung.journaler.adapter

import android.content.Context
import android.database.Cursor
import android.support.v4.widget.CursorAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.database.DbHelper
import com.example.chisunjoung.journaler.model.Entry

/**
 * Created by chisunjoung on 06/02/2018.
 */

class EntryAdapter(ctx: Context, crsr: Cursor) : CursorAdapter(ctx, crsr) {
    override fun newView(p0: Context?, p1: Cursor?, p2: ViewGroup?): View {
        val inflater = LayoutInflater.from(p0)
        return inflater.inflate(R.layout.adapter_entry, null)
    }

    override fun bindView(p0: View?, p1: Context?, p2: Cursor?) {
        p0?.let {
            val label = p0.findViewById<TextView>(R.id.title)
            label.text = cursor.getString(cursor.getColumnIndexOrThrow(DbHelper.COLUMN_TITLE))
        }

    }
}