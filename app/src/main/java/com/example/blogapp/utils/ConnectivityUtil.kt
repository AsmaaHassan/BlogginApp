package com.example.blogapp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

class ConnectivityUtil {

    companion object {
        @RequiresApi(Build.VERSION_CODES.M)
        fun isInternetAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val capability =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            return capability?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) ?: false
        }
    }
}