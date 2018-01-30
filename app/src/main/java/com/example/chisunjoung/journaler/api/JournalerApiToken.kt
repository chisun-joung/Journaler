package com.example.chisunjoung.journaler.api

import com.google.gson.annotations.SerializedName

/**
 * Created by chisunjoung on 2018. 1. 23..
 */
data class JournalerApiToken(
        @SerializedName("id_token") val token: String,
        val expires: Long
)