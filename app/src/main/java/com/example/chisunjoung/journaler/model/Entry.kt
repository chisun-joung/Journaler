package com.example.chisunjoung.journaler.model

import android.location.Location
import com.example.chisunjoung.journaler.database.DbModel

/**
 * Created by chisunjoung on 2017. 12. 25..
 */
abstract class Entry (
        var title: String,
        var message: String,
        var location: Location
) : DbModel()