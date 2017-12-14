package com.example.chisunjoung.journaler.fragment

import com.example.chisunjoung.journaler.R

/**
 * Created by chisunjoung on 14/12/2017.
 */

class ManualFragment : BaseFragement() {
    override val logTag: String
        get() = "Manual Fragment"

    override fun getLayout(): Int {
        return R.layout.fragment_manual
    }

}