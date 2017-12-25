package com.example.chisunjoung.journaler.permission

/**
 * Created by chisunjoung on 2017. 12. 25..
 */

interface PermissionRequestCallback {
    fun onPermissionGranted(permissions: List<String>)
    fun onPermissionDenied(permissions: List<String>)
}