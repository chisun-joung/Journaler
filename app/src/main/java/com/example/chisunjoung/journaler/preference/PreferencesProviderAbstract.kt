package com.example.chisunjoung.journaler.preference

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by chisunjoung on 2018. 1. 4..
 */

abstract class PreferencesProviderAbstract {
    abstract fun obtain(configuration: PreferencesConfiguration,
                        ctx: Context): SharedPreferences
}