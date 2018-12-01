package com.test.app.network

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject


class NetworkChecker @Inject constructor(val context: Context) {

    fun isConnected(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectivityManager.activeNetworkInfo
        ConnectivityManager.NetworkCallback()
        return netInfo != null && netInfo.isConnected
    }
}