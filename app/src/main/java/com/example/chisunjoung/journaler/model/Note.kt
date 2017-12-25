package com.example.chisunjoung.journaler.model

import android.location.Location

/**
 * Created by chisunjoung on 2017. 12. 25..
 */

class Note(
        title: String,
        message: String,
        location: Location
) : Entry(
        title,
        message,
        location
){
    override var id =0L
}