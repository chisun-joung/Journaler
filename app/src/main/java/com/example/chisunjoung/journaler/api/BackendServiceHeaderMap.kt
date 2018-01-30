package com.example.chisunjoung.journaler.api

/**
 * Created by chisunjoung on 2018. 1. 29..
 */

object BackendServiceHeaderMap {

    fun obtain(authorization: Boolean = false): Map<String, String> {
        val map = mutableMapOf(
                Pair("Accept","*/*"),
                Pair("Content-Type", "Application/json; charset=UTF-8")
        )
        if (authorization) {
            map["Authorization"] = "Bearer ${TokenManager.currentToken.token}"
        }
        return map
    }
}