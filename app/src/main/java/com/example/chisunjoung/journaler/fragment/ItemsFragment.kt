package com.example.chisunjoung.journaler.fragment

import com.example.chisunjoung.journaler.R

/**
 * Created by chisunjoung on 14/12/2017.
 */

class ItemsFragment : BaseFragement(){
    override val logTag: String
        get() = "Items fragment"

    override fun getLayout(): Int {

        return R.layout.fragement_items
    }
}