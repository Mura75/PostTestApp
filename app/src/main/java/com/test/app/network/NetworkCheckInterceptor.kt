package com.test.app.network

import android.content.Context
import okhttp3.Interceptor
import okhttp3.Response
import com.test.app.R
import javax.inject.Inject


class NetworkCheckInterceptor @Inject constructor(
    private val networkChecker: NetworkChecker,
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
        if (!networkChecker.isConnected()) {
            throw NoNetworkException(context.resources.getString(R.string.no_network))
        }
        return chain.proceed(requestBuilder?.build())
    }
}
