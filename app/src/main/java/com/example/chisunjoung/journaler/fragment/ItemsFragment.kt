package com.example.chisunjoung.journaler.fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.chisunjoung.journaler.R
import com.example.chisunjoung.journaler.activity.NoteActivity
import com.example.chisunjoung.journaler.activity.TodoActivity
import com.example.chisunjoung.journaler.model.MODE

/**
 * Created by chisunjoung on 14/12/2017.
 */

class ItemsFragment : BaseFragement(){
    override val logTag: String
        get() = "Items fragment"

    override fun getLayout(): Int {

        return R.layout.fragement_items
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(getLayout(), container, false)
        val btn = view?.findViewById<FloatingActionButton>(R.id.new_item)
        btn?.setOnClickListener {
            val items = arrayOf(
                    getString(R.string.todos),
                    getString(R.string.notes)
            )
            val builder =
                    AlertDialog.Builder(this@ItemsFragment.context)
                            .setTitle(R.string.choose_a_type)
                            .setItems(
                                    items,
                                    {
                                        _, which ->
                                        when (which) {
                                            0 -> {
                                                openCreateTodo()
                                            }
                                            1 -> {
                                                openCreateNote()
                                            }
                                            else -> Log.e(logTag, "Unknown option selected [ $which ]")
                                        }
                                    }
                            )
            builder.show()
        }
        return view
    }

    private fun openCreateNote(){
        val intent = Intent(context, NoteActivity::class.java)
        intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        startActivity(intent)
    }
    private fun openCreateTodo(){
        val intent = Intent(context, TodoActivity::class.java)
        intent.putExtra(MODE.EXTRAS_KEY, MODE.CREATE.mode)
        startActivity(intent)
    }
}